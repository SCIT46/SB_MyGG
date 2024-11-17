import { useParams } from "react-router-dom";
import styled from "styled-components";
import Profile from "./Profile";
import Rank from "./Rank";

const SearchPlayerContainer = styled.div`
  width: 100vw;
  display: flex;
  align-items: center;
  flex-direction: column;
`;

const BottomContainer = styled.div`
  margin-top: 20px;
  width: 1000px;
  display: flex;
  gap: 15px;
`;

const LeftConationer = styled.div`
  width: 350px;
`;

const RankContianer = styled.div`
  border: 1px ${({ theme }) => theme.colors.primaryGold} solid;

  width: 100%;
  height: 200px;
  background-color: ${({ theme }) => theme.colors.backgroundWhite};
  border-radius: 10px;
`;

const ChampInfoContainer = styled.div`
  border: 1px ${({ theme }) => theme.colors.primaryGold} solid;

  width: 100%;
  height: 400px;
  background-color: ${({ theme }) => theme.colors.backgroundWhite};
  border-radius: 10px;
  margin-top: 15px;
`;

const MacthContainer = styled.div`
  border: 1px ${({ theme }) => theme.colors.primaryGold} solid;

  width: 100%;
  height: 1000px;
  border-radius: 10px;

  background-color: ${({ theme }) => theme.colors.backgroundWhite};
`;

// '/search/:id 라우트 이동시 랜더링 되는 컴포넌트
export default function SearchPlayer() {
  return (
    <SearchPlayerContainer>
      <Profile />
      <BottomContainer>
        <LeftConationer>
          <Rank />
          <ChampInfoContainer></ChampInfoContainer>
        </LeftConationer>
        <MacthContainer></MacthContainer>
      </BottomContainer>
    </SearchPlayerContainer>
  );
}
