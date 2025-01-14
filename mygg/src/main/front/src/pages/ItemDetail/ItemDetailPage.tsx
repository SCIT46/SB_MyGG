import { useParams } from "react-router-dom";
import { getItemDetail } from "../../services/riotDateService";
import { useEffect, useState } from "react";
import { IItemDetail } from "../../interfaces/itemType";
import styled from "styled-components";
import ItemImage from "../../components/ImageUI/ItemImage";

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

const ItemDetailStats = styled.div`
  margin-top: 20px;
  font-size: 0.8rem;
  max-width: 500px;
`;

const ItemDetailList = styled.ul`
  list-style-type: none;
  padding: 0;
`;

const ItemDetailListItem = styled.li`
  margin: 5px 0;
`;

const ItemTreeContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 20px;
`;

const ItemTreeArrow = styled.div`
  margin: 0 10px;
  font-size: 1.5rem;
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

      <ItemTreeContainer>
        {itemDetail?.from && itemDetail.from.length > 0 && (
          <>
            {itemDetail.from.map((itemId) => (
              <ItemImage
                key={itemId}
                itemId={Number(itemId)}
                width={50}
                height={50}
              />
            ))}
            <ItemTreeArrow>→</ItemTreeArrow>
          </>
        )}
        <ItemImage itemId={Number(id)} width={50} height={50} />
        {itemDetail?.into && itemDetail.into.length > 0 && (
          <>
            <ItemTreeArrow>→</ItemTreeArrow>
            {itemDetail.into.map((itemId) => (
              <ItemImage
                key={itemId}
                itemId={Number(itemId)}
                width={50}
                height={50}
              />
            ))}
          </>
        )}
      </ItemTreeContainer>

      {itemDetail?.stats && Object.keys(itemDetail.stats).length > 0 && (
        <ItemDetailStats>
          <h3>Stats:</h3>
          <ItemDetailList>
            {Object.entries(itemDetail.stats).map(([key, value]) => (
              <ItemDetailListItem key={key}>
                {key}: {value}
              </ItemDetailListItem>
            ))}
          </ItemDetailList>
        </ItemDetailStats>
      )}
    </ItemDetailContainer>
  );
}
