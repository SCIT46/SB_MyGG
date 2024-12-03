import React from "react";
import ReactDOM from "react-dom/client";

import { RouterProvider } from "react-router-dom";
import router from "./Router";
import { ThemeProvider } from "styled-components";
import theme from "./styles/theme";
import { QueryClient, QueryClientProvider } from "react-query";

//index.html 의 root element에서 모든 랜더링 시작
const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);
const queryClient = new QueryClient();
root.render(
  //개발 모드
  // <React.StrictMode>
  // {/* 리액트 쿼리 사용을 위한 컴포넌트 */}
  <QueryClientProvider client={queryClient}>
    {/* 테마 프로바이더(styles 폴더의 theme을 사용하기 위해 이용) */}
    <ThemeProvider theme={theme}>
      {/* 라우트  */}
      <RouterProvider router={router} />
    </ThemeProvider>
  </QueryClientProvider>
  // </React.StrictMode>
);
