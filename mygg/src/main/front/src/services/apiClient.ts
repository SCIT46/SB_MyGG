// apiClient.ts
import axios, { AxiosError } from "axios";

// 공통 설정을 쉽게 적용하기 위해 만들 함수
function createAxiosInstance(baseURL: string) {
  const instance = axios.create({
    baseURL,
    // 필요에 따라 타임아웃, 헤더, 인증 토큰 등을 추가로 설정할 수 있습니다.
    // timeout: 10000,
    // headers: { Authorization: `Bearer ${token}` },
  });

  // 요청 인터셉터(필요시)
  instance.interceptors.request.use(
    (config) => {
      // 요청 전 공통 작업이 필요하다면 처리
      // 예: config.headers.Authorization = 'Bearer ...'
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
  );

  // 응답 인터셉터
  instance.interceptors.response.use(
    (response) => {
      // 응답 데이터 가공, 공통 처리 등이 필요하다면 여기에서 처리
      return response;
    },
    (error: AxiosError) => {
      // 에러 로깅, 에러 메시지 공통 처리 등
      console.error(
        `[${error?.config?.method?.toUpperCase()}] ${error?.config?.url} - ${
          error?.message
        }`
      );
      // 필요에 따라 에러 타입/메시지 변환, 재시도 로직 등을 구현할 수 있습니다.
      return Promise.reject(error);
    }
  );

  return instance;
}

export const apiClient = createAxiosInstance("/api");
export const ddragonClient = createAxiosInstance(
  "https://ddragon.leagueoflegends.com"
);
