import { useState } from "react";
import styled from "styled-components";
import { MagnifyingGlassIcon } from "@heroicons/react/20/solid";
import SearchDetail from "./SearchDetail";

const SearchFormContainer = styled.form`
  position: relative;
  margin-top: 0.625rem;
  width: 55vw;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  @media (max-width: 600px) {
    width: 70vw;
  }
`;

const SearchInputBox = styled.div`
  background-color: ${({ theme }) => theme.colors.background.white};
  border: 1px solid ${({ theme }) => theme.colors.border.main};
  width: 100%;
  height: 3.5rem;
  border-radius: 0.625rem;
  display: flex;
  align-items: center;

  cursor: pointer;
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
`;

const SearchInputSpan = styled.span`
  font-size: 0.9rem;
  color: ${({ theme }) => theme.colors.text.light};
  font-weight: 600;
`;

const SearchIcon = styled(MagnifyingGlassIcon)`
  width: 28px;
  height: 28px;
  margin-right: 14px;
  color: ${({ theme }) => theme.colors.border.dark};
  margin-bottom: 2px;
  margin-left: 16px;
  cursor: pointer;
`;

export default function SearchForm() {
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);

  return (
    <SearchFormContainer>
      <SearchInputBox onClick={() => setIsModalOpen(true)}>
        <SearchIcon />
        <SearchInputSpan>quick search...</SearchInputSpan>
      </SearchInputBox>

      {isModalOpen && <SearchDetail onClose={() => setIsModalOpen(false)} />}
    </SearchFormContainer>
  );
}
