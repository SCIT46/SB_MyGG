import { useState, useEffect } from "react";
import { getMatch } from "../../services/riotDateService";
import { IMatchDetail } from "./type";
import useMatchRefreshStore from "../../stores/useMatchRefreshStore";

export function useFetchMatchDetails(userName: string, tagLine: string) {
  const [matchDetails, setMatchDetails] = useState<IMatchDetail[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const refreshKey = useMatchRefreshStore((state) => state.refreshKey);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await getMatch(userName, tagLine, 0);
        setMatchDetails(data);
      } catch (error) {
        console.error("match fetch error!", error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, [userName, tagLine, refreshKey]);

  return { matchDetails, isLoading };
}
