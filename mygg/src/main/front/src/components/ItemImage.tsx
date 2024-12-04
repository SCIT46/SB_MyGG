import styled, { keyframes } from "styled-components";
import { useState } from "react";
import { Link } from "react-router-dom";
import useItemStore from "../stores/useItemStore"; // Assuming a similar store for items

interface ItemImgProps {
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

const ItemImg = styled.img<ItemImgProps>`
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

const ItemBox = styled.div`
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
  padding: 10px 5px 10px 5px;
  border-radius: 7px;
  background-color: #000000c2;
  position: absolute;
  color: ${({ theme }) => theme.colors.textWhite};
  top: ${({ positionAbove }) => (positionAbove ? "-94px" : "32px")};
  z-index: 1;
`;

const NullItemBox = styled.div`
  width: 28px;
  height: 28px;
  background-color: #85858570;
  border-radius: 5px;
`;

interface IItemProps {
  itemId: number;
}

export default function ItemImage({ itemId }: IItemProps) {
  const items = useItemStore((state) => state.items);
  const [isHover, setIsHover] = useState<boolean>(false);
  const [loaded, setLoaded] = useState<boolean>(false);
  const [positionAbove, setPositionAbove] = useState<boolean>(false);

  const onMouseOver = (event: React.MouseEvent) => {
    setIsHover(true);
    const rect = event.currentTarget.getBoundingClientRect();
    setPositionAbove(window.innerHeight - rect.bottom < 120); // 커서가 아래쪽에 있을 때 위로 위치
  };

  const onMouseOut = () => {
    setIsHover(false);
  };

  if (itemId === 0) {
    return <NullItemBox />;
  }
  return (
    <Container>
      <Link to={`/item/${itemId}`}>
        <ItemBox>
          {!loaded && <LoadingBox />}
          <ItemImg
            src={`https://ddragon.leagueoflegends.com/cdn/14.23.1/img/item/${itemId}.png`}
            onMouseOver={onMouseOver}
            onMouseOut={onMouseOut}
            onLoad={() => setLoaded(true)}
            loaded={loaded.toString() as any}
          />
        </ItemBox>
      </Link>
      {isHover && (
        <DetailBox positionAbove={positionAbove}>
          {String(items?.[itemId as any]?.name || "Unknown Item")}
        </DetailBox>
      )}
    </Container>
  );
}
