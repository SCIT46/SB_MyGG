import { useState } from "react";
import styled from "styled-components";

const RankContianer = styled.div`
  border: 1px ${({ theme }) => theme.colors.primaryGold} solid;
  width: 100%;
  height: 200px;
  background-color: ${({ theme }) => theme.colors.backgroundWhite};
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 3px;
  font-size: 14px;
`;

const RankImg = styled.div`
  margin-top: 15px;
  border-radius: 10px;
  width: 100px;
  height: 100px;
  background-color: gray;
`;

const RankSpan = styled.div``;
const ScoreSpan = styled.div``;
const WinSpan = styled.div``;
const WinRateSpan = styled.div``;

interface IRankProps {
  tier?: string;
  rank?: string;
  leaguePoints?: number;
  wins?: number;
  losses?: number;
}

export default function Rank({
  tier,
  rank,
  leaguePoints,
  wins,
  losses,
}: IRankProps) {
  return (
    <RankContianer>
      <RankImg />
      <RankSpan>{tier}</RankSpan>
      <ScoreSpan>{leaguePoints}</ScoreSpan>
      <WinSpan>
        {wins}승 {losses}패
      </WinSpan>
      <WinRateSpan>{wins && losses && wins / wins + losses}</WinRateSpan>
    </RankContianer>
  );
}
