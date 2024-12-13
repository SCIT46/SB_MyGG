import { useEffect, useState } from "react";
import { getItems, getVersions } from "../../services/Api";
import { IItems } from "./type";
import styled from "styled-components";
import Item from "./Item";
import LoadingSpinner from "../../components/Loading";
import useItemStore from "../../stores/useItemStore";
import ItemImage from "../../components/ItemImage";

const LoadingContainer = styled.div`
  margin-top: 300px;
`;

const TitleBox = styled.div`
  margin-top: 50px;
  width: 60vw;
`;

const TitleLine = styled.div`
  margin-top: 25px;
  margin-bottom: 17px;

  width: 60vw;
  height: 3px;
  background-color: ${({ theme }) => theme.colors.primaryGold};
`;

const Title = styled.div`
  color: ${({ theme }) => theme.colors.lightText};
  font-weight: 600;
  font-size: 24px;
`;

const ItemsContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100vw;
`;

const ItemContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  width: 60vw;
  gap: 7px;
`;

// '/item' 라우트 이동시 랜더링 되는 컴포넌트
export default function Itempage() {
  const items = useItemStore((state) => state.items);
  console.log(items);
  return (
    <ItemsContainer>
      <TitleBox>
        <Title>아이템 분석</Title>
      </TitleBox>
      <TitleLine />
      <ItemContainer>
        {items &&
          Object.entries(items).map(([key, value]) => (
            <ItemImage itemId={Number(key)} key={key} width={50} height={50} />
          ))}
      </ItemContainer>
    </ItemsContainer>
  );
}
