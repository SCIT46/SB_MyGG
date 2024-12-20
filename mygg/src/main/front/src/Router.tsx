import { createBrowserRouter, redirect } from "react-router-dom";
import Root from "./Root";
import HomePage from "./pages/Home/HomePage";
import ItemPage from "./pages/Item/ItemPage";
import ItemDetailPage from "./pages/ItemDetail/ItemPage";
import ChampionsPage from "./pages/Champion/ChampionsPage";
import ChampionDetailPage from "./pages/championDetail/ChampionDetailPage";
import SearchPlayerPage from "./pages/SearchPlayer/SearchPlayerPage";
import Test from "./pages/Test/Test";
import NotFound from "./pages/NotFound/NotFound";

//라우터 생성
const router = createBrowserRouter([
  {
    // path의 라우트로 이동시 Root 컴포넌트 렌더링
    path: "/",
    element: <Root />,
    // 하위 컴포넌트 지정
    children: [
      { path: "", element: <HomePage /> },
      { path: "item", element: <ItemPage /> },
      { path: "item/:id", element: <ItemDetailPage /> },
      { path: "champion", element: <ChampionsPage /> },
      { path: "champion/:id", element: <ChampionDetailPage /> },
      { path: "search/:id", element: <SearchPlayerPage /> },
      //테스트 페이지
      { path: "test", element: <Test /> },
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
