import { useEffect, useState } from "react";
import { getItems, getVersions } from "../../services/Api";
import { IItems } from "./type";
import styled from "styled-components";
import Item from "./Item";

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
export default function Items() {
  const [versions, setVersions] = useState<String[]>();
  const [items, setItems] = useState<IItems>();
  const [isLoading, setIsLoading] = useState<Boolean>(true);
  useEffect(() => {
    const fetchVersionData = async () => {
      try {
        const data = await getVersions();
        setVersions(data);
      } catch (error: any) {
        console.log(error);
      }
    };
    fetchVersionData();
  }, []);
  useEffect(() => {
    const fetchItemData = async () => {
      // versions가 정의되어 있고 첫 번째 요소가 있는지 확인
      if (versions && versions[0]) {
        try {
          const data = await getItems(versions[0]);
          setItems(data as any);
          setIsLoading(false); // 데이터 로딩 완료 후 isLoading 상태를 false로 변경
        } catch (error: any) {
          console.log(error);
        }
      }
    };
    fetchItemData();
  }, [versions]);

  if (isLoading) {
    return <div>loaind...</div>;
  }
  return (
    <ItemsContainer>
      <TitleBox>
        <Title>아이템</Title>
      </TitleBox>
      <ItemContainer>
        {items &&
          Object.values(items.data).map((item, index) => (
            <Item item={item} key={index} />
          ))}
      </ItemContainer>
    </ItemsContainer>
  );
}
