import { NavLink } from "react-router-dom";
import { Link } from "react-router-dom";
import styled from "styled-components";
import PatchNote from "./PatchNote";
import { useState } from "react";
import SearchForm from "../../pages/Home/SearchForm";
import SearchDetail from "../../pages/Home/SearchDetail";
import { MagnifyingGlassIcon } from "@heroicons/react/20/solid";

const HeaderContainer = styled.nav`
  display: flex;
  align-items: center;
  height: 80px;
  margin-left: 10px;
`;

//헤더 링크 버튼
//hover 시 bottom border 생성 & active 시 bottom border 유지
const LinkBtn = styled(NavLink)<{ isActive?: boolean }>`
  padding: 12px 5px 12px 5px;
  margin-left: 7px;
  margin-right: 7px;
  margin-bottom: 2px;
  font-size: 14px;
  font-weight: 700;
  color: ${({ theme }) => theme.colors.text.secondary};
  &:hover {
    color: ${({ theme }) => theme.colors.brand.sky.main};
  }
  &.active {
    color: ${({ theme }) => theme.colors.brand.sky.main};
  }
`;

//*이미지로 수정 필요
const LogoImg = styled.div`
  height: 50px;
  width: 80px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: ${({ theme }) => theme.colors.text.primary};
  font-weight: 800;
  margin-left: 5px;
  margin-right: 20px;
  font-size: 24px;
  text-shadow: 0.5px 0.5px 1px rgba(0, 0, 0, 0.1);
  margin-left: 14px;
`;

const SearchInputBox = styled.div`
  background-color: ${({ theme }) => theme.colors.background.white};
  border: 1px solid ${({ theme }) => theme.colors.border.dark};
  width: 100px;
  height: 40px;
  margin-right: 20px;
  margin-left: auto;
  border-radius: 5px;
  display: flex;
  align-items: center;

  cursor: pointer;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);

  font-size: 14px;

  color: ${({ theme }) => theme.colors.text.light};
`;

const SearchIcon = styled(MagnifyingGlassIcon)`
  width: 20px;
  height: 20px;
  color: ${({ theme }) => theme.colors.border.dark};
  margin-bottom: 2px;
  margin-left: 8px;
  margin-right: 8px;
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
      <LinkBtn to={"item"}>아이템</LinkBtn>
      <LinkBtn to={"champion"}>챔피언</LinkBtn>
      <LinkBtn to={"search/Happy-day12"}>플레이어</LinkBtn>
      <LinkBtn to={"test"}>테스트</LinkBtn>
      {/* 패치노트 컴포넌트 */}

      <SearchInputBox onClick={() => setIsModalOpen(true)}>
        <SearchIcon />
        search...
      </SearchInputBox>
      {isModalOpen && <SearchDetail onClose={() => setIsModalOpen(false)} />}
    </HeaderContainer>
  );
}
