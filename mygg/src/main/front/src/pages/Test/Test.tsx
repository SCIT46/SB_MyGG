import { useEffect, useState } from "react";
import styled from "styled-components";
import { getRecentMatch } from "../../services/riotDateService";
import ChampionImage from "../../components/ImageUI/ChampionImage";

interface IRecentAnaylsis {
  championId: number;
  assist: number;
  championName: string;
  death: number;
  gameCnt: number;
  kda: number;
  kill: number;
  loseCnt: number;
  minionKilled: number;
  neutralKilled: number;
  winCnt: number;
}

//테스트 페이지
export default function Test() {
  const [recentAnaylsis, setRecentAnaylsis] = useState<IRecentAnaylsis[]>([]);
  useEffect(() => {
    getRecentMatch("김춘식", "kr1").then((data) => {
      console.log(data[0]);
      setRecentAnaylsis(data);
    });
  }, []);
  return (
    <div>
      {recentAnaylsis.map((item) => (
        <>
          <ChampionImage
            championId={item.championName}
            width={100}
            height={100}
          />
          <div>{item.championName}</div>
          <div>{item.kda}</div>
          <div>{item.kill}</div>
          <div>{item.death}</div>
          <div>{item.assist}</div>
          <div>{item.gameCnt}</div>
          <div>{item.winCnt}</div>
          <div>{item.loseCnt}</div>
          <div>{item.minionKilled}</div>
          <div>{item.neutralKilled}</div>
        </>
      ))}
    </div>
  );
}
