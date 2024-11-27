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
  background-color: ${({ theme }) => theme.colors.backgroundWhite};
  position: absolute;
  top: 53px;
  left: 0px;
  margin-top: 3px;
  width: 60px;
  height: 80px;
  border-radius: 10px;
  border: 1.5px solid ${({ theme }) => theme.colors.primaryGold};
`;

const SearchInputBox = styled.div`
  width: 100%;
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
  height: 40px;
  border: none;
  outline: none;
  font-size: 14px;
  margin-left: 5px;
`;

const SearchIcon = styled(MagnifyingGlassIcon)`
  width: 28px;
  height: 28px;
  margin-right: 14px;
  color: ${({ theme }) => theme.colors.primaryGold};
  margin-bottom: 2px;
  cursor: pointer;
`;

interface IUserSearchInput {
  userName: string;
  tagLine: string;
}

export default function SearchForm() {
  const navigate = useNavigate();
  const [isSearchInputFocused, setIsSearchInputFocused] =
    useState<boolean>(false);
  const [isRegionSelectClicked, setIsRegionSelectClicked] =
    useState<boolean>(false);
  const [userSearchInput, setUserSearchInput] = useState<IUserSearchInput>({
    userName: "",
    tagLine: "",
  });

  const handleSearchInputChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    const [userName, tagLine = ""] = event.target.value.split("#");

    setUserSearchInput({
      userName: userName,
      tagLine: tagLine,
    });
  };

  const handleSearch = () => {
    const { userName, tagLine } = userSearchInput;
    if (userName.trim() !== "") {
      navigate(`/search/${userName}/${tagLine}`);
    } else {
    }
  };

  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter") {
      event.preventDefault();
      handleSearch();
    }
  };

  return (
    <SearchFormContainer>
      <SearchInputBox>
        <RegionSelectBtn
          onClick={() => {
            setIsRegionSelectClicked((prev) => !prev);
            setIsSearchInputFocused(false);
          }}
        >
          <RegionSelectSpan>KR</RegionSelectSpan>
          <RegionSelectIcon></RegionSelectIcon>
        </RegionSelectBtn>

        <SearchInput
          onChange={handleSearchInputChange}
          onFocus={() => {
            setIsSearchInputFocused(true);
            setIsRegionSelectClicked(false);
          }}
          onBlur={() => setIsSearchInputFocused(false)}
          onKeyDown={handleKeyDown}
          placeholder="소환사 이름을 입력해주세요! 소환사#태그"
        />
        <SearchIcon onClick={handleSearch} />
      </SearchInputBox>
      {isRegionSelectClicked ? (
        <RegionSelectContainer></RegionSelectContainer>
      ) : null}
      {isSearchInputFocused ? <SearchDetail searchedUser={dummy} /> : null}
    </SearchFormContainer>
  );
}
