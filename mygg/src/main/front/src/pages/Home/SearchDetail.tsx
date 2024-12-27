import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import { useState, useEffect, useRef } from "react";
import { useDebounce } from "../../hooks/useDebounce";
import { getSearchedResult } from "../../services/Api";
import useCurrentVersionStore from "../../stores/useCurrentVersionStore";
import { MagnifyingGlassIcon } from "@heroicons/react/20/solid";
import LoadingSpinner from "../../components/LoadingSpinner";
import useRecentSearchStore from "../../stores/useRecentSearch";

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
  background-color: ${({ theme }) => theme.colors.background.overlay};
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  backdrop-filter: blur(1.5px);
  z-index: 1000;
`;

const SearchDetailContainer = styled.div`
  background-color: ${({ theme }) => theme.colors.background.white};
  width: 60%; //전체 창 너비
  height: 20rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  overflow-y: auto;
  border-radius: 0 0 10px 10px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2), 0 4px 8px rgba(0, 0, 0, 0.1);

  @media (max-width: ${({ theme }) => theme.breakpoints.mobile}) {
    width: 85%; // 스마트폰 사이즈일 때
  }
`;

const SearchDetailTitle = styled.div`
  font-size: 14px;
  color: ${({ theme }) => theme.colors.text.light};
  font-weight: 600;
  margin-top: 16px;
  margin-left: 16px;
  margin-right: auto;
  user-select: none;
`;

const SearchedUsersContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 10px;
  width: 97%;
  &:last-child {
    margin-bottom: 4px;
  }
`;

const SearchedUserBox = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  margin-top: 4px;
  margin-bottom: 4px;
  padding: 8px 4px 8px 4px;
  border-radius: 10px;
  cursor: pointer;
  &:hover {
    background-color: ${({ theme }) => theme.colors.background.primary};
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
  height: 2.5rem;
  width: 95%;
  border: none;
  font-size: 16px;
  color: ${({ theme }) => theme.colors.text.primary};
  padding: 5px;
  margin: 4px 0;
  border-radius: 5px;
  outline: none;

  &::placeholder {
    color: ${({ theme }) => theme.colors.text.light};
  }
`;

const ActiveSearchedUserBox = styled(SearchedUserBox)`
  background-color: ${({ theme }) =>
    theme.colors.background.secondary} !important;
`;

const SearchFormContainer = styled.div`
  background-color: ${({ theme }) => theme.colors.background.white};
  display: flex;
  align-items: center;
  justify-content: center;
  width: 60%;
  border-radius: 10px 10px 0 0;
  border-bottom: 1px solid ${({ theme }) => theme.colors.border.light};
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3), 0 3px 6px rgba(0, 0, 0, 0.15);

  @media (max-width: ${({ theme }) => theme.breakpoints.mobile}) {
    width: 85%; // 스마트폰 사이즈일 때
  }
`;

const SearchedUserDetail = styled.div`
  font-size: 14px;
  color: ${({ theme }) => theme.colors.text.light};
  font-weight: 400;
  margin-left: auto;
`;

const BoxContainer = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
`;

const SearchedRecentContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100%;
`;

const SearchedRecentTitle = styled.div`
  font-size: 14px;
  color: ${({ theme }) => theme.colors.text.light};
  font-weight: 600;
  margin-top: 16px;
  margin-left: 16px;
  margin-right: auto;
  user-select: none;
`;

const SearchIcon = styled(MagnifyingGlassIcon)`
  width: 1.5rem;
  height: 1.5rem;
  margin-right: 0.6rem;
  color: ${({ theme }) => theme.colors.border.dark};
  margin-bottom: 0.1rem;
  margin-left: 1.15rem;
  cursor: pointer;
`;

const SearchNotFound = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  margin-bottom: 20px;
`;

const SearchNotFoundText = styled.div`
  font-size: 20px;
  color: ${({ theme }) => theme.colors.text.light};
  font-weight: 400;
`;

const SearchNotFoundInfoText = styled.span`
  color: ${({ theme }) => theme.colors.brand.sky.dark};
  font-weight: 600;
`;

const ExitButton = styled.div`
  margin: 16px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  width: 40px;
  height: 25px;
  background-color: rgba(0, 0, 0, 0);
  font-size: 12px;
  font-weight: 600;
  color: ${({ theme }) => theme.colors.text.light};
  border: 1.5px solid ${({ theme }) => theme.colors.background.secondary};
  border-radius: 5px;
  transition: all 0.1s ease;
  &:hover {
    border: 1.5px solid ${({ theme }) => theme.colors.brand.sky.main};
  }
`;

export default function SearchDetail({ onClose }: ISearchDeailProps) {
  const navigate = useNavigate();

  const version = useCurrentVersionStore((state) => state.version);

  const [searchQuery, setSearchQuery] = useState<string>("");
  const [suggestions, setSuggestions] = useState<ISuggestion | undefined>();
  const [activeSuggestionIndex, setActiveSuggestionIndex] =
    useState<number>(-1);
  const [isComposing, setIsComposing] = useState<boolean>(false);
  const [isQueryLoading, setIsQueryLoading] = useState<boolean>(false);
  const [activeRecentIndex, setActiveRecentIndex] = useState<number>(-1);

  const recentSearch = useRecentSearchStore((state) => state.recentSearch);
  const setRecentSearch = useRecentSearchStore((state) => state.setRecentSearch);
  const debouncedQuery = useDebounce(searchQuery, 300);
  const inputRef = useRef<HTMLInputElement>(null);

  const totalUser = suggestions?.user?.length || 0;
  const totalChampion = suggestions?.champion?.length || 0;
  const totalItem = suggestions?.item?.length || 0;
  const totalLength = totalUser + totalChampion + totalItem;

  const suggestionRefs = useRef<(HTMLDivElement | null)[]>([]);



  useEffect(() => {
    if (inputRef.current) {
      inputRef.current.focus();
    }
  }, []);

  useEffect(() => {
    if (debouncedQuery) {
      const fetchSuggestions = async () => {
        setIsQueryLoading(true);
        try {
          const suggestion: ISuggestion = await getSearchedResult(
            encodeURIComponent(debouncedQuery)
          );
          setSuggestions(suggestion);
          console.log(suggestion);

          if (
            suggestion.user.length > 0 ||
            suggestion.champion.length > 0 ||
            suggestion.item.length > 0
          ) {
            setActiveSuggestionIndex(0);
          }
        } catch (error) {
          console.error("Error fetching suggestions:", error);
        } finally {
          setIsQueryLoading(false);
        }
      };
      fetchSuggestions();
    } else {
      setSuggestions(undefined);
      setActiveSuggestionIndex(-1);
    }
  }, [debouncedQuery]);

  useEffect(() => {
    if (
      activeSuggestionIndex >= 0 &&
      suggestionRefs.current[activeSuggestionIndex]
    ) {
      suggestionRefs.current[activeSuggestionIndex]?.scrollIntoView({
        behavior: "auto",
        block: "nearest",
      });
    }
  }, [activeSuggestionIndex]);

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
      } else if (recentSearch.length > 0) {
        setActiveRecentIndex((prevIndex) => (prevIndex + 1) % recentSearch.length);
      }
    } else if (event.key === "ArrowUp") {
      event.preventDefault();
      if (totalLength > 0) {
        setActiveSuggestionIndex(
          (prevIndex) => (prevIndex - 1 + totalLength) % totalLength
        );
      } else if (recentSearch.length > 0) {
        setActiveRecentIndex(
          (prevIndex) => (prevIndex - 1 + recentSearch.length) % recentSearch.length
        );
      }
    } else if (event.key === "Enter") {
      event.preventDefault();
      if (activeSuggestionIndex >= 0) {
        handleSearch();
      } else if (activeRecentIndex >= 0) {
        handleSuggestionClick("user", recentSearch[activeRecentIndex]);
      }
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

      const recentSearchArr = [
        ...recentSearch,
        { gameName: suggestion.gameName, tagLine: suggestion.tagLine, profileIconId: suggestion.profileIconId },
      ];
      setRecentSearch(recentSearchArr);
      navigate(`/search/${suggestion.gameName}-${suggestion.tagLine}`);
    } else if (type === "champion") {
      setSearchQuery(suggestion.name);
      navigate(`/champion/${suggestion.id}`);
    } else if (type === "item") {
      setSearchQuery(suggestion.name);
      navigate(`/item/${suggestion.id}`);
    }
    setSuggestions(undefined);
    onClose();
    setActiveSuggestionIndex(-1);
  };

  const handleSearch = () => {
    if (suggestions && totalLength > 0 && activeSuggestionIndex >= 0) {
      // activeSuggestionIndex에 따라 어떤 타입의 suggestion인지 확인
      if (activeSuggestionIndex < totalUser) {
        // user 영역
        const user = suggestions.user[activeSuggestionIndex];
        const recentSearchArr = [
          ...recentSearch,
          { gameName: user.gameName, tagLine: user.tagLine, profileIconId: user.profileIconId },
        ];
        
        setRecentSearch(recentSearchArr);
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
    onClose();
  };

  const renderSuggestions = () => {
    if (!suggestions) return null;

    const elements: JSX.Element[] = [];

    // user suggestions
    suggestions.user?.forEach((user, index) => {
      const isActive = index === activeSuggestionIndex;
      const Box = isActive ? ActiveSearchedUserBox : SearchedUserBox;
      elements.push(
        <BoxContainer
          key={`user-${index}`}
          ref={(el) => (suggestionRefs.current[index] = el)}
          onClick={() => handleSuggestionClick("user", user)}
        >
          <Box>
            <SearchedUserProfileImg
              src={`https://ddragon.leagueoflegends.com/cdn/${version}/img/profileicon/${user.profileIconId}.png`}
            />
            <SearchedUserName>{user.gameName}</SearchedUserName>
            <SearchedUserTag>#{user.tagLine}</SearchedUserTag>
            <SearchedUserDetail>유저</SearchedUserDetail>
          </Box>
        </BoxContainer>
      );
    });

    // champion suggestions
    suggestions.champion?.forEach((champ, index) => {
      const currentIndex = totalUser + index;
      const isActive = currentIndex === activeSuggestionIndex;
      const Box = isActive ? ActiveSearchedUserBox : SearchedUserBox;
      elements.push(
        <BoxContainer
          key={`champion-${index}`}
          ref={(el) => (suggestionRefs.current[currentIndex] = el)}
          onClick={() => handleSuggestionClick("champion", champ)}
        >
          <Box>
            <SearchedUserProfileImg
              src={`https://ddragon.leagueoflegends.com/cdn/${version}/img/champion/${champ.id}.png`}
            />
            <SearchedUserName>{champ.name}</SearchedUserName>
            <SearchedUserDetail>챔피언</SearchedUserDetail>
          </Box>
        </BoxContainer>
      );
    });

    // item suggestions
    suggestions.item?.forEach((item, index) => {
      const currentIndex = totalUser + totalChampion + index;
      const isActive = currentIndex === activeSuggestionIndex;
      const Box = isActive ? ActiveSearchedUserBox : SearchedUserBox;
      elements.push(
        <BoxContainer
          key={`item-${index}`}
          ref={(el) => (suggestionRefs.current[currentIndex] = el)}
          onClick={() => handleSuggestionClick("item", item)}
        >
          <Box>
            <SearchedUserProfileImg
              src={`https://ddragon.leagueoflegends.com/cdn/${version}/img/item/${item.id}.png`}
            />
            <SearchedUserName>{item.name}</SearchedUserName>
            <SearchedUserDetail>아이템</SearchedUserDetail>
          </Box>
        </BoxContainer>
      );
    });

    return elements;
  };

  const renderRecentSearches = () => {
    if (!recentSearch || recentSearch.length === 0) return null;

    return recentSearch.map((search, index) => {
      const isActive = index === activeRecentIndex;
      const Box = isActive ? ActiveSearchedUserBox : SearchedUserBox;
      return (
        <BoxContainer
          key={`recent-${index}`}
          onClick={() => handleSuggestionClick("user", search)}
        >
          <Box>
            <SearchedUserProfileImg
              src={`https://ddragon.leagueoflegends.com/cdn/${version}/img/profileicon/${search.profileIconId}.png`}
            />
            <SearchedUserName>{search.gameName}</SearchedUserName>
            <SearchedUserTag>#{search.tagLine}</SearchedUserTag>
            <SearchedUserDetail>유저</SearchedUserDetail>
          </Box>
        </BoxContainer>
      );
    });
  };

  return (
    <ModalOverlay onClick={onClose}>
      <SearchFormContainer onClick={(e) => e.stopPropagation()}>
        {isQueryLoading ? <LoadingSpinner /> : <SearchIcon />}
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
        <ExitButton onClick={onClose}>ESC</ExitButton>
      </SearchFormContainer>
      <SearchDetailContainer onClick={(e) => e.stopPropagation()}>
        {searchQuery && totalLength > 0 ? (
          <>
            <SearchDetailTitle>검색 결과</SearchDetailTitle>
            <SearchedUsersContainer>
              {renderSuggestions()}
            </SearchedUsersContainer>
          </>
        ) : debouncedQuery && totalLength === 0 && !isQueryLoading ? (
          <SearchedRecentContainer>
            <SearchNotFound>
              <SearchNotFoundText>
                '
                <SearchNotFoundInfoText>
                  {debouncedQuery}
                </SearchNotFoundInfoText>{" "}
                '의 검색결과가 없습니다
              </SearchNotFoundText>
            </SearchNotFound>
          </SearchedRecentContainer>
        ) : !searchQuery ? (
          <SearchedRecentContainer>
            <SearchedRecentTitle>최근 검색</SearchedRecentTitle>
            <SearchedUsersContainer>
              {renderRecentSearches()}
            </SearchedUsersContainer>
          </SearchedRecentContainer>
        ) : null}
      </SearchDetailContainer>
    </ModalOverlay>
  );
}
