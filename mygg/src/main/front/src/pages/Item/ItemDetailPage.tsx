import { useParams } from "react-router-dom";
import { getItemDetail, getItems } from "../../services/Api";
import { useEffect, useState } from "react";
import { IItemDetail } from "./type";
import styled from "styled-components";
import ItemImage from "../../components/ItemImage";

const ItemDetailContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 100px;
`;

const ItemDetailTitle = styled.h1`
  font-size: 1rem;
  font-weight: bold;
`;

const ItemDetailDescription = styled.p`
  font-size: 0.8rem;
  max-width: 500px;
`;

const ItemDetailGold = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

// '/item/:id' 라우트 이동시 랜더링 되는 컴포넌트
export default function ItemDetailPage() {
  const { id } = useParams();
  const [itemDetail, setItemDetail] = useState<IItemDetail | null>(null);

  useEffect(() => {
    const fetchItemDetail = async () => {
      if (id) {
        try {
          const data = await getItemDetail(id);
          const itemData = data[id]; // Access the item using the ID as the key
          setItemDetail(itemData);
        } catch (error) {
          console.error("Failed to fetch item detail:", error);
        }
      }
    };

    fetchItemDetail();
  }, [id]);

  return (
    <ItemDetailContainer>
      <ItemDetailTitle>{itemDetail?.name}</ItemDetailTitle>
      <ItemDetailDescription>{itemDetail?.description}</ItemDetailDescription>
      <ItemDetailGold>
        <span>가격: {itemDetail?.gold.total}</span>
        <span>판매가격: {itemDetail?.gold.sell}</span>
      </ItemDetailGold>
      <ItemImage itemId={Number(id)} width={100} height={100} />
    </ItemDetailContainer>
  );
}
