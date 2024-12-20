import { createBrowserRouter, redirect } from "react-router-dom";
import Root from "./Root";
import ChampionDetail from "./pages/ChampionDetail";
import NotFound from "./pages/NotFound";
import Itempage from "./pages/Item/ItemPage";
import Home from "./pages/HomePage/Home";
import SearchPlayer from "./pages/SearchPlayer/SearchPlayer";
import Champions from "./pages/Champion/Champions";
import Test from "./pages/Test/Test";
import ItemDetailPage from "./pages/ItemDetail/ItemDetailPage";

//라우터 생성
const router = createBrowserRouter([
  {
    // path의 라우트로 이동시 Root 컴포넌트 렌더링
    path: "/",
    element: <Root />,
    // 하위 컴포넌트 지정
    children: [
      { path: "", element: <Home /> },
      { path: "item", element: <Itempage /> },
      { path: "item/:id", element: <ItemDetailPage /> },
      { path: "champion", element: <Champions /> },
      { path: "champion/:id", element: <ChampionDetail /> },
      { path: "search/:id", element: <SearchPlayer /> },
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
