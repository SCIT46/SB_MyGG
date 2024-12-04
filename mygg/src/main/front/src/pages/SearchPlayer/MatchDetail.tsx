import styled from "styled-components";
import Participants from "./Participants";
import { IMatchDetail } from "./type";
import { useEffect, useState } from "react";
import LoadingSpinner from "../../components/Loading";
import ChampionImage from "../../components/ChampionImage";
import ItemImage from "../../components/ItemImage";
import SummonerImage from "../../components/SummonerImage";

const MatchDetailContainer = styled.div<{ isWinning: boolean }>`
  background-color: ${(props) => (props.isWinning ? "#e2edff" : "#ffe8e8")};
  border: 1px solid ${(props) => (props.isWinning ? "#a8c1ff" : "#ffa4a4")};
  width: 90%;
  height: 120px;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

const GameInfoContainer = styled.div`
  display: flex;
  margin-left: 7px;
  flex-direction: column;
  justify-content: center;
  width: 50px;
`;

const PlayerInfoContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin-left: -50px;
  gap: 10px;
`;

const PlayerChampionInfoContainer = styled.div`
  display: flex;
  gap: 5px;
`;

const PlayerSpellContianer = styled.div`
  display: flex;
  gap: 5px;

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
  gap: 5px;

  display: flex;
`;

const SpellImg = styled.img`
  background-color: gray;
  border-radius: 5px;
  width: 28px;
  height: 28px;
`;

//todo img로 바꾸기
const RuneImg = styled.div`
  border-radius: 100%;
  background-color: gray;
  width: 28px;
  height: 28px;
`;

const KdaSpan = styled.div``;

const KdaSocreSpan = styled.div`
  font-size: 12px;
`;

const CsSpan = styled.div`
  font-size: 12px;
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

interface IMatchDetailProp {
  matchDetail: IMatchDetail;
  userPuuid?: string;
}

export default function MatchDetail({
  matchDetail,
  userPuuid,
}: IMatchDetailProp) {
  const [userIndex, setUserIndex] = useState<number>();
  const [isDetailOpen, setIsDetailOpen] = useState<boolean>(false);
  console.log(isDetailOpen);

  // const currentTime = Date.now();
  // const timeDifference = currentTime - matchDetail.info.;

  // const minutesAgo = Math.floor(timeDifference / (1000 * 60));
  // const hoursAgo = Math.floor(timeDifference / (1000 * 60 * 60));
  // const daysAgo = Math.floor(timeDifference / (1000 * 60 * 60 * 24));

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
    return <LoadingSpinner></LoadingSpinner>;
  }

  return (
    <>
      <MatchDetailContainer
        onClick={() => {
          setIsDetailOpen((prev) => !prev);
        }}
        isWinning={matchDetail.info.participants[userIndex].win}
      >
        <GameInfoContainer>
          <div></div>
          <div>x일전</div>
          <div>
            {Math.floor(matchDetail.info.gameDuration / 60)}:
            {matchDetail.info.gameDuration % 60}
          </div>
          <div>
            {matchDetail.info.participants[userIndex].win ? "승리" : "패배"}
          </div>
        </GameInfoContainer>
        <PlayerInfoContainer>
          <PlayerChampionInfoContainer>
            {/* todo - img들 전부 컴포넌트와 해야지 호버 기능 구현가능 */}
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
              <RuneImg></RuneImg>
              <RuneImg></RuneImg>
            </PlayerRuneContianer>
            <PlayerKdaContianer>
              <KdaSpan>
                {matchDetail.info.participants[userIndex].kills} /{" "}
                {matchDetail.info.participants[userIndex].deaths} /{" "}
                {matchDetail.info.participants[userIndex].assists}
              </KdaSpan>
              <KdaSocreSpan>
                {(
                  (matchDetail.info.participants[userIndex].kills +
                    matchDetail.info.participants[userIndex].assists) /
                  matchDetail.info.participants[userIndex].deaths
                ).toFixed(2)}
              </KdaSocreSpan>
              <CsSpan>
                {matchDetail.info.participants[userIndex].totalMinionsKilled} cs
              </CsSpan>
            </PlayerKdaContianer>
          </PlayerChampionInfoContainer>
          <PlayerItemsInfoContainer>
            {[0, 1, 2, 3, 4, 5, 6].map((n, index) => (
              <ItemImage
                itemId={
                  (matchDetail.info.participants[userIndex] as any)[`item${n}`]
                }
                key={index}
              />
            ))}
          </PlayerItemsInfoContainer>
        </PlayerInfoContainer>
        <Participants matchDetail={matchDetail} />
        <DetailBtnContainer>
          <DetailBtn></DetailBtn>
        </DetailBtnContainer>
      </MatchDetailContainer>
      {/* todo - detail 컴포넌트 만들기 */}
      <div>{isDetailOpen ? "detail" : ""}</div>
    </>
  );
}
