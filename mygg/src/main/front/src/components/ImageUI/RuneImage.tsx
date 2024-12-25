import styled, { keyframes } from "styled-components";
import { useEffect, useState } from "react";
import useRunesStore from "../../stores/useRunesStore";
import { IRuneSlot } from "../../types/type";

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
  background-color: black;
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

const DetailBox = styled.div<{ positionabove: boolean; height: number }>`
  display: flex;
  flex-direction: column;
  min-width: 350px;
  max-width: 400px; // Maximum width before wrapping text
  width: fit-content; // Allow width to adjust based on content: ;
  white-space: normal; // Allow text to wrap
  height: 65px;
  padding: 15px 10px;
  border-radius: 7px;
  background-color: ${({ theme }) => theme.colors.background.dark};
  position: absolute;
  top: ${({ positionabove, height }) =>
    positionabove ? `${height + 3}px` : "-98px"};
  z-index: 1;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  transition: top 0.2s ease-in-out;
`;

const RuneName = styled.div`
  color: ${({ theme }) => theme.colors.brand.gold.main};
  margin-bottom: 10px;
  font-weight: 600;
`;

const RuneDescription = styled.div`
  color: ${({ theme }) => theme.colors.text.white};
  font-size: 12px;
`;

interface IRuneProps {
  styleRuneId: number;
  runeId: number;
  width?: number;
  height?: number;
}

export default function RuneImage({
  styleRuneId,
  runeId,
  width = 28,
  height = 28,
}: IRuneProps) {
  const rune = useRunesStore((state) => state.runes);
  const [isHover, setIsHover] = useState<boolean>(false);
  const [loaded, setLoaded] = useState<boolean>(false);
  const [positionAbove, setPositionAbove] = useState<boolean>(false);
  const [foundRune, setFoundRune] = useState<IRuneSlot>();

  useEffect(() => {
    if (rune) {
      const index = rune.findIndex((item) => item.id === styleRuneId);
      if (index !== -1) {
        const selectedRune = rune[index];
        if (selectedRune?.slots) {
          for (const group of selectedRune.slots) {
            const foundRune = group.runes.find((rune) => rune.id === runeId);
            if (foundRune) {
              setFoundRune(foundRune);
              break;
            }
          }
        }
      }
    }
  }, [rune, runeId, styleRuneId]);

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
              ? `https://ddragon.leagueoflegends.com/cdn/img/${foundRune?.icon}`
              : ""
          }
          onMouseOver={onMouseOver}
          onMouseOut={onMouseOut}
          onLoad={() => setLoaded(true)}
          loaded={loaded.toString() as any}
        />
      </RuneBox>
      {isHover && (
        <DetailBox positionabove={positionAbove} height={height}>
          <RuneName>{foundRune?.name || "Unknown Rune"}</RuneName>
          <RuneDescription>{foundRune?.shortDesc || ""}</RuneDescription>
        </DetailBox>
      )}
    </Container>
  );
}
