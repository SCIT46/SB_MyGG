import { createBrowserRouter } from "react-router-dom";
import Root from "./Root";
import Home from "./pages/Home";
import Items from "./pages/Items";
import Champions from "./pages/Champions";
import ChampionDetail from "./pages/ChampionDetail";
import ItemDetail from "./pages/ItemDetail";
import SearchPlayer from "./pages/SearchPlayer";
import NotFound from "./pages/NotFound";
import Test from "./pages/Test";

//라우터 생성
const router = createBrowserRouter([
  {
    // path의 라우트로 이동시 Root 컴포넌트 렌더링
    path: "/",
    element: <Root />,
    // 하위 컴포넌트 지정
    children: [
      { path: "", element: <Home /> },
      { path: "item", element: <Items /> },
      { path: "item/:id", element: <ItemDetail /> },
      { path: "champion", element: <Champions /> },
      { path: "champion/:id", element: <ChampionDetail /> },
      { path: "search/:id", element: <SearchPlayer /> },
      //테스트 페이지
      { path: "test", element: <Test /> },
      //not found 페이지
      { path: "*", element: <NotFound /> },
    ],
  },
]);

export default router;
