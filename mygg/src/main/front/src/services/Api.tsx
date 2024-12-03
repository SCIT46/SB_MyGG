import axios from "axios";

// Create an axios instance for common configurations
const apiClient = axios.create({
  baseURL: "/api",
});

const ddragonClient = axios.create({
  baseURL: "https://ddragon.leagueoflegends.com",
});

//api Fetch 함수
export const getHello = async (): Promise<any> => {
  const accountResponse = await apiClient.get("/hello");
  return accountResponse.data;
};

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
    throw error; // Re-throw the error after logging
  }
};

//todo 파라미터추가
export const getChamps = async (): Promise<any> => {
  try {
    const chamResponse = await ddragonClient.get(
      "/cdn/14.23.1/data/ko_KR/champion.json"
    );
    return chamResponse.data;
  } catch (error: any) {
    console.error("Champion fetch error: ", error);
    throw error;
  }
};

export const getUser = async (
  gameName: string,
  tagLine: string
): Promise<any> => {
  try {
    const userResponse = await apiClient.get(`/user/${gameName}/${tagLine}`);
    return userResponse.data;
  } catch (error: any) {
    console.error("User fetch error: ", error);
    throw error;
  }
};

export const getMatch = async (matchId: string): Promise<any> => {
  try {
    const matchResponse = await apiClient.get(`/match/public/${matchId}`);
    return matchResponse.data;
  } catch (error: any) {
    console.error("Match fetch error: ", error);
    throw error;
  }
};
