import "styled-components";
import { Color } from "./theme";

//theme 타입 설정
declare module "styled-components" {
  export interface DefaultTheme {
    colors: {
      background: {
        primary: string;
        secondary: string;
        white: string;
        dark: string;
        overlay: string;
      };
      brand: {
        gold: {
          light: string;
          main: string;
          dark: string;
        };
        sky: {
          light: string;
          main: string;
          dark: string;
          title: string;
        };
      };
      text: {
        primary: string;
        secondary: string;
        light: string;
        white: string;
        disabled: string;
      };
      status: {
        success: string;
        warning: string;
        error: string;
        info: string;
      };
      border: {
        light: string;
        main: string;
        dark: string;
      };
    };
    typography: {
      fontFamily: string;
      fontSize: {
        xs: string;
        sm: string;
        md: string;
        lg: string;
        xl: string;
        xxl: string;
      };
      fontWeight: {
        light: number;
        regular: number;
        medium: number;
        bold: number;
      };
    };
    spacing: {
      xs: string;
      sm: string;
      md: string;
      lg: string;
      xl: string;
      xxl: string;
    };
    breakpoints: {
      mobile: string;
      tablet: string;
      desktop: string;
      wide: string;
    };
    shadows: {
      sm: string;
      md: string;
      lg: string;
    };
    borderRadius: {
      sm: string;
      md: string;
      lg: string;
      round: string;
    };
    zIndex: {
      modal: number;
      overlay: number;
      dropdown: number;
      header: number;
      tooltip: number;
    };
  }
}
