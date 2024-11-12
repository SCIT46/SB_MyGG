import { NavLink } from "react-router-dom";
import { Link } from "react-router-dom";
import styled from "styled-components";

const HeaderContainer = styled.nav`
  display: flex;
  align-items: center;
  width: 100vw;
  height: 50px;
  background-color: ${({ theme }) => theme.colors.primary};
  box-shadow: 0px 4px 4px -2px rgba(0, 0, 0, 0.2);
`;

const LinkBtn = styled(NavLink)<{ isActive?: boolean }>`
  padding: 12px;
  margin-left: 5px;
  margin-right: 5px;
  margin-bottom: 2px;
  border-radius: 10px;
  color: ${({ theme }) => theme.colors.lightText};
  transition: color 0.15s ease;
  &:hover {
    color: ${({ theme }) => theme.colors.text};
    background-color: ${({ theme }) => theme.colors.secondary};
  }
  &.active {
    color: white;
  }
`;

//이미지로 수정 필요
const LogoImg = styled.div`
  height: 50px;
  width: 80px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: ${({ theme }) => theme.colors.text};
  font-weight: 800;
  margin-left: 5px;
  font-size: 18px;
`;

//헤더 컴포넌트
export default function Header() {
  return (
    <HeaderContainer>
      {/* Link 컴포넌트를 통해 라우트 이동 */}
      <Link to={"/"}>
        <LogoImg>
          <div>MY.GG</div>
        </LogoImg>
      </Link>
      <LinkBtn to={"item"}>아이템</LinkBtn>
      <LinkBtn to={"champion"}>챔피언</LinkBtn>
      <LinkBtn to={"search/123"}>플레이어</LinkBtn>
      <LinkBtn to={"test"}>테스트</LinkBtn>
    </HeaderContainer>
  );
}
