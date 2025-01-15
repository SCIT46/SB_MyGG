import styled from "styled-components";
import Profile from "./ProfileInfo/Profile";
import { useEffect, useState } from "react";
import { IUser } from "./type";
import LoadingSpinner from "../../components/LoadingSpinner";
import { useParams } from "react-router-dom";
import Match from "./MatchInfo/Match";
import ChampInfo from "./ChampionInfo/ChampInfo";
import Rank from "./RankInfo/Rank";
import { Link } from "react-router-dom";
import { getUser } from "../../services/riotDateService";

const SearchPlayerContainer = styled.div`
  width: 100vw;
  display: flex;
  align-items: center;
  flex-direction: column;
`;

const LoadingContainer = styled.div`
  width: 100vw;
  height: 80vh;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
`;

const BottomContainer = styled.div`
  margin-top: 20px;
  width: 1000px;
  display: flex;
  gap: 15px;
`;

const LeftContainer = styled.div`
  width: 350px;
`;

const NotFoundBox = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
  margin-bottom: 1rem;
`;

const NotFoundNameSpan = styled.span`
  font-size: 2rem;
  font-weight: 600;
  color: ${({ theme }) => theme.colors.brand.sky.main};
`;

const NotFoundSpan = styled.span`
  font-size: 1.5rem;
  color: ${({ theme }) => theme.colors.text.light};
  font-weight: 400;
`;

const NotFoundLinkSpan = styled.span`
  font-size: 1rem;
  color: ${({ theme }) => theme.colors.brand.sky.main};

  font-weight: 600;
`;

interface IUserInfo {
  gameName: string;
  tagLine: string;
}

// '/search/:id 라우트 이동시 랜더링 되는 컴포넌트
export default function SearchPlayerPage() {
  const { id } = useParams();
  const [user, setUser] = useState<IUser>();
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [userInfo, setUserInfo] = useState<IUserInfo>({
    gameName: "",
    tagLine: "",
  });

  useEffect(() => {
    const fetchData = async () => {
      try {
        if (!id) throw new Error("id parameter is missing");
        const [param1, param2] = id.split("-");
        setUserInfo({ gameName: param1, tagLine: param2 });

        if (!param1 || !param2) throw new Error("Invalid id format");
        const data = (await getUser(param1, param2)) as IUser;
        setUser(data);
      } catch (error) {
        console.error("user fetch error!", error);
        // navigate("/not-found");
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, [id]);

  if (isLoading)
    return (
      <LoadingContainer>
        <LoadingSpinner />
      </LoadingContainer>
    );

  //todo: 유저 정보가 없을 경우 처리
  if (!user) {
    return (
      <LoadingContainer>
        <NotFoundBox>
          <NotFoundNameSpan>
            {userInfo.gameName} #{userInfo.tagLine}
          </NotFoundNameSpan>
          <NotFoundSpan>의 유저 정보를 찾을 수 없습니다!</NotFoundSpan>
        </NotFoundBox>
        <Link to="/">
          <NotFoundLinkSpan>&rarr; 홈으로 돌아가기</NotFoundLinkSpan>
        </Link>
      </LoadingContainer>
    );
  }

  return (
    <SearchPlayerContainer>
      <Profile
        gameName={user?.gameName}
        profileIconId={user?.profileIconId}
        summonerLevel={user?.summonerLevel}
        tagLine={user?.tagLine}
      />
      <BottomContainer>
        <LeftContainer>
          <Rank
            leaguePoints={user?.leaguePoints}
            losses={user?.losses}
            rank={user?.rank}
            tier={user?.tier}
            wins={user?.wins}
          />
          <ChampInfo />
        </LeftContainer>
        <Match
          userName={user?.gameName}
          tagLine={user?.tagLine}
          puuid={user?.puuid}
        />
      </BottomContainer>
    </SearchPlayerContainer>
  );
}
