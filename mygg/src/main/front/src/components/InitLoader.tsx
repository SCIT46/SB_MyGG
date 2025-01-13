import { useEffect } from "react";
import useChampionStore from "../stores/useChampionStore";
import {
  getChamps,
  getItems,
  getRunes,
  getSummoner,
  getVersions,
} from "../services/Api";
import useItemStore from "../stores/useItemStore";
import useCurrentVersionStore from "../stores/useCurrentVersionStore";
import useSummonerStore from "../stores/useSummonerStore";
import useRunesStore from "../stores/useRunesStore";

// 초기 데이터 로드 컴포넌트
// 사용자가 페이지 접속 시 초기 데이터를 로드하는 컴포넌트
// 초기 데이터는 챔피언, 아이템, 스펠, 룬 데이터와 버전 데이터

export default function InitLoader() {
  const setChampions = useChampionStore((state) => state.setChampions);
  const setItems = useItemStore((state) => state.setItems);
  const setVersion = useCurrentVersionStore((state) => state.setVersion);
  const setSummoner = useSummonerStore((state) => state.setSummoner);
  const setRunes = useRunesStore((state) => state.setRunes);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const versionsData = await getVersions();
        const champData: any = await getChamps(versionsData[0]);
        const itemData: any = await getItems(versionsData[0]);
        const summonerData: any = await getSummoner(versionsData[0]);
        const runesData: any = await getRunes(versionsData[0]);
        setChampions(champData.data);
        setItems(itemData.data);
        setSummoner(summonerData.data);
        setVersion(versionsData[0]);
        setRunes(runesData);
      } catch (error) {
        console.error("init loader fetch error!", error);
      }
    };

    fetchData();
  }, [setChampions, setItems, setSummoner, setVersion, setRunes]);

  return <></>;
}
