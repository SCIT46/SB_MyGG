import { useEffect, useState } from "react";
import { getItems, getVersions } from "../../services/Api";
import { IItems } from "./type";
import styled from "styled-components";
import Item from "./Item";
import LoadingSpinner from "../../components/Loading";

const LoadingContainer = styled.div`
  margin-top: 300px;
`;

const TitleBox = styled.div`
  margin-top: 10px;
  margin-bottom: 10px;
  width: 60vw;
`;

const Title = styled.div`
  color: black;
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
  const [items, setItems] = useState<IItems | null>(null);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  //fetch function
  useEffect(() => {
    const fetchData = async () => {
      setIsLoading(true);
      try {
        const versionsData = await getVersions();
        if (versionsData && versionsData[0]) {
          const itemsData = await getItems(versionsData[0]);
          setItems(itemsData as any);
        }
      } catch (error: any) {
        console.error(error);
        setError("데이터를 불러오는 중 오류가 발생했습니다.");
      } finally {
        setIsLoading(false);
      }
    };
    fetchData();
  }, []);
  //fetch error
  //loading
  if (isLoading) {
    return (
      <LoadingContainer>
        <LoadingSpinner />
      </LoadingContainer>
    );
  }
  if (error) {
    return <div>{error}</div>;
  }
  //item not found
  if (!items) {
    return <div>아이템 데이터를 찾을 수 없습니다.</div>;
  }

  //items 페이지 랜더링
  return (
    <ItemsContainer>
      <TitleBox>
        <Title>아이템</Title>
      </TitleBox>
      <ItemContainer>
        {items &&
          Object.entries(items.data)
            .filter(([key, item]) => item.maps && item.maps["11"] === true)
            .filter(([key, item]) => item.inStore === undefined)
            .map(([key, item], index) => (
              <Item item={item} itemId={key} key={index} />
            ))}
      </ItemContainer>
    </ItemsContainer>
  );
}
