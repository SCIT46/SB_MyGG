import "styled-components";
import { Color } from "./theme";

//theme 타입 설정
declare module "styled-components" {
  export interface DefaultTheme {
    colors: {
      backgroundGray: string;
      primaryGold: string;
      backgroundWhite: string;
      primarySky: string;
      backgroundDarkerGray: string;
      textWhite: string;
      textBlack: string;
      lightText: string;
    };
  }
}
