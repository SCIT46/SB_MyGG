import styled, { keyframes } from "styled-components";
import { useState } from "react";
import { Link } from "react-router-dom";
import useSummonerStore from "../stores/useSummonerStore"; // Assuming a similar store for summoners
import { SummonerSpellDictionary } from "../utils/SummonerObj";
import useCurrentVersionStore from "../stores/useCurrentVersionStore";

interface SummonerImgProps {
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

const SummonerImg = styled.img<SummonerImgProps>`
  width: ${({ width }) => width}px;
  height: ${({ height }) => height}px;
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
  position: absolute;
  top: 0;
  left: 0;
  background: linear-gradient(90deg, #848484 25%, #9e9e9e 50%, #b5b5b5 75%);
  background-size: 200% 100%;
`;

const SummonerBox = styled.div<{ width: number; height: number }>`
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
  flex-direction: column;
  min-width: 350px;
  max-width: 400px; // Maximum width before wrapping text
  width: fit-content; // Allow width to adjust based on content: ;
  white-space: normal; // Allow text to wrap
  height: 65px;
  padding: 15px 10px;
  border-radius: 7px;
  background-color: ${({ theme }) => theme.colors.background.dark};
  color: ${({ theme }) => theme.colors.text.white};
  position: absolute;
  top: ${({ positionAbove, height }) =>
    positionAbove ? `${height + 3}px` : "-98px"};
  z-index: 1;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  transition: top 0.2s ease-in-out;
`;

const SpellName = styled.div`
  color: ${({ theme }) => theme.colors.brand.gold.main};
  margin-bottom: 10px;
  font-weight: 600;
`;

const SpellDescription = styled.div`
  color: ${({ theme }) => theme.colors.text.white};
  font-size: 12px;
`;

interface ISummonerProps {
  summonerId: string;
  width?: number;
  height?: number;
}

export default function SummonerImage({
  summonerId,
  width = 28,
  height = 28,
}: ISummonerProps) {
  const summoner = useSummonerStore((state) => state.summoner);
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
      <Link to={`/summoner/${summonerId}`}>
        <SummonerBox width={width} height={height}>
          {!loaded && <LoadingBox width={width} height={height} />}
          <SummonerImg
            width={width}
            height={height}
            src={`https://ddragon.leagueoflegends.com/cdn/${version}/img/spell/${
              SummonerSpellDictionary[summonerId as string]
            }.png`}
            onMouseOver={onMouseOver}
            onMouseOut={onMouseOut}
            onLoad={() => setLoaded(true)}
            loaded={loaded.toString() as any}
          />
        </SummonerBox>
      </Link>
      {isHover && (
        <DetailBox positionAbove={positionAbove} height={height}>
          <SpellName>
            {String(
              summoner?.[SummonerSpellDictionary[summonerId as string] as any]
                ?.name || "Unknown Summoner"
            )}
          </SpellName>
          <SpellDescription>
            {String(
              summoner?.[SummonerSpellDictionary[summonerId as string] as any]
                ?.description || "Unknown Summoner"
            )}
          </SpellDescription>
        </DetailBox>
      )}
    </Container>
  );
}
