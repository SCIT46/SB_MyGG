import styled from "styled-components";
import Profile from "./Profile";
import Rank from "./Rank";
import ChampInfo from "./ChampInfo";
import Match from "./Match";
import { useEffect, useState } from "react";
import { getUser } from "../../services/Api";

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

const LeftConationer = styled.div`
  width: 350px;
`;

// '/search/:id 라우트 이동시 랜더링 되는 컴포넌트
export default function SearchPlayer() {
  const [user, setUser] = useState();
  useEffect(() => {
    const fetchData = async () => {
      try {
        const data: any = await getUser();
        setUser(data);
        console.log(data);
      } catch (error) {
        console.error("champions fetch error!", error);
      }
    };

    fetchData();
  }, [getUser]);

  return (
    <SearchPlayerContainer>
      <Profile />
      <BottomContainer>
        <LeftConationer>
          <Rank />
          <ChampInfo />
        </LeftConationer>
        <Match />
      </BottomContainer>
    </SearchPlayerContainer>
  );
}
