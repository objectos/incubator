/*
 * Copyright 2023 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package br.com.objectos.http.server.jetty;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JettyHttpTest {

  private InetAddress address;

  private int port;

  private Server server;

  @BeforeClass
  public void setupServer() throws Exception {
    address = InetAddress.getLoopbackAddress();

    port = 9123;

    server = new Server();

    ServerConnector connector;
    connector = new ServerConnector(server);

    connector.setHost(address.getHostName());

    connector.setPort(port);

    connector.setIdleTimeout(5);

    server.addConnector(connector);

    server.start();
  }

  @Test
  public void getRequest() throws IOException {
    try (Socket socket = new Socket(address, port)) {
      req(socket, """
      GET / HTTP/1.1
      Host: 127.0.0.1:9123
      Connection: close

      """.replace("\n", "\r\n"));

      assertStartsWith(
        resp(socket),

        """
        HTTP/1.1 404 Not Found
        Connection: close
        Cache-Control: must-revalidate,no-cache,no-store
        Content-Type: text/html;charset=iso-8859-1
        Content-Length: 438
        Server: Jetty(9.4.35.v20201120)

        """.replace("\n", "\r\n")
      );
    }
  }

  @Test
  public void badRequestIllegalChar() throws IOException {
    try (Socket socket = new Socket(address, port)) {
      req(socket, """
      FOO / HTTP/1.1
      Host: 127.0.0.1:9123
      Éç: FooBar

      """);

      assertStartsWith(
        resp(socket),

        """
        HTTP/1.1 400 Illegal character OTEXT=0xc3
        Content-Type: text/html;charset=iso-8859-1
        Content-Length: 71
        Connection: close
        Server: Jetty(9.4.35.v20201120)

        """.replace("\n", "\r\n")
      );
    }
  }

  @Test
  public void badRequestNoHost() throws IOException {
    try (Socket socket = new Socket(address, port)) {
      req(socket, """
      FOO / HTTP/1.1
      Connection: Close

      """);

      assertStartsWith(
        resp(socket),

        """
        HTTP/1.1 400 No Host
        Content-Type: text/html;charset=iso-8859-1
        Content-Length: 50
        Connection: close
        Server: Jetty(9.4.35.v20201120)

        """.replace("\n", "\r\n")
      );
    }
  }

  @AfterClass(alwaysRun = true)
  public void shutdownServer() throws Exception {
    if (server != null) {
      server.stop();
    }
  }

  private void assertStartsWith(String resp, String expected) {
    assertTrue(resp.startsWith(expected), resp);
  }

  private void req(Socket s, String textBlock) throws IOException {
    OutputStream out;
    out = s.getOutputStream();

    byte[] bytes;
    bytes = textBlock.getBytes(StandardCharsets.UTF_8);

    out.write(bytes);
  }

  private String resp(Socket s) throws IOException {
    InputStream in;
    in = s.getInputStream();

    byte[] bytes;
    bytes = in.readAllBytes();

    return new String(bytes, StandardCharsets.UTF_8);
  }

}