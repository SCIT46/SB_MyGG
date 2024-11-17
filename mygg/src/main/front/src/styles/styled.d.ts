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
      //백그라운드 대비 기존 텍스트 컬러
      textWhite: string;
      textBlack: string;
      //
      lightText: string;
    };
  }
}
