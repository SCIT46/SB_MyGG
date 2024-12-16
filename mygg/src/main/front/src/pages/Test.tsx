import { MagnifyingGlassIcon } from "@heroicons/react/20/solid";
import styled, { keyframes } from "styled-components";

import RuneImage from "../components/RuneImage";
import StyledRuneImage from "../components/StyledRuneImage";
import SummonerImage from "../components/SummonerImage";

const SearchIcon = styled(MagnifyingGlassIcon)`
  width: 20px;
  height: 20px;
`;

const GradientBackground = styled.div`
  margin: 0;
  min-height: 100vh;

  color: #333;
  font-family: sans-serif;
`;

const Spinner = styled.div`
  height: 30px;
  width: 30px;
  border: 1px solid ${({ theme }) => theme.colors.brand.sky.main};
  border-radius: 50%;
  border-top: none;
  border-right: none;
  margin: 16px auto;
`;

//테스트 페이지
export default function Test() {
  // //api fetching
  // useEffect(() => {
  //   const ApiData = async () => {
  //     try {
  //       const data = await getHello();
  //       setMessage(data as any);
  //     } catch (error: any) {
  //       console.log(error);
  //     }
  //   };
  //   ApiData();
  // }, []);

  return (
    <GradientBackground>
      <SearchIcon />
      <Spinner />
      {/* <ChampionImage championId="Ahri" />
      <SummonerImage summonerId="21" /> */}
      <RuneImage styleRuneId={8300} runeId={8351} />
      <StyledRuneImage runeId={8300} />
      <SummonerImage summonerId="21" />
    </GradientBackground>
  );
}
