import styled from "styled-components";
import IRecentAnaylsis from "../../../interfaces/IRecentAnaylsis";
import ChampionImage from "../../../components/ImageUI/ChampionImage";

const ChampContainer = styled.div`
  margin-top: 10px;
  width: 90%;
  background-color: #dbdbdb;
  height: 40px;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 10px;
  padding: 5px;
`;

const ChamNameSpan = styled.div`
  margin-left: -7px;
`;
const ChamKdaSpan = styled.div``;
const ChamKdaDetailSpan = styled.div``;
const ChamWinRateSpan = styled.div``;
const ChamGameSpan = styled.div``;
const KdaContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-left: 10px;
  gap: 5px;
`;

const WinRateContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-right: 10px;
  gap: 5px;
  margin-left: 5px;
`;

export default function ChampDetail({ data }: { data: IRecentAnaylsis }) {
  console.log(data);
  return (
    <ChampContainer>
      <ChampionImage championId={data.championName} width={35} height={35} />
      <ChamNameSpan>{data.championName}</ChamNameSpan>
      <KdaContainer>
        <ChamKdaSpan>{data.kda.toFixed(2)} kda</ChamKdaSpan>
        <ChamKdaDetailSpan>
          {data.kill} / {data.death} / {data.assist}
        </ChamKdaDetailSpan>
      </KdaContainer>
      <WinRateContainer>
        <ChamWinRateSpan> %</ChamWinRateSpan>
        <ChamGameSpan>{data.gameCnt} 게임</ChamGameSpan>
      </WinRateContainer>
    </ChampContainer>
  );
}
