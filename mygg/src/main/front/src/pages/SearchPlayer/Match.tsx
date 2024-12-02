import styled from "styled-components";
import MatchDetail from "./MatchDetail";

const MacthContainer = styled.div`
  border: 1px ${({ theme }) => theme.colors.primaryGold} solid;
  width: 100%;
  border-radius: 10px;
  background-color: ${({ theme }) => theme.colors.backgroundWhite};
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
`;

const CustomGameBtn = styled.div`
  margin-top: 15px;
  margin-bottom: 10px;
  width: 80%;
  border-radius: 15px;
  height: 40px;
  background-color: ${({ theme }) => theme.colors.primarySky};
  display: flex;
  justify-content: center;
  align-items: center;
  color: ${({ theme }) => theme.colors.textWhite};
  box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.3);
`;

export default function Match() {
  const dummy = [1];
  return (
    <MacthContainer>
      <CustomGameBtn>커스텀 게임 보러가기 -</CustomGameBtn>
      {dummy.map((match, index) => (
        <MatchDetail />
      ))}
    </MacthContainer>
  );
}
