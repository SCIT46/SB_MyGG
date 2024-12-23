import styled from "styled-components";
import useCurrentVersionStore from "../../../stores/useCurrentVersionStore";

const ProfileContainer = styled.div`
  margin-top: 30px;
  width: 1000px;
  height: 150px;
  background-color: ${({ theme }) => theme.colors.background.white};
  border: 1px ${({ theme }) => theme.colors.brand.gold.main} solid;
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
  align-items: center;
  margin-bottom: auto;
`;

const NameSpan = styled.div`
  font-size: 24px;
  font-weight: 700;
  color: ${({ theme }) => theme.colors.text.primary};
  display: inline-block;
  margin-right: 10px;
`;

const LevelBox = styled.div`
  color: ${({ theme }) => theme.colors.text.white};
  font-size: 14px;
  background-color: ${({ theme }) => theme.colors.background.dark};
  border-radius: 7px;
  padding: 5px 7px;
  margin-top: -12px;
`;

const TagSpan = styled.span`
  color: ${({ theme }) => theme.colors.text.light};
  margin-top: 2px;
  font-weight: 500;
  font-size: 18px;
`;

const ProfileImgBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: -10px;
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
      <ProfileImgBox>
        <ProfileImg
          src={`https://ddragon.leagueoflegends.com/cdn/${version}/img/profileicon/${profileIconId}.png`}
        />
        <LevelBox>{summonerLevel}</LevelBox>
      </ProfileImgBox>
      <DetailContainer>
        <NameSpan>{gameName}</NameSpan>
        <TagSpan>#{tagLine}</TagSpan>
      </DetailContainer>
    </ProfileContainer>
  );
}
