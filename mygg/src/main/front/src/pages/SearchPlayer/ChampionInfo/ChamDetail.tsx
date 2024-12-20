import styled from "styled-components";

const ChampContainer = styled.div`
  width: 90%;
  background-color: #dbdbdb;
  height: 60px;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 10px;
  margin-bottom: 10px;
`;

const ChampImg = styled.div`
  width: 40px;
  height: 40px;
  border-radius: 100%;
  background-color: beige;
  margin-left: 8px;
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

export default function ChampDetail() {
  return (
    <ChampContainer>
      <ChampImg />
      <ChamNameSpan>챔피언</ChamNameSpan>
      <KdaContainer>
        <ChamKdaSpan>3.33 kda</ChamKdaSpan>
        <ChamKdaDetailSpan>10 / 6 / 10</ChamKdaDetailSpan>
      </KdaContainer>
      <WinRateContainer>
        <ChamWinRateSpan>60 %</ChamWinRateSpan>
        <ChamGameSpan>200 게임</ChamGameSpan>
      </WinRateContainer>
    </ChampContainer>
  );
}
