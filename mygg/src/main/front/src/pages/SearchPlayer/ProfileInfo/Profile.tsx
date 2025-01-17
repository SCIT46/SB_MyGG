import styled from "styled-components";
import useCurrentVersionStore from "../../../stores/useCurrentVersionStore";
import { updateMatch, updateUser } from "../../../services/riotDateService";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import useMatchRefreshStore from "../../../stores/useMatchRefreshStore";

const ProfileContainer = styled.div`
  margin-top: 30px;
  width: 1000px;
  height: 150px;
  background-color: ${({ theme }) => theme.colors.background.white};
  border: 1px ${({ theme }) => theme.colors.border.main} solid;
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

const UserInfoContainer = styled.div`
  height: 60px;
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

const DetailContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

const ButtonContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 5px;
`;

const RefreshButton = styled.div`
  cursor: pointer;
  width: 80px;
  height: 30px;
  background-color: ${({ theme }) => theme.colors.brand.sky.main};
  color: ${({ theme }) => theme.colors.text.white};
  font-size: 12px;
  font-weight: 500;
  border-radius: 5px;
  display: flex;
  align-items: center;
  justify-content: center;
  &:hover {
    background-color: ${({ theme }) => theme.colors.brand.sky.dark};
  }
`;

const LoadingButton = styled(RefreshButton)`
  cursor: not-allowed;
  background-color: ${({ theme }) => theme.colors.text.disabled};
  &:hover {
    background-color: ${({ theme }) => theme.colors.text.disabled};
  }
`;

const LastUpdateDateSpan = styled.div`
  font-size: 12px;
  color: ${({ theme }) => theme.colors.text.light};
`;

interface IProfileProps {
  profileIconId: number;
  gameName: string;
  tagLine: string;
  summonerLevel: number;
  lastUpdateDate: string;
}

export default function Profile({
  profileIconId,
  gameName,
  tagLine,
  summonerLevel,
  lastUpdateDate,
}: IProfileProps) {
  const version = useCurrentVersionStore((state) => state.version);
  const [isLoading, setIsLoading] = useState(false);
  const [isRecentRenewed, setIsRecentRenewed] = useState(false);
  const { setRefreshKey } = useMatchRefreshStore();
  const navigate = useNavigate();

  const handleUpdateMatch = () => {
    setIsLoading(true);

    updateMatch(gameName, tagLine).then((data) => {
      data.status === 500 && navigate("/");
      updateUser(gameName, tagLine).then((data) => {
        setRefreshKey();
        setIsLoading(false);
      });
    });
  };
  // 유저 정보 업데이트
  return (
    <ProfileContainer>
      <ProfileImgBox>
        <ProfileImg
          src={`https://ddragon.leagueoflegends.com/cdn/${version}/img/profileicon/${profileIconId}.png`}
        />
        <LevelBox>{summonerLevel}</LevelBox>
      </ProfileImgBox>
      <DetailContainer>
        <UserInfoContainer>
          <NameSpan>{gameName}</NameSpan>
          <TagSpan>#{tagLine}</TagSpan>
        </UserInfoContainer>
        <ButtonContainer>
          {isLoading ? (
            <LoadingButton>로딩중...</LoadingButton>
          ) : (
            <RefreshButton onClick={handleUpdateMatch}>전적 갱신</RefreshButton>
          )}
          <LastUpdateDateSpan>
            {(() => {
              if (!lastUpdateDate) return "버튼을 눌러, 전적을 갱신해보세요!";
              const now = new Date();
              const lastUpdate = new Date(lastUpdateDate);
              const diffInMinutes = Math.floor(
                (now.getTime() - lastUpdate.getTime()) / (1000 * 60)
              );
              if (diffInMinutes < 2) return "방금 전 갱신되었습니다!";
              if (diffInMinutes < 60) return `${diffInMinutes}분 전 갱신`;
              const diffInHours = Math.floor(diffInMinutes / 60);
              if (diffInHours < 24) return `${diffInHours}시간 전 갱신`;

              const diffInDays = Math.floor(diffInHours / 24);
              return `${diffInDays}일 전 갱신`;
            })()}
          </LastUpdateDateSpan>
        </ButtonContainer>
      </DetailContainer>
    </ProfileContainer>
  );
}
