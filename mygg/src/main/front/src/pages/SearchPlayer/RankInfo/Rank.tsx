import { useState } from "react";
import styled from "styled-components";

const RankContianer = styled.div`
  border: 1px ${({ theme }) => theme.colors.brand.gold.main} solid;
  width: 100%;
  height: 120px;
  background-color: ${({ theme }) => theme.colors.background.white};
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
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
  margin-left: -2px;
  border-radius: 10px;
  width: 85px;
  height: 85px;
`;

const RankSpan = styled.div`
  font-size: 16px;
  font-weight: 700;
  color: ${({ theme }) => theme.colors.text.primary};
`;
const ScoreSpan = styled.div`
  font-size: 14px;
  font-weight: 600;
  color: ${({ theme }) => theme.colors.text.primary};
`;
const WinSpan = styled.div`
  font-size: 12px;
  color: ${({ theme }) => theme.colors.text.light};
`;
const WinRateSpan = styled.div`
  margin-left: 4px;
  font-size: 12px;
  color: ${({ theme }) => theme.colors.text.light};
`;

const RankInfoBox = styled.div`
  display: flex;
  flex-direction: column;
  margin-left: 10px;
`;

const WinInfoBox = styled.div`
  display: flex;
  align-items: center;
  margin-top: 13px;
  margin-bottom: 2px;
`;

interface IRankProps {
  tier?: string;
  rank?: string;
  leaguePoints?: number;
  wins?: number;
  losses?: number;
}

function capitalizeFirstLetter(str: string) {
  return str.charAt(0).toUpperCase() + str.slice(1).toLowerCase();
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
      <RankInfoBox>
        <RankSpan>
          {capitalizeFirstLetter(tier as string)} {rank}
        </RankSpan>
        <ScoreSpan>{leaguePoints} p</ScoreSpan>
        <WinInfoBox>
          <WinSpan>
            {wins}승 {losses}패
          </WinSpan>
          <WinRateSpan>
            승률{" "}
            {(
              ((wins as number) / ((wins as number) + (losses as number))) *
              100
            ).toFixed(0)}
            %
          </WinRateSpan>
        </WinInfoBox>
      </RankInfoBox>
    </RankContianer>
  );
}
