/*
 * Copyright (C) 2022 Objectos Software LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package objectos.docs;

import br.com.objectos.css.framework.border.BorderBottom;
import br.com.objectos.css.framework.border.BorderColor;
import br.com.objectos.css.framework.sizing.Height;
import br.com.objectos.css.framework.sizing.MaxWidth;
import br.com.objectos.css.framework.spacing.MarginX;
import br.com.objectos.css.framework.spacing.PaddingY;
import br.com.objectos.html.tmpl.AbstractFragment;

final class DocsTopBar extends AbstractFragment implements Docs.TopBar {

  @Override
  public final AbstractFragment toFragment() { return this; }

  @Override
  protected final void definition() {
    header(
      BorderColor.gray300,
      BorderBottom.v1,
      Height.v16,
      PaddingY.v02,

      nav(
        MarginX.auto,
        MaxWidth.screenX2l,

        a(
          href("https://www.objectos.com.br/index.html"),

          svg(
            Height.v08,

            width("200"),
            height("49.28"),
            viewBox("0 0 200 49.28"),
            xmlns("http://www.w3.org/2000/svg"),

            path(
              d("m189.6 38.21q-2.53 0-5.3-0.932-2.76-0.903-4.6-3.087-0.38-0.495-0.35-1.02 0.12-0.582 0.64-0.99 0.5-0.32 1.02-0.233 0.58 0.09 0.93 0.524 1.4 1.66 3.38 2.33 2.04 0.641 4.43 0.641 4.08 0 5.77-1.456 1.71-1.456 1.71-3.408 0-1.893-1.86-3.087-1.78-1.281-5.56-1.806-4.87-0.669-7.2-2.621-2.33-1.951-2.33-4.514 0-2.417 1.23-4.077 1.19-1.689 3.29-2.534 2.12-0.874 4.86-0.874 3.29 0 5.56 1.223 2.31 1.165 3.7 3.146 0.38 0.495 0.24 1.077-0.1 0.525-0.73 0.874-0.47 0.233-1.02 0.146-0.53-0.09-0.9-0.583-1.23-1.543-2.97-2.33-1.69-0.815-3.99-0.815-3.06 0-4.75 1.31-1.69 1.311-1.69 3.146 0 1.252 0.67 2.242 0.73 0.903 2.33 1.602 1.6 0.612 4.28 1.02 3.64 0.466 5.71 1.63 2.15 1.165 3.03 2.738 0.9 1.485 0.9 3.233 0 2.301-1.46 3.99-1.45 1.689-3.81 2.621-2.39 0.874-5.16 0.874zm-27.97 0q-3.9 0-6.99-1.748-3.05-1.805-4.85-4.863-1.75-3.088-1.75-6.932 0-3.873 1.75-6.931 1.8-3.117 4.85-4.864 3.09-1.806 6.99-1.806 3.89 0 6.95 1.806 3.05 1.747 4.8 4.864 1.81 3.058 1.81 6.931 0 3.844-1.81 6.932-1.75 3.058-4.8 4.863-3.06 1.748-6.95 1.748zm0-2.709q3.07 0 5.43-1.427 2.45-1.456 3.79-3.873 1.42-2.476 1.42-5.592 0-3.058-1.42-5.475-1.34-2.476-3.79-3.874-2.36-1.456-5.43-1.456-3.03 0-5.45 1.456-2.41 1.398-3.83 3.874-1.4 2.417-1.4 5.533 0 3.058 1.4 5.534 1.42 2.417 3.83 3.873 2.42 1.427 5.45 1.427zm-19.01 2.418q-2.65-0.06-4.75-1.224-2.09-1.194-3.26-3.291-1.16-2.126-1.16-4.805v-24.17q0-0.67 0.4-1.078 0.44-0.436 1.05-0.436 0.7 0 1.08 0.436 0.44 0.408 0.44 1.078v24.17q0 2.825 1.74 4.601 1.75 1.748 4.52 1.748h1.08q0.67 0 1.05 0.437 0.43 0.407 0.43 1.077 0 0.641-0.43 1.078-0.38 0.379-1.05 0.379zm-12.96-22.95q-0.58 0-0.96-0.35-0.35-0.378-0.35-0.961 0-0.582 0.35-0.932 0.38-0.378 0.96-0.378h13.16q0.59 0 0.94 0.378 0.38 0.35 0.38 0.932 0 0.583-0.38 0.961-0.35 0.35-0.94 0.35zm-13.09 23.24q-3.78 0-6.79-1.806-2.96-1.776-4.71-4.834-1.7-3.059-1.7-6.903 0-3.873 1.6-6.931t4.42-4.806q2.81-1.806 6.45-1.806 3.1 0 5.7 1.224 2.56 1.165 4.45 3.582 0.38 0.495 0.29 1.019-0.1 0.525-0.64 0.874-0.44 0.349-0.96 0.291-0.52-0.09-0.93-0.582-3.09-3.699-7.91-3.699-2.86 0-5.04 1.427-2.14 1.398-3.35 3.815-1.17 2.447-1.17 5.592 0 3.058 1.31 5.534 1.31 2.417 3.59 3.873 2.33 1.427 5.39 1.427 1.99 0 3.74-0.582 1.81-0.583 3.12-1.806 0.44-0.379 0.96-0.437 0.52-0.06 0.93 0.35 0.47 0.437 0.47 1.019 0.1 0.524-0.38 0.903-3.55 3.262-8.84 3.262zm-27.69-0.06q-3.84 0-6.85-1.69-2.96-1.747-4.66-4.805t-1.7-6.99q0-3.99 1.61-6.99 1.6-3.058 4.41-4.805 2.82-1.748 6.46-1.748 3.59 0 6.36 1.69 2.76 1.66 4.31 4.63 1.56 2.913 1.56 6.728 0 0.641-0.39 1.019-0.39 0.35-1.02 0.35h-21.35v-2.534h22.13l-2.14 1.602q0.1-3.146-1.07-5.563-1.16-2.446-3.35-3.786-2.13-1.427-5.04-1.427-2.77 0-4.95 1.427-2.14 1.34-3.4 3.786-1.21 2.417-1.21 5.621 0 3.146 1.31 5.592 1.31 2.417 3.64 3.815 2.33 1.369 5.34 1.369 1.89 0 3.78-0.641 1.94-0.67 3.06-1.747 0.39-0.379 0.92-0.379 0.58-0.06 0.97 0.291 0.54 0.437 0.54 0.962 0 0.553-0.44 0.99-1.55 1.398-4.08 2.33-2.47 0.903-4.75 0.903zm-30.93 11.13q-0.64 0-1.07-0.44-0.44-0.38-0.44-1.02 0-0.67 0.44-1.1 0.43-0.41 1.07-0.41 2.47 0 4.31-1.05 1.9-1.08 2.97-2.97 1.06-1.89 1.06-4.313v-25.16q0-0.67 0.39-1.049 0.44-0.408 1.07-0.408 0.68 0 1.07 0.408 0.43 0.379 0.43 1.049v25.16q0 3.291-1.45 5.821-1.46 2.57-4.03 4.02-2.52 1.46-5.82 1.46zm9.75-43.48q-0.92 0-1.6-0.641-0.63-0.67-0.63-1.66 0-1.107 0.68-1.631 0.73-0.582 1.6-0.582 0.82 0 1.5 0.582 0.73 0.524 0.73 1.631 0 0.99-0.68 1.66-0.63 0.641-1.6 0.641zm-21.55 32.42q-3.79 0-6.84-1.748-3.06-1.747-4.86-4.747-1.75-3.029-1.84-6.815v-23.44q0-0.67 0.39-1.048 0.43-0.408 1.06-0.408 0.68 0 1.07 0.408 0.39 0.378 0.39 1.048v15.14q1.55-2.505 4.32-4.019 2.82-1.515 6.31-1.515 3.88 0 6.94 1.806 3.11 1.747 4.85 4.805 1.8 3.058 1.8 6.932 0 3.902-1.8 6.99-1.74 3.058-4.85 4.863-3.06 1.748-6.94 1.748zm0-2.709q3.06 0 5.44-1.427 2.42-1.456 3.83-3.873 1.41-2.476 1.41-5.592 0-3.087-1.41-5.534-1.41-2.417-3.83-3.815-2.38-1.456-5.44-1.456-3.01 0-5.44 1.456-2.42 1.398-3.83 3.815-1.36 2.447-1.36 5.534 0 3.116 1.36 5.592 1.41 2.417 3.83 3.873 2.43 1.427 5.44 1.427zm-32.53 2.709q-3.88 0-6.99-1.748-3.06-1.805-4.85-4.863-1.75-3.088-1.75-6.932 0-3.873 1.75-6.931 1.79-3.117 4.85-4.864 3.11-1.806 6.99-1.806t6.94 1.806q3.06 1.747 4.8 4.864 1.8 3.058 1.8 6.931 0 3.844-1.8 6.932-1.74 3.058-4.8 4.863-3.06 1.748-6.94 1.748zm0-2.709q3.06 0 5.44-1.427 2.42-1.456 3.78-3.873 1.41-2.476 1.41-5.592 0-3.058-1.41-5.475-1.36-2.476-3.78-3.874-2.38-1.456-5.44-1.456-3.01 0-5.44 1.456-2.42 1.398-3.83 3.874-1.41 2.417-1.41 5.533 0 3.058 1.41 5.534 1.41 2.417 3.83 3.873 2.43 1.427 5.44 1.427z"),
              fill("#4c7cda"),
              strokeWidth(".9101")
            )
          )
        )
      )
    );
  }

}