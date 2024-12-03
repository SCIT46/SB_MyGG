import { useState, useEffect } from "react";
import { getMatch } from "../../services/Api";
import { IMatchDetail } from "./type";

export function useFetchMatchDetails(matchList?: string[]) {
  const [matchDetails, setMatchDetails] = useState<IMatchDetail[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        if (matchList && matchList.length > 0) {
          const details = await Promise.all(
            matchList.map((id) => getMatch(id))
          );
          setMatchDetails(details);
        } else {
          setMatchDetails([]);
        }
      } catch (error) {
        console.error("match fetch error!", error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, [matchList?.length]);

  return { matchDetails, isLoading };
}
