import styled from "styled-components";

const RankContianer = styled.div`
  border: 1px ${({ theme }) => theme.colors.primaryGold} solid;

  width: 100%;
  height: 200px;
  background-color: ${({ theme }) => theme.colors.backgroundWhite};
  border-radius: 10px;
`;

export default function Rank() {
  return <RankContianer>123</RankContianer>;
}
