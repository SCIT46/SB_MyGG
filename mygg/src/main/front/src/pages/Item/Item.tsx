import styled, { keyframes } from "styled-components";
import { IItem } from "./type";
import { useState } from "react";
import { Link } from "react-router-dom";

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
  height: 50px;
  width: 50px;
  border-radius: 5px;
  position: absolute;
  top: 0;
  left: 0;
  opacity: ${(props) => (props.loaded === "true" ? 1 : 0)};
  animation: ${(props) => (props.loaded === "true" ? fadeIn : "none")} 0.1s
    ease-in-out;
`;

const LoadingBox = styled.div`
  height: 50px;
  width: 50px;
  border-radius: 5px;
  position: absolute;
  top: 0;
  left: 0;
  background: linear-gradient(90deg, #848484 25%, #9e9e9e 50%, #b5b5b5 75%);
  background-size: 200% 100%;
`;

const ItemBox = styled.div`
  position: relative;
  width: 50px;
  height: 50px;
`;

const Container = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  flex-direction: column;
`;

const DetailBox = styled.div`
  width: 100px;
  height: 70px;
  padding: 10px 5px 10px 5px;
  border-radius: 7px;
  background-color: #000000c2;
  position: absolute;
  color: ${({ theme }) => theme.colors.textWhite};
  top: 50px;
  z-index: 1;
`;

interface IItemProps {
  item: IItem;
  itemId: string;
}

export default function Item({ item, itemId }: IItemProps) {
  const [isHover, setIsHover] = useState<boolean>(false);
  const [loaded, setLoaded] = useState<boolean>(false);

  const onMouseOver = () => {
    setIsHover(true);
  };

  const onMouseOut = () => {
    setIsHover(false);
  };

  return (
    <Container>
      <Link to={`/item/${itemId}`}>
        <ItemBox>
          {!loaded && <LoadingBox />}
          <ItemImg
            src={`https://ddragon.leagueoflegends.com/cdn/14.21.1/img/item/${item.image.full}`}
            onMouseOver={onMouseOver}
            onMouseOut={onMouseOut}
            onLoad={() => setLoaded(true)}
            loaded={loaded.toString() as any}
          />
        </ItemBox>
      </Link>
      {isHover && (
        <DetailBox>
          {item.name}, {item.image.full}
        </DetailBox>
      )}
    </Container>
  );
}
