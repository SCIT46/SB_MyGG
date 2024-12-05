import styled, { keyframes } from "styled-components";
import { useState } from "react";
import { Link } from "react-router-dom";
import useChampionStore from "../stores/useChampionStore";
import useCurrentVersionStore from "../stores/useCurrentVersionStore";

interface ChampionImgProps {
  loaded: string;
  width: number;
  height: number;
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
  height: ${({ height }) => height}px;
  width: ${({ width }) => width}px;
  border-radius: 5px;
  position: absolute;
  top: 0;
  left: 0;
  opacity: ${(props) => (props.loaded === "true" ? 1 : 0)};
  animation: ${(props) => (props.loaded === "true" ? fadeIn : "none")} 0.1s
    ease-in-out;
`;

const LoadingBox = styled.div<{ width: number; height: number }>`
  border-radius: 5px;
  width: ${({ width }) => width}px;
  height: ${({ height }) => height}px;
  border-radius: 5px;
  position: absolute;
  top: 0;
  left: 0;
  background: linear-gradient(90deg, #848484 25%, #9e9e9e 50%, #b5b5b5 75%);
  background-size: 200% 100%;
`;

const ChampionBox = styled.div<{ width: number; height: number }>`
  position: relative;
  height: ${({ height }) => height}px;
  width: ${({ width }) => width}px;
`;

const Container = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  flex-direction: column;
`;

const DetailBox = styled.div<{ positionAbove: boolean; height: number }>`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 12px;
  height: 20px;
  width: fit-content;
  white-space: nowrap;
  border-radius: 7px;
  background-color: #000000c2;
  position: absolute;
  color: ${({ theme }) => theme.colors.primarySky};
  top: ${({ positionAbove, height }) =>
    positionAbove ? `${height + 3}px` : "-46px"};
  z-index: 1;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  transition: top 0.2s ease-in-out;
`;

interface IChampionProps {
  championId: string;
  width?: number;
  height?: number;
}

export default function ChampionImage({
  championId,
  width = 60,
  height = 60,
}: IChampionProps) {
  const champions = useChampionStore((state) => state.champions);
  const version = useCurrentVersionStore((state) => state.version);
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
        <ChampionBox width={width} height={height}>
          {!loaded && <LoadingBox width={width} height={height} />}
          <ChampionImg
            src={`https://ddragon.leagueoflegends.com/cdn/${version}/img/champion/${championId}.png`}
            onMouseOver={onMouseOver}
            onMouseOut={onMouseOut}
            onLoad={() => setLoaded(true)}
            loaded={loaded.toString() as any}
            width={width}
            height={height}
          />
        </ChampionBox>
      </Link>
      {isHover && (
        <DetailBox positionAbove={positionAbove} height={height}>
          {String(champions?.[championId as any]?.name || "Unknown Champion")}
        </DetailBox>
      )}
    </Container>
  );
}
