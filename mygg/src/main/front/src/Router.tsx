import { createBrowserRouter, Navigate, redirect } from "react-router-dom";
import React, { Suspense, lazy } from "react";
import Root from "./Root";
import NotFound from "./pages/NotFound/NotFound";
import LoadingSpinner from "./components/LoadingSpinner";
import LoginPage from "./pages/Login/LoginPage";
import BoardPage from "./pages/Board/BoardPage";

// Lazy load the components
const HomePage = lazy(() => import("./pages/Home/HomePage"));
const ItemPage = lazy(() => import("./pages/Item/ItemPage"));
const ItemDetailPage = lazy(() => import("./pages/ItemDetail/ItemDetailPage"));
const ChampionsPage = lazy(() => import("./pages/Champion/ChampionsPage"));
const ChampionDetailPage = lazy(
  () => import("./pages/championDetail/ChampionDetailPage")
);
const SearchPlayerPage = lazy(
  () => import("./pages/SearchPlayer/SearchPlayerPage")
);
const Test = lazy(() => import("./pages/Test/Test"));

//라우터 생성
const router = createBrowserRouter([
  {
    // path의 라우트로 이동시 Root 컴포넌트 렌더링
    path: "/",
    element: <Root />,
    // 하위 컴포넌트 지정
    children: [
      {
        path: "",
        element: (
          <Suspense fallback={<LoadingSpinner />}>
            <HomePage />
          </Suspense>
        ),
      },
      {
        path: "item",
        element: (
          <Suspense fallback={<LoadingSpinner />}>
            <ItemPage />
          </Suspense>
        ),
      },
      {
        path: "item/:id",
        element: (
          <Suspense fallback={<LoadingSpinner />}>
            <ItemDetailPage />
          </Suspense>
        ),
      },
      {
        path: "champion",
        element: (
          <Suspense fallback={<LoadingSpinner />}>
            <ChampionsPage />
          </Suspense>
        ),
      },
      {
        path: "champion/:id",
        element: (
          <Suspense fallback={<LoadingSpinner />}>
            <ChampionDetailPage />
          </Suspense>
        ),
      },
      {
        path: "search/:id",
        element: (
          <Suspense fallback={<LoadingSpinner />}>
            <SearchPlayerPage />
          </Suspense>
        ),
      },
      // 로그인 페이지
      { path: "login", element: <LoginPage /> },
      // 익명게시판 페이지
      { path: "board", element: <BoardPage /> },
      //테스트 페이지
      {
        path: "test",
        element: (
          <Suspense fallback={<LoadingSpinner />}>
            <Test />
          </Suspense>
        ),
      },
      { path: "images/*", element: <Navigate to="/" replace /> },
      //not found 페이지
      { path: "*", element: <NotFound /> },
    ],
  },
  {
    path: "/api/*",
    loader: () => redirect("/"),
  },
]);

export default router;
