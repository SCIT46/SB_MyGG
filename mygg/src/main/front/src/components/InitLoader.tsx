import { useEffect } from "react";
import useChampionStore from "../stores/useChampionStore";
import { Ichamps } from "../pages/Champion/typs";
import { getChamps } from "../services/Api";

export default function InitLoader() {
  const setChampions = useChampionStore((state) => state.setChampions);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data: any = await getChamps();
        const arr: Ichamps[] = Object.values(data.data);
        setChampions(arr);
        console.log("i'm here!");
      } catch (error) {
        console.error("champions fetch error!", error);
      }
    };

    fetchData();
  }, []);

  return <></>;
}
