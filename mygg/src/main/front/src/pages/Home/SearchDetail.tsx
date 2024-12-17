import styled from "styled-components";
import dummy from "./dummy";
import { useNavigate } from "react-router-dom";
import { useState, useEffect, useRef } from "react";

const SearchDetailContainer = styled.div`
  background-color: ${({ theme }) => theme.colors.background.white};
  margin-top: 3px;
  margin-left: 30px;
  width: 80%;
  height: 400px;
  border-radius: 10px;
  border: 1.5px solid ${({ theme }) => theme.colors.brand.gold.main};
  display: flex;
  flex-direction: column;
  align-items: center;
  overflow-y: auto;
`;

const Line = styled.div`
  width: 97%;
  height: 1px;
  margin-top: 6px;
  margin-bottom: 4px;
  background-color: ${({ theme }) => theme.colors.brand.gold.main};
`;

const SearchDetailTitle = styled.div`
  font-size: 14px;
  color: ${({ theme }) => theme.colors.brand.gold.main};
  font-weight: 600;
  margin-top: 10px;
  margin-left: 12px;
  margin-right: auto;
`;

const SearchedUsersContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 95%;
  &:last-child {
    margin-bottom: 4px;
  }
`;

const SearchedUserBox = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 4px;
  margin-left: 3px;
  padding: 6px 0px 6px 0px;
  border-radius: 10px;
  cursor: pointer;
  &:hover {
    background-color: ${({ theme }) => theme.colors.brand.sky.light};
  }
`;

const SearchedUserProfileImg = styled.div`
  background-color: #b7b7b7;
  width: 30px;
  height: 30px;
  border-radius: 100%;
`;

const SearchedUserName = styled.div`
  font-size: 14px;
  margin-left: 10px;
  color: ${({ theme }) => theme.colors.text.primary};
`;

const SearchedUserTag = styled.div`
  font-size: 14px;
  margin-left: 5px;
  color: ${({ theme }) => theme.colors.text.primary};
  font-weight: 600;
`;

const SearchInput = styled.input`
  width: 90%;
  height: 30px;
  margin: 10px 0;
  padding: 5px;
  border: 1px solid ${({ theme }) => theme.colors.border.dark};
  border-radius: 5px;
  outline: none;
  font-size: 14px;
`;

const ActiveSearchedUserBox = styled(SearchedUserBox)`
  background-color: lightblue;
`;

interface IUser {
  gameName: string;
  tagLine: string;
  imageId: string;
  lever: number;
}

interface ISearchDeailProps {
  onClose: () => void;
}

const ModalOverlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
`;

export default function SearchDetail({ onClose }: ISearchDeailProps) {
  const navigate = useNavigate();
  const [searchQuery, setSearchQuery] = useState<string>("");
  const [suggestions, setSuggestions] = useState<IUser[]>([]);
  const [activeSuggestionIndex, setActiveSuggestionIndex] =
    useState<number>(-1);
  const [isComposing, setIsComposing] = useState<boolean>(false);

  const inputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    if (inputRef.current) {
      inputRef.current.focus();
    }
  }, []);

  const handleSearchInputChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    const query = event.target.value;
    setSearchQuery(query);

    if (query) {
      const filteredSuggestions = dummy.filter((user) =>
        user.gameName.toLowerCase().includes(query.toLowerCase())
      );
      setSuggestions(filteredSuggestions);
      setActiveSuggestionIndex(filteredSuggestions.length > 0 ? 0 : -1);
    } else {
      setSuggestions([]);
      setActiveSuggestionIndex(-1);
    }
  };

  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    // 한글 입력 조합 중일 때는 방향키나 엔터키로 인덱스 변경을 하지 않음
    if (isComposing) return;

    if (event.key === "Escape") {
      onClose();
    } else if (event.key === "ArrowDown") {
      event.preventDefault();
      setActiveSuggestionIndex((prevIndex) =>
        prevIndex < suggestions.length - 1 ? prevIndex + 1 : 0
      );
    } else if (event.key === "ArrowUp") {
      event.preventDefault();
      setActiveSuggestionIndex((prevIndex) =>
        prevIndex > 0 ? prevIndex - 1 : suggestions.length - 1
      );
    } else if (event.key === "Enter") {
      event.preventDefault();
      handleSearch();
    }
  };

  const handleCompositionStart = () => {
    setIsComposing(true);
  };

  const handleCompositionEnd = () => {
    setIsComposing(false);
  };

  const handleSuggestionClick = (user: IUser) => {
    setSearchQuery(`${user.gameName}#${user.tagLine}`);
    setSuggestions([]);
    setActiveSuggestionIndex(-1);
    navigate(`/search/${user.gameName}-${user.tagLine}`);
  };

  const handleSearch = () => {
    if (
      activeSuggestionIndex >= 0 &&
      activeSuggestionIndex < suggestions.length
    ) {
      const selectedUser = suggestions[activeSuggestionIndex];
      const userName = selectedUser.gameName;
      const tagLine = selectedUser.tagLine;

      if (userName.trim() !== "") {
        navigate(`/search/${userName}-${tagLine}`);
      }
    } else {
      console.error("No valid suggestion selected");
    }
  };

  return (
    <ModalOverlay onClick={onClose}>
      <SearchDetailContainer onClick={(e) => e.stopPropagation()}>
        <SearchDetailTitle>소환사</SearchDetailTitle>
        <SearchInput
          ref={inputRef}
          type="text"
          placeholder="Search by game name"
          value={searchQuery}
          onChange={handleSearchInputChange}
          onCompositionStart={handleCompositionStart}
          onCompositionEnd={handleCompositionEnd}
          onKeyDown={handleKeyDown}
        />
        {suggestions.length > 0 && (
          <SearchedUsersContainer>
            {suggestions.map((user, index) => (
              <div key={index} onClick={() => handleSuggestionClick(user)}>
                {index === activeSuggestionIndex ? (
                  <ActiveSearchedUserBox>
                    <SearchedUserProfileImg />
                    <SearchedUserName>{user.gameName}</SearchedUserName>
                    <SearchedUserTag>#{user.tagLine}</SearchedUserTag>
                  </ActiveSearchedUserBox>
                ) : (
                  <SearchedUserBox>
                    <SearchedUserProfileImg />
                    <SearchedUserName>{user.gameName}</SearchedUserName>
                    <SearchedUserTag>#{user.tagLine}</SearchedUserTag>
                  </SearchedUserBox>
                )}
              </div>
            ))}
          </SearchedUsersContainer>
        )}
        <Line />
      </SearchDetailContainer>
    </ModalOverlay>
  );
}
