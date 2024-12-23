import { createGlobalStyle } from "styled-components";
import reset from "styled-reset";

//전역 스타일링
export const GlobalStyle = createGlobalStyle`
//디폴트 css 리셋
${reset}

// Google Fonts에서 Roboto 폰트 가져오기
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');
//디폴드 bgcolor 및 폰트색상 설정
body {
    background-color: ${({ theme }) =>
      theme.colors.background.primary}; /* 원하는 배경색 설정 */
    min-height: 100vh;
    color: ${({ theme }) => theme.colors.text.primary};
    font-family: 'Noto Sans KR', sans-serif; /* 전역 폰트 설정 */
}

//기본 하이퍼링크 Css 속성 제거
a {
    text-decoration: none;
}


`;
