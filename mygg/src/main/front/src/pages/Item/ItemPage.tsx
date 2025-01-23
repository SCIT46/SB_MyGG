import styled from "styled-components";
import useItemStore from "../../stores/useItemStore";
import ItemImage from "../../components/ImageUI/ItemImage";

const TitleBox = styled.div`
  margin-top: 50px;
  width: 60vw;
`;

const TitleLine = styled.div`
  margin-top: 25px;
  margin-bottom: 17px;

  width: 60vw;
  height: 3px;
  background-color: ${({ theme }) => theme.colors.brand.gold.main};
`;

const Title = styled.div`
  color: ${({ theme }) => theme.colors.text.primary};
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
export default function ItemPage() {
  const items = useItemStore((state) => state.items);
  return (
    <ItemsContainer>
      <TitleBox>
        <Title>아이템 분석</Title>
      </TitleBox>
      <TitleLine />
      <ItemContainer>
        {items &&
          Object.entries(items).map(
            ([key, value]) =>
              value.maps["11"] &&
              value.gold.purchasable === true && (
                <ItemImage
                  itemId={Number(key)}
                  key={key}
                  width={50}
                  height={50}
                />
              )
          )}
      </ItemContainer>
    </ItemsContainer>
  );
}
