import styled from "styled-components";
import Profile from "./Profile/Profile";
import Rank from "./Rank";
import { useEffect, useState } from "react";
import { getUser } from "../../services/Api";
import { IUser } from "./type";
import LoadingSpinner from "../../components/Loading";
import { useNavigate, useParams } from "react-router-dom";
import Match from "./Match/Match";
import ChampInfo from "./Champion/ChampInfo";

const SearchPlayerContainer = styled.div`
  width: 100vw;
  display: flex;
  align-items: center;
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

// '/search/:id 라우트 이동시 랜더링 되는 컴포넌트
export default function SearchPlayer() {
  const { id } = useParams();
  const [user, setUser] = useState<IUser>();
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      try {
        if (!id) throw new Error("id parameter is missing");
        const [param1, param2] = id.split("-");
        if (!param1 || !param2) throw new Error("Invalid id format");
        const data = (await getUser(param1, param2)) as { user: IUser };
        setUser(data.user);
      } catch (error) {
        console.error("user fetch error!", error);
        navigate("/not-found");
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, [id]);

  if (isLoading)
    return (
      <SearchPlayerContainer>
        <LoadingSpinner />
      </SearchPlayerContainer>
    );

  //todo: 유저 정보가 없을 경우 처리
  if (!user) {
    return <SearchPlayerContainer>유저 정보가 없습니다.</SearchPlayerContainer>;
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
        <Match matchList={user?.matchList} puuid={user?.puuid} />
      </BottomContainer>
    </SearchPlayerContainer>
  );
}
