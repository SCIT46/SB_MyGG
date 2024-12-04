import styled, { keyframes } from "styled-components";
import { useState } from "react";
import { Link } from "react-router-dom";
import useSummonerStore from "../stores/useSummonerStore"; // Assuming a similar store for summoners
import { SummonerSpellDictionary } from "../utils/SummonerObj";

interface SummonerImgProps {
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

const SummonerImg = styled.img<SummonerImgProps>`
  width: 28px;
  height: 28px;
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
  width: 28px;
  height: 28px;
  position: absolute;
  top: 0;
  left: 0;
  background: linear-gradient(90deg, #848484 25%, #9e9e9e 50%, #b5b5b5 75%);
  background-size: 200% 100%;
`;

const SummonerBox = styled.div`
  position: relative;
  width: 28px;
  height: 28px;
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
  padding: 10px 5px;
  border-radius: 7px;
  background-color: #000000c2;
  color: ${({ theme }) => theme.colors.textWhite};
  position: absolute;
  top: ${({ positionAbove }) => (positionAbove ? "32px" : "-94px")};
  z-index: 1;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  transition: top 0.2s ease-in-out;
`;

interface ISummonerProps {
  summonerId: string;
}

export default function SummonerImage({ summonerId }: ISummonerProps) {
  const summoner = useSummonerStore((state) => state.summoner);
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
        <SummonerBox>
          {!loaded && <LoadingBox />}
          <SummonerImg
            src={`https://ddragon.leagueoflegends.com/cdn/14.23.1/img/spell/${
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
        <DetailBox positionAbove={positionAbove}>
          {String(
            summoner?.[SummonerSpellDictionary[summonerId as string] as any]
              ?.name || "Unknown Summoner"
          )}
        </DetailBox>
      )}
    </Container>
  );
}
