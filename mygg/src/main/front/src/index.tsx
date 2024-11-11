import React from "react";
import ReactDOM from "react-dom/client";

import { RouterProvider } from "react-router-dom";
import router from "./Router";

//index.html 의 root element에서 모든 랜더링 시작
const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);
root.render(
  //개발 모드
  <React.StrictMode>
    {/* 라우트  */}
    <RouterProvider router={router} />
  </React.StrictMode>
);
