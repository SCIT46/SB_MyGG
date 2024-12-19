import { DefaultTheme } from "styled-components";

//컴포넌트들에서 이용하는 디폴드 theme 설정
const theme: DefaultTheme = {
  colors: {
    // 백그라운드 컬러
    background: {
      primary: "#F5F5F5",
      secondary: "#E2E2E2",
      white: "#ffffff",
      dark: "#333333",
      overlay: "rgba(0, 0, 0, 0.5)",
    },

    // 브랜드/테마 컬러
    brand: {
      gold: {
        light: "#d4bc8d48",
        main: "#C4AB75",
        dark: "#B49A64",
      },
      sky: {
        light: "#8ccce381",
        main: "#72BFDB",
        dark: "#5BACC8",
      },
    },

    // 텍스트 컬러
    text: {
      primary: "#111111",
      secondary: "#333333",
      light: "#969696",
      white: "#EDEDED",
      disabled: "#CCCCCC",
    },

    // 상태 컬러
    status: {
      success: "#4CAF50",
      warning: "#FFC107",
      error: "#FF5252",
      info: "#2196F3",
    },

    // 보더 컬러
    border: {
      light: "#E0E0E0",
      main: "#CCCCCC",
      dark: "#999999",
    },
  },

  // 타이포그래피
  typography: {
    fontFamily: "'Noto Sans KR', sans-serif",
    fontSize: {
      xs: "12px",
      sm: "14px",
      md: "16px",
      lg: "18px",
      xl: "24px",
      xxl: "32px",
    },
    fontWeight: {
      light: 300,
      regular: 400,
      medium: 500,
      bold: 700,
    },
  },

  // 스페이싱
  spacing: {
    xs: "4px",
    sm: "8px",
    md: "16px",
    lg: "24px",
    xl: "32px",
    xxl: "48px",
  },

  // 반응형 브레이크포인트
  breakpoints: {
    mobile: "320px",
    tablet: "768px",
    desktop: "1024px",
    wide: "1440px",
  },

  // 그림자 효과
  shadows: {
    sm: "0 1px 3px rgba(0,0,0,0.12)",
    md: "0 4px 6px rgba(0,0,0,0.1)",
    lg: "0 10px 15px rgba(0,0,0,0.1)",
  },

  // 테두리 반경
  borderRadius: {
    sm: "4px",
    md: "8px",
    lg: "16px",
    round: "50%",
  },

  // z-index 레벨
  zIndex: {
    modal: 1000,
    overlay: 900,
    dropdown: 800,
    header: 700,
    tooltip: 600,
  },
};

export { theme };
