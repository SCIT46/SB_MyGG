import styled from "styled-components";
import { IMatchDetailProp } from "./MatchDetail";
import ChampionImage from "../../../components/ChampionImage";
import SummonerImage from "../../../components/SummonerImage";
import ItemImage from "../../../components/ItemImage";

const MatchDetailContentContainer = styled.div`
  border: 2px solid black;
  border-radius: 10px;
  width: 90%;
  display: flex;
  flex-direction: column;
  gap: 10px;
`;

const MatchDetailContentBox = styled.div`
  display: flex;
  justify-content: space-around;
  border: 1px solid black;
`;

const StyledChampionImage = styled(ChampionImage)`
  width: 25px; // 원하는 크기로 설정
  height: 25px; // 원하는 크기로 설정
`;

const SummonerContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

export default function MatchDetailContent({
  matchDetail,
  userPuuid,
}: IMatchDetailProp) {
  return (
    <MatchDetailContentContainer>
      {matchDetail.info.participants.map((part, index) => (
        <MatchDetailContentBox key={index}>
          <StyledChampionImage
            championId={part.championName}
            width={40}
            height={40}
          />
          <SummonerContainer>
            <SummonerImage
              summonerId={part.summoner1Id}
              width={18}
              height={18}
            />
            <SummonerImage
              summonerId={part.summoner2Id}
              width={18}
              height={18}
            />
          </SummonerContainer>
          <div>{part.riotIdGameName}</div>
          <ItemImage itemId={part.item0} width={20} height={20} />
        </MatchDetailContentBox>
      ))}
    </MatchDetailContentContainer>
  );
}
