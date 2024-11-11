import React from "react";
import ReactDOM from "react-dom/client";

import { RouterProvider } from "react-router-dom";
import router from "./Router";
import { ThemeProvider } from "styled-components";
import theme from "./styles/theme";

//index.html 의 root element에서 모든 랜더링 시작
const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);
root.render(
  //개발 모드
  <React.StrictMode>
    {/* 테마 프로바이더(styles 폴더의 theme을 사용하기 위해 이용) */}
    <ThemeProvider theme={theme}>
      {/* 라우트  */}
      <RouterProvider router={router} />
    </ThemeProvider>
  </React.StrictMode>
);
