import { createGlobalStyle } from "styled-components";
import reset from "styled-reset";

//전역 스타일링
export const GlobalStyle = createGlobalStyle`
//디폴트 css 리셋
${reset}

a {
    text-decoration: none;

}
`;
