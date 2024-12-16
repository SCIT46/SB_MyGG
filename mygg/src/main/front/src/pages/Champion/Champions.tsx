import styled from "styled-components";
import useChampionStore from "../../stores/useChampionStore";
import ChampionImage from "../../components/ChampionImage";

const TitleBox = styled.div`
  margin-top: 50px;
  width: 60vw;
`;

const TitleLine = styled.div`
  margin-top: 25px;
  margin-bottom: 17px;

  width: 60vw;
  height: 3px;
  background-color: ${({ theme }) => theme.colors.brand.gold.main};
`;

const Title = styled.div`
  color: ${({ theme }) => theme.colors.text.primary};
  font-weight: 600;
  font-size: 24px;
`;

const ChampionsContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100vw;
`;

const ChampionContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  width: 60vw;
  gap: 7px;
`;
// '/champion' 라우트 이동시 랜더링 되는 컴포넌트
export default function Champions() {
  const champions = useChampionStore((state) => state.champions);

  return (
    <ChampionsContainer>
      <TitleBox>
        <Title>챔피언 분석</Title>
      </TitleBox>
      <TitleLine />
      <ChampionContainer>
        {champions &&
          Object.entries(champions).map(([key, value]) => (
            <ChampionImage championId={key} key={key} width={50} height={50} />
          ))}
      </ChampionContainer>
    </ChampionsContainer>
  );
}
