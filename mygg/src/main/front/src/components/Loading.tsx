import styled, { keyframes } from "styled-components";

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
  height: 30px;
  width: 30px;
  border: 1px solid ${({ theme }) => theme.colors.primarySky};
  border-radius: 50%;
  border-top: none;
  border-right: none;
  margin: 16px auto;
  animation: ${rotation} 1s linear infinite;
`;

//loading 스피너 컴포넌트
export default function LoadingSpinner() {
  return <Spinner />;
}
