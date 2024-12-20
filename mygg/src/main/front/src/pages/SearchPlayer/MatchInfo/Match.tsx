import styled from "styled-components";
import { useFetchMatchDetails } from "../useFetchMatchDetails";
import MatchDetail from "./MatchDetail";

const MacthContainer = styled.div`
  border: 1px ${({ theme }) => theme.colors.brand.gold.main} solid;
  width: 100%;
  border-radius: 10px;
  background-color: ${({ theme }) => theme.colors.background.white};
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 5px;
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

interface IMatchProps {
  matchList?: string[];
  puuid?: string;
}

export default function Match({ matchList, puuid }: IMatchProps) {
  const { matchDetails, isLoading } = useFetchMatchDetails(matchList);

  if (isLoading) {
    return (
      <MacthContainer>
        <CustomGameBtn>커스텀 게임 보러가기 -</CustomGameBtn>
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
