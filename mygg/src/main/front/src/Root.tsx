import { Outlet } from "react-router-dom";
import { GlobalStyle } from "./styles/GlobalStyle";
import Header from "./components/Header";

function Root() {
  return (
    <>
      {/* 전역 css스타일 */}
      <GlobalStyle />
      {/* 헤더 네비게이션 바 */}
      <Header />
      {/* Router 에서 지정된 컴포넌트 렌더링 */}
      <Outlet />
    </>
  );
}

export default Root;
