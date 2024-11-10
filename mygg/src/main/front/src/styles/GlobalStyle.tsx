import { createGlobalStyle } from "styled-components";
import reset from "styled-reset";

export const GlobalStyle = createGlobalStyle`
//디폴트 css 리셋
${reset}

a {
    text-decoration: none;

}
`;
