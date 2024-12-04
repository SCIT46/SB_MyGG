import styled, { keyframes } from "styled-components";
import { useState } from "react";
import { Link } from "react-router-dom";
import useChampionStore from "../stores/useChampionStore";

interface ChampionImgProps {
  loaded: string;
}

const fadeIn = keyframes`
  from {
    opacity: 0.5;
  }
  to {
    opacity: 1;
  }
`;

const ChampionImg = styled.img<ChampionImgProps>`
  height: 60px;
  width: 60px;
  border-radius: 5px;
  position: absolute;
  top: 0;
  left: 0;
  opacity: ${(props) => (props.loaded === "true" ? 1 : 0)};
  animation: ${(props) => (props.loaded === "true" ? fadeIn : "none")} 0.1s
    ease-in-out;
`;

const LoadingBox = styled.div`
  border-radius: 5px;
  width: 60px;
  height: 60px;
  border-radius: 5px;
  position: absolute;
  top: 0;
  left: 0;
  background: linear-gradient(90deg, #848484 25%, #9e9e9e 50%, #b5b5b5 75%);
  background-size: 200% 100%;
`;

const ChampionBox = styled.div`
  position: relative;
  height: 60px;
  width: 60px;
`;

const Container = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  flex-direction: column;
`;

const DetailBox = styled.div<{ positionAbove: boolean }>`
  width: 100px;
  height: 70px;
  padding: 10px 5px 10px 5px;
  border-radius: 7px;
  background-color: #000000c2;
  position: absolute;
  color: ${({ theme }) => theme.colors.textWhite};
  top: ${({ positionAbove }) => (positionAbove ? "63px" : "-93px")};
  z-index: 1;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  transition: top 0.2s ease-in-out;
`;

interface IChampionProps {
  championId: string;
}

export default function ChampionImage({ championId }: IChampionProps) {
  const champions = useChampionStore((state) => state.champions);
  const [isHover, setIsHover] = useState<boolean>(false);
  const [loaded, setLoaded] = useState<boolean>(false);
  const [positionAbove, setPositionAbove] = useState<boolean>(false);

  const onMouseOver = (event: React.MouseEvent) => {
    setIsHover(true);
    const rect = event.currentTarget.getBoundingClientRect();
    setPositionAbove(rect.top < 120);
  };

  const onMouseOut = () => {
    setIsHover(false);
  };

  return (
    <Container>
      <Link to={`/champion/${championId}`}>
        <ChampionBox>
          {!loaded && <LoadingBox />}
          <ChampionImg
            src={`https://ddragon.leagueoflegends.com/cdn/14.23.1/img/champion/${championId}.png`}
            onMouseOver={onMouseOver}
            onMouseOut={onMouseOut}
            onLoad={() => setLoaded(true)}
            loaded={loaded.toString() as any}
          />
        </ChampionBox>
      </Link>
      {isHover && (
        <DetailBox positionAbove={positionAbove}>
          {String(champions?.[championId as any]?.name || "Unknown Champion")}
        </DetailBox>
      )}
    </Container>
  );
}
