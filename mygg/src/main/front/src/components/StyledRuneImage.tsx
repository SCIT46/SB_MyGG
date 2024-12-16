import styled, { keyframes } from "styled-components";
import { useEffect, useState } from "react";
import useRunesStore from "../stores/useRunesStore";

interface RuneImgProps {
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

const RuneImg = styled.img<RuneImgProps>`
  width: ${({ width }) => width}px;
  height: ${({ height }) => height}px;
  border-radius: 100%;
  position: absolute;
  top: 0;
  left: 0;
  opacity: ${(props) => (props.loaded === "true" ? 1 : 0)};
  animation: ${(props) => (props.loaded === "true" ? fadeIn : "none")} 0.1s
    ease-in-out;
`;

const LoadingBox = styled.div<{ width: number; height: number }>`
  border-radius: 100%;
  width: ${({ width }) => width}px;
  height: ${({ height }) => height}px;
  position: absolute;
  top: 0;
  left: 0;
  background: linear-gradient(90deg, #848484 25%, #9e9e9e 50%, #b5b5b5 75%);
  background-size: 200% 100%;
`;

const RuneBox = styled.div<{ width: number; height: number }>`
  position: relative;
  width: ${({ width }) => width}px;
  height: ${({ height }) => height}px;
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
  background-color: ${({ theme }) => theme.colors.background.dark};
  color: ${({ theme }) => theme.colors.brand.gold.main};
  position: absolute;
  top: ${({ positionAbove, height }) =>
    positionAbove ? `${height + 3}px` : "-49px"};
  z-index: 1;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  transition: top 0.2s ease-in-out;
`;

interface IRuneProps {
  runeId: number;
  width?: number;
  height?: number;
}

export default function StyledRuneImage({
  runeId,
  width = 28,
  height = 28,
}: IRuneProps) {
  const rune = useRunesStore((state) => state.runes);
  const [isHover, setIsHover] = useState<boolean>(false);
  const [loaded, setLoaded] = useState<boolean>(false);
  const [positionAbove, setPositionAbove] = useState<boolean>(false);
  const [styleRuneIndex, setStyleRuneIndex] = useState<number>(0);
  useEffect(() => {
    if (rune) {
      setStyleRuneIndex(rune.findIndex((item) => item.id === runeId));
    }
  }, [rune, runeId]);

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
      <RuneBox width={width} height={height}>
        {!loaded && <LoadingBox width={width} height={height} />}
        <RuneImg
          width={width}
          height={height}
          src={
            rune
              ? `https://ddragon.leagueoflegends.com/cdn/img/${rune[styleRuneIndex].icon}`
              : ""
          }
          onMouseOver={onMouseOver}
          onMouseOut={onMouseOut}
          onLoad={() => setLoaded(true)}
          loaded={loaded.toString() as any}
        />
      </RuneBox>
      {isHover && (
        <DetailBox positionAbove={positionAbove} height={height}>
          {rune?.[styleRuneIndex].name || "Unknown Rune"}
        </DetailBox>
      )}
    </Container>
  );
}
