import { useState } from "react";
import styled from "styled-components";
import { MagnifyingGlassIcon } from "@heroicons/react/20/solid";

const SearchFormContainer = styled.form`
  position: relative;
  margin-top: 10px;
  width: 100vw;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const SearchDetailContainer = styled.div`
  background-color: ${({ theme }) => theme.colors.backgroundWhite};
  margin-top: 3px;
  width: 30%;
  height: 80px;
  border-radius: 10px;
  border: 1.5px solid ${({ theme }) => theme.colors.primaryGold};
`;

const RegionSelectContainer = styled.div`
  background-color: ${({ theme }) => theme.colors.backgroundWhite};
  margin-top: 3px;
  width: 10%;
  height: 80px;

  border-radius: 10px;
  border: 1.5px solid ${({ theme }) => theme.colors.primaryGold};
`;

const SearchInputBox = styled.div`
  width: 50%;
  height: 50px;
  border-radius: 30px;
  border: 1.5px solid ${({ theme }) => theme.colors.primaryGold};
  padding-left: 8px;
  background-color: ${({ theme }) => theme.colors.backgroundWhite};
  display: flex;
  justify-content: space-between;
  align-items: center;

  &:focus {
    outline: none;
    border-bottom: none;
    border-radius: 10px 10px 0px 0px;
  }
`;

const SearchDetailHearder = styled.div`
  width: 100%;
  height: 35px;
  border-radius: 10px;
  background-color: ${({ theme }) => theme.colors.backgroundDarkerGray};
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
  color: ${({ theme }) => theme.colors.textWhite};
  background-color: ${({ theme }) => theme.colors.primarySky};
  margin-right: 7px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const RegionSelectIcon = styled.div`
  border-left: 4px solid transparent; /* 왼쪽 투명한 테두리 */
  border-right: 4px solid transparent; /* 오른쪽 투명한 테두리 */
  border-top: 6px solid ${({ theme }) => theme.colors.primaryGold}; /* 아래쪽에 색을 채운 테두리 */
`;

const SearchInput = styled.input`
  width: 77%;
  height: 30px;
  border: none;
  outline: none;
  font-size: 14px;
`;

const SearchIcon = styled(MagnifyingGlassIcon)`
  width: 30px;
  height: 30px;
  margin-right: 12px;
  color: ${({ theme }) => theme.colors.primaryGold};
  margin-bottom: 2px;
`;

export default function SearchForm() {
  const [isSearchInputFocused, setIsSearchInputFocused] =
    useState<boolean>(false);
  const [isRegionSelectClicked, setIsRegionSelectClicked] =
    useState<boolean>(false);

  return (
    <SearchFormContainer>
      <SearchInputBox>
        <RegionSelectBtn
          onClick={() => setIsRegionSelectClicked((prev) => !prev)}
        >
          <RegionSelectSpan>KR</RegionSelectSpan>
          <RegionSelectIcon></RegionSelectIcon>
        </RegionSelectBtn>

        <SearchInput
          onFocus={() => setIsSearchInputFocused(true)}
          onBlur={() => setIsSearchInputFocused(false)}
          placeholder="소환사 이름을 입력해주세요! 소환사#태그"
        />
        <SearchIcon />
      </SearchInputBox>

      {isSearchInputFocused ? (
        <SearchDetailContainer></SearchDetailContainer>
      ) : null}
    </SearchFormContainer>
  );
}
