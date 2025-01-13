import styled from "styled-components";
import parse, { DOMNode, domToReact, Element, HTMLReactParserOptions } from "html-react-parser";
import CustomHtmlRenderer from "../../components/CustomHtmlRenderer";

const GradientBackground = styled.div`
  margin: 0;
  min-height: 100vh;
  color: #333;
  font-family: sans-serif;
`;

const ColoredText = styled.span`
  color: ${({ theme }) => theme.colors.brand.gold.main};
  font-weight: bold;
`;

interface IRecentSearch {
  search: string;
  userName: string;
}


const htmlContent = `
  <mainText>
    <stats>
      공격력 <attention>15</attention><br>
      체력 <attention>200</attention>
    </stats><br><br>
    <passive>분노</passive><br>
    기본 공격을 가하면 2초 동안 <speed>이동 속도</speed>가 상승합니다.
  </mainText>
`;

//테스트 페이지
export default function Test() {
  return (
    <GradientBackground>
      <CustomHtmlRenderer htmlString={htmlContent} />
    </GradientBackground>
  );
}
