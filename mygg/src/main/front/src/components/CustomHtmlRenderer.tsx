import React from "react";
import parse, {
  HTMLReactParserOptions,
  Element,
  domToReact,
  DOMNode,
} from "html-react-parser";
import {
  MainText,
  Stats,
  Attention,
  Passive,
  Speed,
  CustomLi,
} from "./CustomHtmlRenderer.styles";

interface CustomHtmlRendererProps {
  htmlString: string;
}

// 타입 가드: DOMNode 타입인지 확인
const isDOMNode = (node: any): node is DOMNode => {
  return (
    node &&
    typeof node === "object" &&
    "type" in node &&
    (node.type === "tag" || node.type === "text")
  );
};

const CustomHtmlRenderer: React.FC<CustomHtmlRendererProps> = ({
  htmlString,
}) => {
  const options: HTMLReactParserOptions = {
    replace: (domNode) => {
      if (domNode.type === "tag") {
        const element = domNode as Element;
        const { name, children } = element;

        // 필터링하여 DOMNode[]로 변환
        const filteredChildren = children.filter(isDOMNode);

        switch (name) {
          case "mainText":
            return <MainText>{domToReact(filteredChildren, options)}</MainText>;

          case "li":
            return <CustomLi>{domToReact(filteredChildren, options)}</CustomLi>;

          case "stats":
            return <Stats>{domToReact(filteredChildren, options)}</Stats>;

          case "attention":
            return (
              <Attention>{domToReact(filteredChildren, options)}</Attention>
            );

          case "passive":
            return <Passive>{domToReact(filteredChildren, options)}</Passive>;

          case "speed":
            return <Speed>{domToReact(filteredChildren, options)}</Speed>;

          default:
            return undefined; // 기본적으로 아무것도 반환하지 않음
        }
      }
    },
  };

  return <>{parse(htmlString, options)}</>;
};

export default CustomHtmlRenderer;
