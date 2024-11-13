import axios from "axios";
import { cacheAdapterEnhancer } from "axios-extensions";

//api Fetch 함수
export const getHello = async () => {
  const accountResponse = await axios({
    method: "get",
    url: `/api/hello`,
  });
  return accountResponse.data;
};

export const getVersions = async () => {
  const versionResponse = await axios({
    method: "get",
    url: `https://ddragon.leagueoflegends.com/api/versions.json`,
  });
  const data = versionResponse.data as string[];
  return data;
};

export const getItems = async (version: String) => {
  try {
    const itemResponse = await axios({
      method: "get",
      url: `https://ddragon.leagueoflegends.com/cdn/${version}/data/ko_KR/item.json`,
    });
    return itemResponse.data;
  } catch (error: any) {
    console.log(error);
  }
};
