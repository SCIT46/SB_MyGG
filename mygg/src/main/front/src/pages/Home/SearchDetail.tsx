import styled from "styled-components";
import dummy from "./dummy";
import { useNavigate } from "react-router-dom";

const SearchDetailContainer = styled.div`
  background-color: ${({ theme }) => theme.colors.backgroundWhite};
  margin-top: 3px;
  margin-left: 30px;
  width: 80%;
  border-radius: 10px;
  border: 1.5px solid ${({ theme }) => theme.colors.primaryGold};
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Line = styled.div`
  width: 97%;
  height: 1px;
  margin-top: 6px;
  margin-bottom: 4px;
  background-color: ${({ theme }) => theme.colors.primaryGold};
`;

const SearchDetailTitle = styled.div`
  font-size: 14px;
  color: ${({ theme }) => theme.colors.primaryGold};
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
    background-color: aliceblue;
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
  color: ${({ theme }) => theme.colors.textBlack};
`;

const SearchedUserTag = styled.div`
  font-size: 14px;
  margin-left: 5px;
  color: ${({ theme }) => theme.colors.lightText};
  font-weight: 600;
`;

interface IUser {
  gameName: string;
  tagLine: string;
  imageId: string;
  lever: number;
}

interface ISearchDeailProps {
  searchedUser: IUser[];
}

export default function SearchDetail({ searchedUser }: ISearchDeailProps) {
  const navigate = useNavigate();

  const handleNavigate = (gameName: string, tagLine: string) => {
    navigate(`/search/${gameName}/${tagLine}`);
  };

  return (
    <SearchDetailContainer>
      <SearchDetailTitle>소환사</SearchDetailTitle>
      <Line />
      <SearchedUsersContainer>
        {searchedUser.map((searchedUser, index) => (
          <SearchedUserBox
            key={index}
            onMouseDown={() =>
              handleNavigate(searchedUser.gameName, searchedUser.tagLine)
            }
          >
            <SearchedUserProfileImg />
            <SearchedUserName>{searchedUser.gameName}</SearchedUserName>
            <SearchedUserTag>#{searchedUser.tagLine}</SearchedUserTag>
          </SearchedUserBox>
        ))}
      </SearchedUsersContainer>
    </SearchDetailContainer>
  );
}
