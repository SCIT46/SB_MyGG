import { apiClient, ddragonClient } from "./apiClient";

// (1) 버전 목록 가져오기
export const getVersions = async (): Promise<string[]> => {
  const { data } = await ddragonClient.get<string[]>("/api/versions.json");
  return data;
};

// (2) 아이템 목록 가져오기
export const getItems = async (version: string): Promise<any> => {
  const { data } = await ddragonClient.get(
    `/cdn/${version}/data/ko_KR/item.json`
  );
  return data;
};

// (3) 개별 아이템 상세 가져오기
export const getItemDetail = async (itemId: string): Promise<any> => {
  const { data } = await apiClient.get(`/item/${itemId}`);
  return data;
};

// (4) 챔피언 목록 가져오기
export const getChamps = async (version: string): Promise<any> => {
  const { data } = await ddragonClient.get(
    `/cdn/${version}/data/ko_KR/champion.json`
  );
  return data;
};

// (5) 개별 챔피언 상세 가져오기
export const getChampionDetail = async (championId: string): Promise<any> => {
  const { data } = await apiClient.get(`/champion/${championId}`);
  return data;
};

// (6) 소환사 주문 정보 가져오기
export const getSummoner = async (version: string): Promise<any> => {
  const { data } = await ddragonClient.get(
    `/cdn/${version}/data/ko_KR/summoner.json`
  );
  return data;
};

// (7) 룬 정보 가져오기
export const getRunes = async (version: string): Promise<any> => {
  const { data } = await ddragonClient.get(
    `/cdn/${version}/data/ko_KR/runesReforged.json`
  );
  return data;
};

// (8) 사용자 정보 가져오기
export const getUser = async (
  gameName: string,
  tagLine: string
): Promise<any> => {
  const { data } = await apiClient.get(`/user/${gameName}/${tagLine}`, {
    headers: {
      "Cache-Control": "max-age=300",
    },
  });
  return data;
};

// (9) 매치 정보 가져오기
export const getMatch = async (
  userName: string,
  tagLine: string
): Promise<any> => {
  const { data } = await apiClient.get(`/match/test/${userName}/${tagLine}`, {
    headers: {
      "Cache-Control": "max-age=300",
    },
  });
  return data;
};

// (10) 검색 결과 가져오기
export const getSearchedResult = async (query: string): Promise<any> => {
  const { data } = await apiClient.get(`/search/${query}`);
  return data;
};
