import styled from "styled-components";
import { IItem, IItems } from "./type";
import { useState } from "react";

const ItemContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  width: 70vw;
  gap: 7px;
`;

const ItemImg = styled.img`
  height: 50px;
  width: 50px;
  border-radius: 5px;
`;

const ItemBox = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  flex-direction: column;
`;

const DetailBox = styled.div`
  width: 70px;
  height: 70px;
  background-color: #000000c2;
  position: absolute;
  top: 50px;
  z-index: 1;
`;

interface IItemProps {
  item: IItem;
}

export default function Item({ item }: IItemProps) {
  const [isHover, setIsHover] = useState<boolean>(false);
  const onMouseOver = () => {
    setIsHover(true);
  };
  const onMouseOut = () => {
    setIsHover(false);
  };
  return (
    <ItemBox>
      <ItemImg
        src={`https://ddragon.leagueoflegends.com/cdn/14.21.1/img/item/${item.image.full}`}
        alt={item.name}
        onMouseOver={onMouseOver}
        onMouseOut={onMouseOut}
      />
      {isHover && <DetailBox>{item.name}</DetailBox>}
    </ItemBox>
  );
}
