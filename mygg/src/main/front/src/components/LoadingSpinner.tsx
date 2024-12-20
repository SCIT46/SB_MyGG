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
  height: 20px;
  width: 20px;
  border: 2px solid ${({ theme }) => theme.colors.brand.sky.dark};
  border-radius: 50%;
  border-top: 2px solid transparent;
  border-right: 2px solid transparent;
  margin-left: 20px;
  margin-right: 10.9px;
  margin-bottom: 2px;

  animation: ${rotation} 1s linear infinite;
  box-sizing: border-box;
`;

//loading 스피너 컴포넌트
export default function LoadingSpinner() {
  return <Spinner />;
}
