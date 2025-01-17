import { NavLink } from "react-router-dom";
import { Link } from "react-router-dom";
import styled from "styled-components";
import { useState } from "react";
import SearchDetail from "../../pages/Home/SearchDetail";

const HeaderContainer = styled.nav`
  display: flex;
  align-items: center;
  height: 80px;

  margin-left: 1rem;
`;

//헤더 링크 버튼
//hover 시 bottom border 생성 & active 시 bottom border 유지
const LinkBtn = styled(NavLink)<{ isActive?: boolean }>`
  padding: 0.75rem 0.3rem 0.75rem 0.3rem;
  margin-left: 0.2rem;
  margin-right: 0.5rem;
  margin-bottom: 0.1rem;
  font-size: 0.9rem;
  font-weight: 700;
  color: ${({ theme }) => theme.colors.text.primary};
  &:hover {
    color: ${({ theme }) => theme.colors.brand.sky.main};
  }
  &.active {
    color: ${({ theme }) => theme.colors.brand.sky.main};
  }
`;

//*이미지로 수정 필요
const LogoImg = styled.div`
  height: 3rem;
  width: 4%.5;
  display: flex;
  justify-content: center;
  align-items: center;
  color: ${({ theme }) => theme.colors.text.primary};
  font-weight: 800;
  margin-left: 0.2rem;
  margin-right: 1.3rem;
  font-size: 1.5rem;
  text-shadow: 0.5px 0.5px 1px rgba(0, 0, 0, 0.1);
  margin-left: 14px;
`;

const SearchBtn = styled.div`
  padding: 0.75rem 0.3rem 0.75rem 0.3rem;
  margin-left: 0.175rem;
  margin-right: 0.6rem;
  margin-bottom: 0.135rem;
  font-size: 0.9rem;
  font-weight: 700;
  cursor: pointer;
  color: ${({ theme }) => theme.colors.text.secondary};
  &:hover {
    color: ${({ theme }) => theme.colors.brand.sky.main};
  }
  &.active {
    color: ${({ theme }) => theme.colors.brand.sky.main};
  }
`;

const LinkBox = styled.div`
  display: flex;
  align-items: center;
  margin-left: auto;
  margin-right: 1rem;
`;

//헤더 컴포넌트
export default function Header() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  return (
    <HeaderContainer>
      {/* Link 컴포넌트를 통해 라우트 이동 */}
      <Link to={"/"}>
        <LogoImg>
          <div>MY.GG</div>
        </LogoImg>
      </Link>
      <LinkBox>
        <LinkBtn to={"item"}>아이템</LinkBtn>
        <LinkBtn to={"champion"}>챔피언</LinkBtn>
        <LinkBtn to={"board"}>익명게시판</LinkBtn>
        <LinkBtn to={"login"}>로그인</LinkBtn>
        <SearchBtn onClick={() => setIsModalOpen(true)}>검색</SearchBtn>
      </LinkBox>
      {/* 패치노트 컴포넌트 */}
      {isModalOpen && <SearchDetail onClose={() => setIsModalOpen(false)} />}
    </HeaderContainer>
  );
}
