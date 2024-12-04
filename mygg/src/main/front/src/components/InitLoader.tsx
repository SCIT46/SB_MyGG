import { useEffect } from "react";
import useChampionStore from "../stores/useChampionStore";
import { getChamps, getItems, getSummoner, getVersions } from "../services/Api";
import useItemStore from "../stores/useItemStore";
import useCurrentVersionStore from "../stores/useCurrentVersionStore";
import useSummonerStore from "../stores/useSummonerStore";

//todo 아이템, 스펠, 룬 패치 추가
export default function InitLoader() {
  const setChampions = useChampionStore((state) => state.setChampions);
  const setItems = useItemStore((state) => state.setItems);
  const setVersion = useCurrentVersionStore((state) => state.setVersion);
  const setSummoner = useSummonerStore((state) => state.setSummoner);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const versionsData = await getVersions();
        const champData: any = await getChamps(versionsData[0]);
        const itemData: any = await getItems(versionsData[0]);
        const summonerData: any = await getSummoner(versionsData[0]);
        setChampions(champData.data);
        setItems(itemData.data);
        setSummoner(summonerData.data);
        setVersion(versionsData[0]);
        console.log(summonerData.data);
      } catch (error) {
        console.error("init loader fetch error!", error);
      }
    };

    fetchData();
  }, [setChampions, setItems, setSummoner, setVersion]);

  return <></>;
}
