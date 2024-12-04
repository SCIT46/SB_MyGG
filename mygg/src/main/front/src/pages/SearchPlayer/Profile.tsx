import styled from "styled-components";
import useCurrentVersionStore from "../../stores/useCurrentVersionStore";

const ProfileContainer = styled.div`
  margin-top: 70px;
  width: 1000px;
  height: 150px;
  background-color: ${({ theme }) => theme.colors.backgroundWhite};
  border: 1px ${({ theme }) => theme.colors.primaryGold} solid;
  border-radius: 10px;
  display: flex;
  align-items: center;
`;

const ProfileImg = styled.img`
  margin-left: 25px;
  margin-right: 25px;
  height: 100px;
  width: 100px;
  background-color: #a4a4a4;
  border-radius: 10px;
`;

const DetailContainer = styled.div`
  height: 90px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

const NameSpan = styled.div`
  font-size: 20px;
  font-weight: 600;
  color: ${({ theme }) => theme.colors.textBlack};
`;

const LevelSpan = styled.div`
  color: ${({ theme }) => theme.colors.lightText};
  margin-bottom: 30px;
  font-size: 16px;
`;

interface IProfileProps {
  profileIconId?: number;
  gameName?: string;
  tagLine?: string;
  summonerLevel?: number;
}

export default function Profile({
  profileIconId,
  gameName,
  tagLine,
  summonerLevel,
}: IProfileProps) {
  const version = useCurrentVersionStore((state) => state.version);
  return (
    <ProfileContainer>
      <ProfileImg
        src={`https://ddragon.leagueoflegends.com/cdn/${version}/img/profileicon/${profileIconId}.png`}
      />
      <DetailContainer>
        <NameSpan>
          {gameName} #{tagLine}
        </NameSpan>
        <LevelSpan>{summonerLevel}</LevelSpan>
        <div>전적갱신 버튼</div>
      </DetailContainer>
    </ProfileContainer>
  );
}
