import { Outlet } from "react-router-dom";
import { GlobalStyle } from "./styles/GlobalStyle";
import Footer from "./components/Footer/Footer";
import Header from "./components/Header/Header";
import InitLoader from "./components/InitLoader";
import styled from "styled-components";

function Root() {
  return (
    <>
      <InitLoader />
      {/* 전역 css스타일 */}
      <GlobalStyle />
      {/* 헤더 네비게이션 바 */}
      <Header />
      {/* Router 에서 지정된 컴포넌트 렌더링 */}
      <Outlet />
      {/* 푸터 */}
      <Footer />
    </>
  );
}

export default Root;
