import styled from "styled-components";
import Participants from "./Participants";

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
const ChampionImg = styled.div`
  border-radius: 5px;
  background-color: gray;
  width: 60px;
  height: 60px;
`;

//todo img로 바꾸기
const SpellImg = styled.div`
  border-radius: 5px;
  background-color: gray;
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
const ItemImg = styled.div`
  border-radius: 5px;
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
  height: 100%;
  display: flex;
  align-items: flex-end;
`;

export default function MatchDetail() {
  return (
    <MatchDetailContainer>
      <GameInfoContainer>
        <div>gameMode</div>
        <div>x일전</div>
        <div>gameDuration</div>
        <div>패배</div>
      </GameInfoContainer>
      <PlayerInfoContainer>
        <PlayerChampionInfoContainer>
          <ChampionImg></ChampionImg>
          <PlayerSpellContianer>
            <SpellImg></SpellImg>
            <SpellImg></SpellImg>
          </PlayerSpellContianer>
          <PlayerRuneContianer>
            <RuneImg></RuneImg>
            <RuneImg></RuneImg>
          </PlayerRuneContianer>
          <PlayerKdaContianer>
            <KdaSpan>k/d/a</KdaSpan>
            <KdaSocreSpan>kdascore</KdaSocreSpan>
            <CsSpan>cs</CsSpan>
          </PlayerKdaContianer>
        </PlayerChampionInfoContainer>
        <PlayerItemsInfoContainer>
          <ItemImg></ItemImg>
          <ItemImg></ItemImg>
          <ItemImg></ItemImg>
          <ItemImg></ItemImg>
          <ItemImg></ItemImg>
          <ItemImg></ItemImg>
          <ItemImg></ItemImg>
        </PlayerItemsInfoContainer>
      </PlayerInfoContainer>
      <Participants />
      <DetailBtn>btn</DetailBtn>
    </MatchDetailContainer>
  );
}
