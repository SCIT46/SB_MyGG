import { useState } from "react";
import styled from "styled-components";
import { MagnifyingGlassIcon } from "@heroicons/react/20/solid";
import SearchDetail from "./SearchDetail";
import { useNavigate } from "react-router-dom";
import dummy from "./dummy";

const SearchFormContainer = styled.form`
  position: relative;
  margin-top: 10px;
  width: 50vw;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const RegionSelectContainer = styled.div`
  background-color: ${({ theme }) => theme.colors.background.white};
  position: absolute;
  top: 53px;
  left: 0px;
  margin-top: 3px;
  width: 60px;
  height: 80px;
  border-radius: 10px;
  border: 1.5px solid ${({ theme }) => theme.colors.brand.gold.main};
`;

const SearchInputBox = styled.div`
  background-color: ${({ theme }) => theme.colors.background.white};
  border: 1px solid ${({ theme }) => theme.colors.border.dark};
  width: 100%;
  height: 60px;
  border-radius: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
`;

const RegionSelectBtn = styled.div`
  width: 55px;
  height: 70%;
  margin-left: 6px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
`;

const RegionSelectSpan = styled.div`
  width: 50px;
  height: 30px;
  border-radius: 15px;
  font-weight: 600;
  font-size: 14px;
  color: ${({ theme }) => theme.colors.text.white};
  background-color: ${({ theme }) => theme.colors.brand.sky.main};
  margin-right: 7px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const RegionSelectIcon = styled.div`
  border-left: 4px solid transparent; /* 왼쪽 투명한 테두리 */
  border-right: 4px solid transparent; /* 오른쪽 투명한 테두리 */
  border-top: 6px solid ${({ theme }) => theme.colors.brand.gold.main}; /* 아래쪽에 색을 채운 테두리 */
`;

const SearchInput = styled.input`
  width: 80%;
  height: 40px;
  border: none;
  outline: none;
  font-size: 14px;
`;

const SearchIcon = styled(MagnifyingGlassIcon)`
  width: 28px;
  height: 28px;
  margin-right: 14px;
  color: ${({ theme }) => theme.colors.brand.gold.main};
  margin-bottom: 2px;
  cursor: pointer;
`;

export default function SearchForm() {
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);

  return (
    <SearchFormContainer>
      <SearchInputBox onClick={() => setIsModalOpen(true)}>
        quick search
      </SearchInputBox>

      {isModalOpen && <SearchDetail onClose={() => setIsModalOpen(false)} />}
    </SearchFormContainer>
  );
}
