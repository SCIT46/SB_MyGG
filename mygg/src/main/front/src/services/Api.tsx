import axios from "axios";

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
    console.log("item fetch error! ", error);
  }
};

export const getChamps = async () => {
  try {
    const chamResponse = await axios({
      method: "get",
      url: `https://ddragon.leagueoflegends.com/cdn/14.23.1/data/ko_KR/champion.json`,
    });
    return chamResponse.data;
  } catch (error: any) {
    console.log("champion fetch error! ", error);
  }
};

export const getUser = async () => {
  try {
    const userResponse = await axios({
      method: "get",
      url: `/api/user/이시형/페미니스트`,
    });
    return userResponse.data;
  } catch (error: any) {
    console.log("champion fetch error! ", error);
  }
};
