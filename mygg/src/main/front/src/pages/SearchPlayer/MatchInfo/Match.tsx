import styled from "styled-components";
import { useFetchMatchDetails } from "../useFetchMatchDetails";
import MatchDetail from "./MatchDetail";

const MacthContainer = styled.div`
  border: 1px ${({ theme }) => theme.colors.border.main} solid;
  width: 100%;
  border-radius: 10px;
  background-color: ${({ theme }) => theme.colors.background.white};
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
`;

const CustomGameBtn = styled.div`
  margin-top: 15px;
  margin-bottom: 10px;
  width: 80%;
  border-radius: 15px;
  height: 40px;
  background-color: ${({ theme }) => theme.colors.brand.sky.main};
  display: flex;
  justify-content: center;
  align-items: center;
  color: ${({ theme }) => theme.colors.text.white};
  box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.3);
`;

const MatchDetailLoadingSkeleton = styled.div`
  width: 90%;
  height: 120px;
  background-color: ${({ theme }) => theme.colors.background.secondary};
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  opacity: 0.5;
  animation: pulse 1.5s infinite ease-in-out;

  @keyframes pulse {
    0% {
      opacity: 0.5;
    }
    50% {
      opacity: 1;
    }
    100% {
      opacity: 0.5;
    }
  }
`;

interface IMatchProps {
  userName: string;
  tagLine: string;
  puuid?: string;
}

export default function Match({ userName, tagLine, puuid }: IMatchProps) {
  const { matchDetails, isLoading } = useFetchMatchDetails(userName, tagLine);

  console.log(matchDetails);

  if (isLoading) {
    return (
      <MacthContainer>
        <CustomGameBtn>커스텀 게임 보러가기 -</CustomGameBtn>
        {Array.from({ length: 20 }).map((_, index) => (
          <MatchDetailLoadingSkeleton key={index} />
        ))}
      </MacthContainer>
    );
  }

  return (
    <MacthContainer>
      <CustomGameBtn>커스텀 게임 보러가기 -</CustomGameBtn>
      {matchDetails.map((matchDetail, index) => (
        <MatchDetail matchDetail={matchDetail} userPuuid={puuid} key={index} />
      ))}
    </MacthContainer>
  );
}
