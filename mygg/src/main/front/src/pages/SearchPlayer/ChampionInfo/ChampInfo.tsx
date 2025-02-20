import styled from "styled-components";
import ChampDetail from "./ChamDetail";
import { useEffect, useState } from "react";
import IRecentAnaylsis from "../../../interfaces/IRecentAnaylsis";
import { getRecentMatch } from "../../../services/riotDateService";

const ChampInfoContainer = styled.div`
  border: 1px ${({ theme }) => theme.colors.border.main} solid;
  width: 100%;
  background-color: ${({ theme }) => theme.colors.background.white};
  border-radius: 10px;
  margin-top: 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-bottom: 10px;
`;

export default function ChampInfo({
  gameName,
  tagLine,
}: {
  gameName: string;
  tagLine: string;
}) {
  const [recentAnaylsis, setRecentAnaylsis] = useState<IRecentAnaylsis[]>([]);
  useEffect(() => {
    getRecentMatch(gameName, tagLine).then((data) => {
      setRecentAnaylsis(data);
      console.log(data);
    });
  }, []);
  return (
    <ChampInfoContainer>
      {recentAnaylsis.map((data, index) => (
        <ChampDetail key={index} data={data} />
      ))}
    </ChampInfoContainer>
  );
}
