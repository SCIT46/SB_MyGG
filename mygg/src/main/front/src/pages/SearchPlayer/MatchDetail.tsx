import styled from "styled-components";
import Participants from "./Participants";
import { IMatchDetail } from "./type";
import { useEffect, useState } from "react";
import LoadingSpinner from "../../components/Loading";
import ChampionImage from "../../components/ChampionImage";

const MatchDetailContainer = styled.div`
  background-color: #c7c7c7;
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

//todo img로 바꾸기
const ChampionImg = styled.img`
  border-radius: 5px;
  width: 60px;
  height: 60px;
`;

//todo img로 바꾸기
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

//todo img로 바꾸기
const ItemImg = styled.img`
  border-radius: 5px;
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
  height: 100%;
  display: flex;
  align-items: flex-end;
`;

interface IMatchDetailProp {
  matchDetail: IMatchDetail;
  userPuuid?: string;
}

export default function MatchDetail({
  matchDetail,
  userPuuid,
}: IMatchDetailProp) {
  const nullUrl =
    "https://mblogthumb-phinf.pstatic.net/MjAxODAzMDNfMjU4/MDAxNTIwMDQxODA4Mjc0.gR3L5xx3IbpACbvRRF9j9xjJmO-EPAY35oF1AdBnDcog.WZyeqFi6cMmH-v-R-ec44Ny6ZgVyAJIYMT78p4Rxbkwg.PNG.osy2201/2_%2850%ED%8D%BC%EC%84%BC%ED%8A%B8_%ED%9A%8C%EC%83%89%29_%ED%9A%8C%EC%83%89_%EB%8B%A8%EC%83%89_%EB%B0%B0%EA%B2%BD%ED%99%94%EB%A9%B4_180303.png?type=w800";
  const [userIndex, setUserIndex] = useState<number>();
  useEffect(() => {
    if (userPuuid) {
      setUserIndex(matchDetail.metadata.participants.indexOf(userPuuid));
    }
  }, []);

  if (userIndex === undefined) {
    return <LoadingSpinner></LoadingSpinner>;
  }

  return (
    <MatchDetailContainer>
      <GameInfoContainer>
        <div></div>
        <div>x일전</div>
        <div></div>
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
            <SpellImg></SpellImg>
            <SpellImg></SpellImg>
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
          <ItemImg
            src={
              matchDetail.info.participants[userIndex].item0
                ? `https://ddragon.leagueoflegends.com/cdn/14.21.1/img/item/${matchDetail.info.participants[userIndex].item0}.png`
                : nullUrl
            }
          ></ItemImg>
          <ItemImg
            src={
              matchDetail.info.participants[userIndex].item1
                ? `https://ddragon.leagueoflegends.com/cdn/14.21.1/img/item/${matchDetail.info.participants[userIndex].item1}.png`
                : nullUrl
            }
          ></ItemImg>
          <ItemImg
            src={
              matchDetail.info.participants[userIndex].item2
                ? `https://ddragon.leagueoflegends.com/cdn/14.21.1/img/item/${matchDetail.info.participants[userIndex].item2}.png`
                : nullUrl
            }
          ></ItemImg>
          <ItemImg
            src={
              matchDetail.info.participants[userIndex].item3
                ? `https://ddragon.leagueoflegends.com/cdn/14.21.1/img/item/${matchDetail.info.participants[userIndex].item3}.png`
                : nullUrl
            }
          ></ItemImg>
          <ItemImg
            src={
              matchDetail.info.participants[userIndex].item4
                ? `https://ddragon.leagueoflegends.com/cdn/14.21.1/img/item/${matchDetail.info.participants[userIndex].item4}.png`
                : nullUrl
            }
          ></ItemImg>
          <ItemImg
            src={
              matchDetail.info.participants[userIndex].item5
                ? `https://ddragon.leagueoflegends.com/cdn/14.21.1/img/item/${matchDetail.info.participants[userIndex].item5}.png`
                : nullUrl
            }
          ></ItemImg>
          <ItemImg
            src={
              matchDetail.info.participants[userIndex].item6
                ? `https://ddragon.leagueoflegends.com/cdn/14.21.1/img/item/${matchDetail.info.participants[userIndex].item6}.png`
                : nullUrl
            }
          ></ItemImg>
        </PlayerItemsInfoContainer>
      </PlayerInfoContainer>
      <Participants matchDetail={matchDetail} />
      <DetailBtn>btn</DetailBtn>
    </MatchDetailContainer>
  );
}
