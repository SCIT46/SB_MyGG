import { useState, useEffect } from "react";
import { getMatch } from "../../services/riotDateService";
import { IMatchDetail } from "./type";

export function useFetchMatchDetails(userName: string, tagLine: string) {
  const [matchDetails, setMatchDetails] = useState<IMatchDetail[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await getMatch(userName, tagLine);
        setMatchDetails(data);
      } catch (error) {
        console.error("match fetch error!", error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, [userName, tagLine]);

  return { matchDetails, isLoading };
}
