import { Link } from "react-router-dom";
import styled from "styled-components";

const NotFoundContainer = styled.div`
  width: 100vw;
  height: 90vh;
  display: flex;
  align-items: center;
  justify-content: center;
`;

export default function NotFound() {
  return (
    <NotFoundContainer>
      <div>404 Not Found! 유저 정보를 찾을 수 없습니다.</div>
      <Link to={"/"}> {"=>"}홈페이지로 돌아가기</Link>
    </NotFoundContainer>
  );
}
