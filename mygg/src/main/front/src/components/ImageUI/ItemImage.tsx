import styled, { keyframes } from "styled-components";
import { useState, useRef, useEffect } from "react";
import { Link } from "react-router-dom";
import useItemStore from "../../stores/useItemStore"; // Assuming a similar store for items
import useCurrentVersionStore from "../../stores/useCurrentVersionStore";
import CustomHtmlRenderer from "../../components/CustomHtmlRenderer";

interface ItemImgProps {
  loaded: string;
  width: number;
  height: number;
  istrinket: boolean;
}

const fadeIn = keyframes`
  from {
    opacity: 0.5;
  }
  to {
    opacity: 1;
  }
`;

const ItemImg = styled.img<ItemImgProps>`
  width: ${({ width }) => width}px;
  height: ${({ height }) => height}px;
  border-radius: ${({ istrinket }) => (istrinket ? "100%" : "5px")};
  position: absolute;
  top: 0;
  left: 0;
  opacity: ${(props) => (props.loaded === "true" ? 1 : 0)};
  animation: ${(props) => (props.loaded === "true" ? fadeIn : "none")} 0.1s
    ease-in-out;
`;

const LoadingBox = styled.div<{
  width: number;
  height: number;
  istrinket: boolean;
}>`
  border-radius: ${({ istrinket }) => (istrinket ? "100%" : "5px")};
  width: ${({ width }) => width}px;
  height: ${({ height }) => height}px;
  position: absolute;
  top: 0;
  left: 0;
  background: linear-gradient(90deg, #848484 25%, #9e9e9e 50%, #b5b5b5 75%);
  background-size: 200% 100%;
`;

const ItemBox = styled.div<{ width: number; height: number }>`
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
  width: 350px;
  height: fit-content;
  padding: 15px 10px;
  border-radius: 7px;
  background-color: ${({ theme }) => theme.colors.background.dark};
  position: absolute;
  color: ${({ theme }) => theme.colors.text.primary};
  top: ${({ positionabove, height }) =>
  positionabove ? `-${height + 3}px` : `calc(100% + 3px)`};
  z-index: 1;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
`;

const NullItemBox = styled.div<{ width: number; height: number }>`
  width: ${({ width }) => width}px;
  height: ${({ height }) => height}px;
  background-color: #85858570;
  border-radius: 5px;
`;

const ItemName = styled.div`
  color: ${({ theme }) => theme.colors.brand.sky.main};
  margin-bottom: 10px;
  font-weight: 600;
`;

const ItemDescription = styled.div`
  line-height: 1.2;
  font-size: 12px;
  margin-bottom: 10px;
  font-weight: 400;
  color: ${({ theme }) => theme.colors.text.white};
`;

const ItemGold = styled.div`
  font-size: 12px;
  font-weight: 600;
  color: ${({ theme }) => theme.colors.brand.gold.main};
`;

interface IItemProps {
  itemId: number;
  width?: number;
  height?: number;
  isTrinket?: boolean;
}

export default function ItemImage({
  itemId,
  isTrinket = false,
  width = 28,
  height = 28,
}: IItemProps) {
  const items = useItemStore((state) => state.items);
  const version = useCurrentVersionStore((state) => state.version);
  const [isHover, setIsHover] = useState<boolean>(false);
  const [loaded, setLoaded] = useState<boolean>(false);
  const [positionAbove, setPositionAbove] = useState<boolean>(false);
  const detailBoxRef = useRef<HTMLDivElement | null>(null);
  const [detailBoxHeight, setDetailBoxHeight] = useState<number>(0);

  useEffect(() => {
    if (detailBoxRef.current) {
      setDetailBoxHeight(detailBoxRef.current.offsetHeight);
    }
  }, [isHover]);

  const onMouseOver = (event: React.MouseEvent) => {
    setIsHover(true);
    const rect = event.currentTarget.getBoundingClientRect();
    setPositionAbove(window.innerHeight - rect.bottom < detailBoxHeight + 120);
  };

  const onMouseOut = () => {
    setIsHover(false);
  };

  if (itemId === 0) {
    return <NullItemBox width={width} height={height} />;
  }
  return (
    <Container>
      <Link to={`/item/${itemId}`}>
        <ItemBox width={width} height={height}>
          {!loaded && (
            <LoadingBox width={width} height={height} istrinket={isTrinket} />
          )}
          <ItemImg
            width={width}
            height={height}
            istrinket={isTrinket}
            src={`https://ddragon.leagueoflegends.com/cdn/${version}/img/item/${itemId}.png`}
            onMouseOver={onMouseOver}
            onMouseOut={onMouseOut}
            onLoad={() => setLoaded(true)}
            loaded={loaded.toString()}
          />
        </ItemBox>
      </Link>
      {isHover && (
        <DetailBox
          ref={detailBoxRef}
          positionabove={positionAbove}
          height={detailBoxHeight}
        >
          <ItemName>
            {String(items?.[itemId as any]?.name || "Unknown Item")}
          </ItemName>
          <ItemDescription>
            <CustomHtmlRenderer htmlString={items?.[itemId as any]?.description || ""} />
          </ItemDescription>
          <ItemGold>
            {String(items?.[itemId as any]?.gold.total || "")}
          </ItemGold>
        </DetailBox>
      )}
    </Container>
  );
}
