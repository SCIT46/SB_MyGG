import styled from "styled-components";
import ChampDetail from "./ChamDetail";

const ChampInfoContainer = styled.div`
  border: 1px ${({ theme }) => theme.colors.border.main} solid;
  width: 100%;
  height: 400px;
  background-color: ${({ theme }) => theme.colors.background.white};
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
