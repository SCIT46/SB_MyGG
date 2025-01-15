import styled, { keyframes } from "styled-components";

// loading 스피너 컴포넌트
// 해당 컴포넌트를 사용 중인 컴포넌트
// - SearchDetail.tsx
// - SearchPlayer.tsx
// - MatchDetail.tsx

// styled 의 애니메이션 효과를 이용해 구현
const rotation = keyframes`
    from{
        transform: rotate(0deg);
    }

    to{
        transform: rotate(360deg);
    }

`;

const Spinner = styled.div`
  height: 1.25em;
  width: 1.25em;
  border: 0.16em solid ${({ theme }) => theme.colors.brand.sky.main};
  border-radius: 100%;
  border-top: 0.125em solid transparent;
  border-right: 0.125em solid transparent;
  margin-left: 1.14em;
  margin-right: 0.68em;
  margin-bottom: 0.125em;

  animation: ${rotation} 1s linear infinite;
  box-sizing: border-box;
  aspect-ratio: 1 / 1;
`;

//loading 스피너 컴포넌트
export default function LoadingSpinner() {
  return <Spinner />;
}
