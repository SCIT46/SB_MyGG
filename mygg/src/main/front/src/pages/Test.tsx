import { MagnifyingGlassIcon } from "@heroicons/react/20/solid";
import styled, { keyframes } from "styled-components";

import RuneImage from "../components/RuneImage";

const SearchIcon = styled(MagnifyingGlassIcon)`
  width: 20px;
  height: 20px;
`;

const rotation = keyframes`
    from{
        transform: rotate(0deg);
    }

    to{
        transform: rotate(360deg);
    }

`;

const Spinner = styled.div`
  height: 30px;
  width: 30px;
  border: 1px solid ${({ theme }) => theme.colors.primarySky};
  border-radius: 50%;
  border-top: none;
  border-right: none;
  margin: 16px auto;
  animation: ${rotation} 1s linear infinite;
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
    <>
      <SearchIcon />
      <Spinner />
      {/* <ChampionImage championId="Ahri" />
      <SummonerImage summonerId="21" /> */}
      <RuneImage styleRuneId={8300} runeId={8351} />
    </>
  );
}
