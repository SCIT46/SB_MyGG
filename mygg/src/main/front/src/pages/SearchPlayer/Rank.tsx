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

export default function Rank() {
  return (
    <RankContianer>
      <RankImg />
      <RankSpan>Diamond</RankSpan>
      <ScoreSpan>67p</ScoreSpan>
      <WinSpan>100 승 100 패</WinSpan>
      <WinRateSpan>승률 60 %</WinRateSpan>
    </RankContianer>
  );
}
