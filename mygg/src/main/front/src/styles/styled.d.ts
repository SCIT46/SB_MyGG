import "styled-components";
import { Color } from "./theme";

//theme 타입 설정
declare module "styled-components" {
  export interface DefaultTheme {
    colors: {
      primary: string;
      secondary: string;
      background: string;
      text: string;
      lightText: string;
    };
  }
}
