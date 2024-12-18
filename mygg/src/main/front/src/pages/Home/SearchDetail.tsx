import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import { useState, useEffect, useRef } from "react";
import { useDebounce } from "../../hooks/useDebounce";
import { getSearchedResult } from "../../services/Api";
import useCurrentVersionStore from "../../stores/useCurrentVersionStore";

export interface ISuggestion {
  champion: IChampionSuggestion[];
  item: IItemSuggestion[];
  user: IUserSuggestion[];
}

interface IChampionSuggestion {
  name: string;
  id: string;
}

interface IItemSuggestion {
  name: string;
  id: string;
}

interface IUserSuggestion {
  gameName: string;
  profileIconId: number;
  tagLine: string;
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

const SearchedUserProfileImg = styled.img`
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

export default function SearchDetail({ onClose }: ISearchDeailProps) {
  const navigate = useNavigate();

  const version = useCurrentVersionStore((state) => state.version);

  const [searchQuery, setSearchQuery] = useState<string>("");
  const [suggestions, setSuggestions] = useState<ISuggestion | undefined>();
  const [activeSuggestionIndex, setActiveSuggestionIndex] =
    useState<number>(-1);
  const [isComposing, setIsComposing] = useState<boolean>(false);

  const debouncedQuery = useDebounce(searchQuery, 300);
  const inputRef = useRef<HTMLInputElement>(null);

  const totalUser = suggestions?.user?.length || 0;
  const totalChampion = suggestions?.champion?.length || 0;
  const totalItem = suggestions?.item?.length || 0;
  const totalLength = totalUser + totalChampion + totalItem;

  useEffect(() => {
    if (inputRef.current) {
      inputRef.current.focus();
    }
  }, []);

  useEffect(() => {
    if (debouncedQuery) {
      const fetchSuggestions = async () => {
        try {
          const suggestion: ISuggestion = await getSearchedResult(
            debouncedQuery
          );
          setSuggestions(suggestion);
        } catch (error) {
          console.error("Error fetching suggestions:", error);
        }
      };
      fetchSuggestions();
    } else {
      setSuggestions(undefined);
      setActiveSuggestionIndex(-1);
    }
  }, [debouncedQuery]);

  const handleSearchInputChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    const query = event.target.value;
    setSearchQuery(query);
  };

  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (isComposing) return;

    if (event.key === "Escape") {
      onClose();
    } else if (event.key === "ArrowDown") {
      event.preventDefault();
      if (totalLength > 0) {
        setActiveSuggestionIndex((prevIndex) => (prevIndex + 1) % totalLength);
      }
    } else if (event.key === "ArrowUp") {
      event.preventDefault();
      if (totalLength > 0) {
        setActiveSuggestionIndex(
          (prevIndex) => (prevIndex - 1 + totalLength) % totalLength
        );
      }
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

  const handleSuggestionClick = (type: string, suggestion: any) => {
    if (type === "user") {
      setSearchQuery(`${suggestion.gameName}#${suggestion.tagLine}`);
      navigate(`/search/${suggestion.gameName}-${suggestion.tagLine}`);
    } else if (type === "champion") {
      setSearchQuery(suggestion.name);
      navigate(`/champion/${suggestion.id}`);
    } else if (type === "item") {
      setSearchQuery(suggestion.name);
      navigate(`/item/${suggestion.id}`);
    }
    setSuggestions(undefined);
    setActiveSuggestionIndex(-1);
  };

  const handleSearch = () => {
    if (suggestions && totalLength > 0 && activeSuggestionIndex >= 0) {
      // activeSuggestionIndex에 따라 어떤 타입의 suggestion인지 확인
      if (activeSuggestionIndex < totalUser) {
        // user 영역
        const user = suggestions.user[activeSuggestionIndex];
        navigate(`/search/${user.gameName}-${user.tagLine}`);
      } else if (activeSuggestionIndex < totalUser + totalChampion) {
        // champion 영역
        const champIndex = activeSuggestionIndex - totalUser;
        const champ = suggestions.champion[champIndex];
        navigate(`/champion/${champ.id}`);
      } else {
        // item 영역
        const itemIndex = activeSuggestionIndex - totalUser - totalChampion;
        const item = suggestions.item[itemIndex];
        navigate(`/item/${item.id}`);
      }
    } else {
      const searchTerms = searchQuery.split("#");
      if (searchTerms.length === 2) {
        const [gameName, tagLine] = searchTerms;
        navigate(`/search/${gameName}-${tagLine}`);
      } else {
        console.error("Invalid search format. Please use 'gameName#tagLine'");
      }
    }
  };

  const renderSuggestions = () => {
    if (!suggestions) return null;

    const elements: JSX.Element[] = [];

    // user suggestions
    suggestions.user?.forEach((user, index) => {
      const isActive = index === activeSuggestionIndex;
      const Box = isActive ? ActiveSearchedUserBox : SearchedUserBox;
      elements.push(
        <div
          key={`user-${index}`}
          onClick={() => handleSuggestionClick("user", user)}
        >
          <Box>
            <SearchedUserProfileImg
              src={`https://ddragon.leagueoflegends.com/cdn/${version}/img/profileicon/${user.profileIconId}.png`}
            />
            <SearchedUserName>{user.gameName}</SearchedUserName>
            <SearchedUserTag>#{user.tagLine}</SearchedUserTag>
          </Box>
        </div>
      );
    });

    // champion suggestions
    suggestions.champion?.forEach((champ, index) => {
      const currentIndex = totalUser + index;
      const isActive = currentIndex === activeSuggestionIndex;
      const Box = isActive ? ActiveSearchedUserBox : SearchedUserBox;
      elements.push(
        <div
          key={`champion-${index}`}
          onClick={() => handleSuggestionClick("champion", champ)}
        >
          <Box>
            <SearchedUserProfileImg
              src={`https://ddragon.leagueoflegends.com/cdn/${version}/img/champion/${champ.id}.png`}
            />
            <SearchedUserName>{champ.name}</SearchedUserName>
          </Box>
        </div>
      );
    });

    // item suggestions
    suggestions.item?.forEach((item, index) => {
      const currentIndex = totalUser + totalChampion + index;
      const isActive = currentIndex === activeSuggestionIndex;
      const Box = isActive ? ActiveSearchedUserBox : SearchedUserBox;
      elements.push(
        <div
          key={`item-${index}`}
          onClick={() => handleSuggestionClick("item", item)}
        >
          <Box>
            <SearchedUserProfileImg
              src={`https://ddragon.leagueoflegends.com/cdn/${version}/img/item/${item.id}.png`}
            />
            <SearchedUserName>{item.name}</SearchedUserName>
          </Box>
        </div>
      );
    });

    return elements;
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
        {totalLength > 0 && (
          <SearchedUsersContainer>{renderSuggestions()}</SearchedUsersContainer>
        )}
        <Line />
      </SearchDetailContainer>
    </ModalOverlay>
  );
}
