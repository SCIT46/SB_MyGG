import { useState } from "react";
import styled from "styled-components";

const RankContianer = styled.div`
  border: 1px ${({ theme }) => theme.colors.brand.gold.main} solid;
  width: 100%;
  height: 200px;
  background-color: ${({ theme }) => theme.colors.background.white};
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 3px;
  font-size: 14px;
`;

const UnRankContianer = styled.div`
  border: 1px ${({ theme }) => theme.colors.brand.gold.main} solid;
  width: 100%;
  height: 50px;
  background-color: ${({ theme }) => theme.colors.background.white};
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 3px;
  font-size: 14px;
`;

const RankImg = styled.img`
  margin-top: 5px;
  border-radius: 10px;
  width: 100px;
  height: 100px;
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
  if (tier === "UNRANKED") {
    return (
      <UnRankContianer>
        <div>unranked</div>
      </UnRankContianer>
    );
  }
  return (
    <RankContianer>
      <RankImg src={`/images/Rank=${tier}.png`} />
      <RankSpan>
        {tier} {rank}
      </RankSpan>
      <ScoreSpan>{leaguePoints} p</ScoreSpan>
      <WinSpan>
        {wins}승 {losses}패
      </WinSpan>
      <WinRateSpan>
        승률 :{" "}
        {(
          ((wins as number) / ((wins as number) + (losses as number))) *
          100
        ).toFixed(0)}
        %
      </WinRateSpan>
    </RankContianer>
  );
}
