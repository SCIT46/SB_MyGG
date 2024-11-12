import { createGlobalStyle } from "styled-components";
import reset from "styled-reset";

//전역 스타일링
export const GlobalStyle = createGlobalStyle`
//디폴트 css 리셋
${reset}


//디폴드 bgcolor 및 폰트색상 설정
body {
    background-color: ${({ theme }) => theme.colors.background};
    color: ${({ theme }) => theme.colors.text}
}

//기본 하이퍼링크 Css 속성 제거
a {
    text-decoration: none;
}
`;
