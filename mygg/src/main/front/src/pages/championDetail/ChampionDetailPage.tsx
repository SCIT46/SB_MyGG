import { useParams } from "react-router-dom";
import { getChampionDetail } from "../../services/riotDateService";
import { useEffect, useState } from "react";
import { IChampionDetail } from "../../interfaces/championType";
import styled from "styled-components";

const ChampionDetailContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const ChampionDetailWrapper = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  max-width: 60rem;
  height: 18rem;
  background: #000;
  border-radius: 1rem;
  @media (max-width: 60rem) {
    width: 80%;
    height: 12rem;
  }
`;

const ChampionDetailImage = styled.img`
  position: absolute;
  object-fit: cover;
  width: 100%;
  height: 100%;

  object-position: 50% 20%;
  opacity: 0.7;
  border-radius: 1rem;
  box-shadow: 0 0 1rem rgba(0, 0, 0, 0.5);

  @media (max-width: 60rem) {
    width: 100%;
    height: 12rem;
  }
`;

const ChampionNameSpan = styled.span`
  position: absolute;
  bottom: 6.5rem;
  left: 2rem;
  font-size: 3.5rem;
  font-weight: 600;
  font-style: italic;
  text-shadow: 0 0 1rem rgba(0, 0, 0, 0.5);
  color: ${({ theme }) => theme.colors.text.white};

  @media (max-width: 60rem) {
    bottom: 4.5rem;
    left: 1rem;
    font-size: 2rem;
  }
`;

const ChampionTitleSpan = styled.span`
  position: absolute;
  bottom: 11rem;
  left: 2.5rem;
  font-size: 1.5rem;
  font-weight: 600;
  font-style: italic;
  text-shadow: 0 0 1rem rgba(0, 0, 0, 0.5);
  color: ${({ theme }) => theme.colors.brand.gold.dark};

  @media (max-width: 60rem) {
    bottom: 7rem;
    left: 1rem;
    font-size: 1.2rem;
  }
`;

const SkillImgContainer = styled.div`
  position: absolute;
  bottom: 2rem;
  left: 2.8rem;
  display: flex;
  gap: 0.4rem;

  @media (max-width: 60rem) {
    bottom: 1rem;
    left: 1rem;
  }
`;

const SkillImg = styled.img`
  width: 3rem;
  height: 3rem;
  border-radius: 50%;
  border: 1px solid ${({ theme }) => theme.colors.brand.gold.dark};
  box-shadow: 0 0 1rem rgba(0, 0, 0, 0.5);

  @media (max-width: 60rem) {
    width: 2rem;
    height: 2rem;
    border-radius: 1rem;
  }
`;

const ChampionDescriptionContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  max-width: 60rem;

  @media (max-width: 60rem) {
    width: 80%;
  }
`;

const ChampionDescriptionContent = styled.p`
  font-size: 1.5rem;
  font-weight: 400;
  color: ${({ theme }) => theme.colors.text.secondary};
`;

// '/champion/:id' 라우트 이동시 랜더링 되는 컴포넌트
export default function ChampionDetailPage() {
  const { id } = useParams();
  const [isOpen, setIsOpen] = useState(false);
  console.log(isOpen);

  const [championDetail, setChampionDetail] = useState<IChampionDetail | null>(
    null
  );

  useEffect(() => {
    const fetchChampionDetail = async () => {
      if (id) {
        const data = await getChampionDetail(id);
        setChampionDetail(data[id]);
      }
    };
    fetchChampionDetail();
  }, [id]);

  console.log(championDetail);
  return (
    <ChampionDetailContainer>
      <ChampionDetailWrapper onClick={() => setIsOpen(!isOpen)}>
        <ChampionDetailImage
          src={`https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${championDetail?.id}_0.jpg`}
          alt={championDetail?.name}
        />
        <ChampionTitleSpan>{championDetail?.title}</ChampionTitleSpan>
        <ChampionNameSpan>{championDetail?.name}</ChampionNameSpan>
        <SkillImgContainer>
          {championDetail?.spells.map((spell) => (
            <SkillImg
              src={`https://ddragon.leagueoflegends.com/cdn/14.1.1/img/spell/${spell.id}.png`}
              alt={spell.name}
            />
          ))}
        </SkillImgContainer>
      </ChampionDetailWrapper>
    </ChampionDetailContainer>
  );
}
