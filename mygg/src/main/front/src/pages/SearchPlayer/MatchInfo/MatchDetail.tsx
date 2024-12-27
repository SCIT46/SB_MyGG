import styled from "styled-components";

import { useEffect, useState } from "react";
import { IMatchDetail } from "../type";
import ChampionImage from "../../../components/ImageUI/ChampionImage";
import SummonerImage from "../../../components/ImageUI/SummonerImage";
import ItemImage from "../../../components/ImageUI/ItemImage";
import Participants from "./Participants";
import MatchDetailContent from "./MatchDetailContent";
import StyledRuneImage from "../../../components/ImageUI/StyledRuneImage";
import RuneImage from "../../../components/ImageUI/RuneImage";
import { queueIdObj } from "../../../utils/QueueIdObj";

const MatchDetailContainer = styled.div<{ iswinning: boolean }>`
  background-color: ${(props) => (props.iswinning ? "#f0f6ff" : "#feeaea")};
  border: 1px solid ${(props) => (props.iswinning ? "#c7d5ff" : "#ffb0b0")};
  border-left: 8px solid
    ${(props) => (props.iswinning ? "#0057b3cc" : "#e63947c6")};
  width: 90%;
  height: 120px;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

const GameInfoContainer = styled.div`
  display: flex;
  margin-left: 9px;
  flex-direction: column;
  justify-content: center;
  width: 110px;
`;

const PlayerInfoContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin-left: -30px;
  gap: 8px;
`;

const PlayerChampionInfoContainer = styled.div`
  display: flex;
  gap: 4px;
`;

const PlayerSpellContianer = styled.div`
  display: flex;
  gap: 4px;

  flex-direction: column;
`;

const PlayerRuneContianer = styled.div`
  display: flex;
  gap: 5px;

  flex-direction: column;
`;

const PlayerKdaContianer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-left: 12px;
`;

const PlayerItemsInfoContainer = styled.div`
  gap: 4px;

  display: flex;
`;

const KdaSpan = styled.div`
  margin-bottom: 4px;
  font-size: 14px;
  font-weight: 500;
  color: ${({ theme }) => theme.colors.text.primary};
`;

const KdaSocreSpan = styled.div`
  font-size: 12px;
  font-weight: 400;
  color: ${({ theme }) => theme.colors.text.secondary};
`;

const CsSpan = styled.div`
  font-size: 12px;
  color: ${({ theme }) => theme.colors.text.secondary};
`;

const DetailBtn = styled.div`
  width: 10px;
  height: 10px;
  border-left: 2px solid black; /* 화살표의 왼쪽 선 */
  border-bottom: 2px solid black; /* 화살표의 아래쪽 선 */
  transform: rotate(315deg); /* 기울여서 화살표 모양으로 */
  cursor: pointer;
`;

const DetailBtnContainer = styled.div`
  display: flex;
  height: 80%;
  align-items: flex-end;
  margin-left: 30px;
  margin-right: 10px;
`;

const GameModeSpan = styled.div<{ iswinning: boolean }>`
  font-size: 14px;
  font-weight: 600;
  margin-bottom: 2px;
  color: ${({ iswinning }) => (iswinning ? "#0056b3" : "#E63946")};
`;

const GameTimeSpan = styled.div`
  font-size: 12px;
  margin-bottom: 20px;
  color: ${({ theme }) => theme.colors.text.secondary};
`;

const GameDurationSpan = styled.div`
  font-size: 14px;
  margin-bottom: 2px;
  color: ${({ theme }) => theme.colors.text.secondary};
`;

const GameResultSpan = styled.div<{ iswinning: boolean }>`
  font-size: 14px;
  font-weight: 600;
  color: ${({ iswinning }) => (iswinning ? "#0056b3" : "#E63946")};
`;

const KdaNumber = styled.span`
  font-size: 16px;
  font-weight: 600;
  color: ${({ theme }) => theme.colors.text.primary};
`;

const KdaNumberDeath = styled.span`
  font-size: 16px;
  font-weight: 600;
  color: #e63946;
`;

const KdaSlash = styled.span`
  font-size: 16px;
  font-weight: 400;
  color: ${({ theme }) => theme.colors.text.secondary};
`;

export interface IMatchDetailProp {
  matchDetail: IMatchDetail;
  userPuuid?: string;
}

export default function MatchDetail({
  matchDetail,
  userPuuid,
}: IMatchDetailProp) {
  const [userIndex, setUserIndex] = useState<number>();
  const [isDetailOpen, setIsDetailOpen] = useState<boolean>(false);

  const currentTime = Date.now();

  const timeDifference = currentTime - matchDetail.info.gameStartTimestamp;
  const daysAgo = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
  const hoursAgo = Math.floor((timeDifference / (1000 * 60 * 60)) % 24);
  const minutesAgo = Math.floor((timeDifference / (1000 * 60)) % 60);

  useEffect(() => {
    if (userPuuid && matchDetail.metadata) {
      setUserIndex(matchDetail.metadata.participants.indexOf(userPuuid));
    }
  }, [matchDetail.metadata, userPuuid]);

  if (
    userIndex === undefined ||
    !matchDetail.info ||
    !matchDetail.info.participants[userIndex]
  ) {
    return (
      <div>
        Puuid 에러! (DB의 Puuid 와 MatchDetail 의 Puuid 가 다름) #발견시 DB 수정
      </div>
    );
  }

  return (
    <>
      <MatchDetailContainer
        onClick={() => {
          setIsDetailOpen((prev) => !prev);
        }}
        iswinning={matchDetail.info.participants[userIndex].win}
      >
        <GameInfoContainer>
          <GameModeSpan
            iswinning={matchDetail.info.participants[userIndex].win}
          >
            {queueIdObj[matchDetail.info.queueId].description}
          </GameModeSpan>
          <GameTimeSpan>
            {daysAgo > 0
              ? `${daysAgo}일 전`
              : hoursAgo > 0
              ? `${hoursAgo}시간 전`
              : `${minutesAgo}분 전`}
          </GameTimeSpan>
          <GameDurationSpan>
            {String(Math.floor(matchDetail.info.gameDuration / 60)).padStart(
              2,
              "0"
            )}
            :{String(matchDetail.info.gameDuration % 60).padStart(2, "0")}
          </GameDurationSpan>
          <GameResultSpan
            iswinning={matchDetail.info.participants[userIndex].win}
          >
            {matchDetail.info.participants[userIndex].win ? "승리" : "패배"}
          </GameResultSpan>
        </GameInfoContainer>
        <PlayerInfoContainer>
          <PlayerChampionInfoContainer>
            <ChampionImage
              championId={matchDetail.info.participants[userIndex].championName}
            />
            <PlayerSpellContianer>
              <SummonerImage
                summonerId={
                  matchDetail.info.participants[userIndex].summoner1Id
                }
              />
              <SummonerImage
                summonerId={
                  matchDetail.info.participants[userIndex].summoner2Id
                }
              />
            </PlayerSpellContianer>
            <PlayerRuneContianer>
              <RuneImage
                styleRuneId={
                  matchDetail.info.participants[userIndex].perks.styles[0].style
                }
                runeId={
                  matchDetail.info.participants[userIndex].perks.styles[0]
                    .selections[0].perk
                }
              />
              <StyledRuneImage
                runeId={
                  matchDetail.info.participants[userIndex].perks.styles[1].style
                }
              />
            </PlayerRuneContianer>
            <PlayerKdaContianer>
              <KdaSpan>
                <KdaNumber>
                  {matchDetail.info.participants[userIndex].kills}
                </KdaNumber>
                <KdaSlash> / </KdaSlash>
                <KdaNumberDeath>
                  {matchDetail.info.participants[userIndex].deaths}
                </KdaNumberDeath>
                <KdaSlash> / </KdaSlash>
                <KdaNumber>
                  {matchDetail.info.participants[userIndex].assists}
                </KdaNumber>
              </KdaSpan>
              <KdaSocreSpan>
                {(
                  (matchDetail.info.participants[userIndex].kills +
                    matchDetail.info.participants[userIndex].assists) /
                  matchDetail.info.participants[userIndex].deaths
                ).toFixed(2)}{" "}
                KDA
              </KdaSocreSpan>
              <CsSpan>
                {matchDetail.info.participants[userIndex].totalMinionsKilled} cs
              </CsSpan>
            </PlayerKdaContianer>
          </PlayerChampionInfoContainer>
          <PlayerItemsInfoContainer>
            {[0, 1, 2, 3, 4, 5].map((n, index) => (
              <ItemImage
                itemId={
                  (matchDetail.info.participants[userIndex] as any)[`item${n}`]
                }
                key={index}
              />
            ))}
            <ItemImage
              itemId={
                (matchDetail.info.participants[userIndex] as any)[`item6`]
              }
              isTrinket={true}
            />
          </PlayerItemsInfoContainer>
        </PlayerInfoContainer>
        <Participants matchDetail={matchDetail} />
        <DetailBtnContainer>
          <DetailBtn></DetailBtn>
        </DetailBtnContainer>
      </MatchDetailContainer>
      {isDetailOpen && (
        <MatchDetailContent matchDetail={matchDetail} userPuuid={userPuuid} />
      )}
    </>
  );
}
