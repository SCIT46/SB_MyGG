import { NavLink } from "react-router-dom";
import { Link } from "react-router-dom";
import styled from "styled-components";
import PatchNote from "./PatchNote";

const HeaderContainer = styled.nav`
  display: flex;
  align-items: center;
  width: 100vw;
  height: 50px;
  background-color: ${({ theme }) => theme.colors.backgroundWhite};
  box-shadow: 0px 4px 4px -2px rgba(0, 0, 0, 0.2);
`;

//헤더 링크 버튼
//hover 시 bottom border 생성 & active 시 bottom border 유지
const LinkBtn = styled(NavLink)<{ isActive?: boolean }>`
  padding: 12px 5px 12px 5px;
  margin-left: 7px;
  margin-right: 7px;
  margin-bottom: 2px;
  font-size: 14px;
  font-weight: 600;
  color: ${({ theme }) => theme.colors.textBlack};
  &:hover {
    border-bottom: 3px solid ${({ theme }) => theme.colors.primarySky};
  }
  &.active {
    border-bottom: 3px solid ${({ theme }) => theme.colors.primarySky};
  }
`;

//*이미지로 수정 필요
const LogoImg = styled.div`
  height: 50px;
  width: 80px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: ${({ theme }) => theme.colors.primaryGold};
  font-weight: 800;
  margin-left: 5px;
  margin-right: 20px;
  font-size: 18px;
  text-shadow: 0.5px 0.5px 1px rgba(0, 0, 0, 0.1);
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
      {/* 패치노트 컴포넌트 */}
      <PatchNote />
    </HeaderContainer>
  );
}
