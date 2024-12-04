import axios from "axios";

// Create an axios instance for common configurations
const apiClient = axios.create({
  baseURL: "/api",
});

const ddragonClient = axios.create({
  baseURL: "https://ddragon.leagueoflegends.com",
});

export const getVersions = async (): Promise<string[]> => {
  const versionResponse = await ddragonClient.get<string[]>(
    "/api/versions.json"
  );
  return versionResponse.data;
};

export const getItems = async (version: string): Promise<any> => {
  try {
    const itemResponse = await ddragonClient.get(
      `/cdn/${version}/data/ko_KR/item.json`
    );
    return itemResponse.data;
  } catch (error: any) {
    console.error("Item fetch error: ", error);
    throw error;
  }
};

export const getChamps = async (version: string): Promise<any> => {
  try {
    const chamResponse = await ddragonClient.get(
      `/cdn/${version}/data/ko_KR/champion.json`
    );
    return chamResponse.data;
  } catch (error: any) {
    console.error("Champion fetch error: ", error);
    throw error;
  }
};

export const getSummoner = async (version: string): Promise<any> => {
  try {
    const summonerResponse = await ddragonClient.get(
      `/cdn/${version}/data/ko_KR/summoner.json`
    );
    return summonerResponse.data;
  } catch (error: any) {
    console.error("Summoner fetch error: ", error);
    throw error;
  }
};

export const getRunes = async (version: string): Promise<any> => {
  try {
    const runesResponse = await ddragonClient.get(
      `/cdn/${version}/data/ko_KR/runesReforged.json`
    );
    return runesResponse.data;
  } catch (error: any) {
    console.error("Runes fetch error: ", error);
    throw error;
  }
};

export const getUser = async (
  gameName: string,
  tagLine: string
): Promise<any> => {
  try {
    const userResponse = await apiClient.get(`/user/${gameName}/${tagLine}`, {
      headers: {
        "Cache-Control": "max-age=3600", // 1시간 동안 캐시
      },
    });
    return userResponse.data;
  } catch (error: any) {
    console.error("User fetch error: ", error);
    throw error;
  }
};

export const getMatch = async (matchId: string): Promise<any> => {
  try {
    const matchResponse = await apiClient.get(`/match/public/${matchId}`, {
      headers: {
        "Cache-Control": "max-age=3600", // 1시간 동안 캐시
      },
    });
    return matchResponse.data;
  } catch (error: any) {
    console.error("Match fetch error: ", error);
    throw error;
  }
};
