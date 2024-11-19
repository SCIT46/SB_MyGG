import styled from "styled-components";
import ChampDetail from "./ChamDetail";

const ChampInfoContainer = styled.div`
  border: 1px ${({ theme }) => theme.colors.primaryGold} solid;
  width: 100%;
  height: 400px;
  background-color: ${({ theme }) => theme.colors.backgroundWhite};
  border-radius: 10px;
  margin-top: 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const ChampBar = styled.div`
  width: 100%;
  height: 60px;
  margin-top: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
`;

const ChampBtn = styled.div`
  width: 70px;
  height: 35px;
  background-color: black;
  border-radius: 5px;
  background-color: gray;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  font-size: 14px;
`;

const ChampContainer = styled.div`
  width: 90%;
  background-color: #dbdbdb;
  height: 60px;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 10px;
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
export default function ChampInfo() {
  const dummy = [1, 2, 3, 4, 5];
  return (
    <ChampInfoContainer>
      <ChampBar>
        <ChampBtn>전체</ChampBtn>
        <ChampBtn>솔로랭크</ChampBtn>
        <ChampBtn>자유랭크</ChampBtn>
      </ChampBar>
      {dummy.map((champ, index) => (
        <ChampDetail key={index} />
      ))}
    </ChampInfoContainer>
  );
}
