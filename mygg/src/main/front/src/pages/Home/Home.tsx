import { useState } from "react";
import styled from "styled-components";
import SearchForm from "./SearchForm";
const HoemContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const TitleBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 220px;
  width: 100vw;
`;

const Title = styled.div`
  color: ${({ theme }) => theme.colors.primaryGold};
  font-size: 72px;
  font-weight: 700;
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
`;

const SearchBox = styled.div`
  margin-top: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100vw;
`;

// 메인 홈페이지 컴포넌트
export default function Home() {
  return (
    <HoemContainer>
      <TitleBox>
        <Title>MY.GG</Title>
      </TitleBox>
      {/* 서치 관련 컴포넌트 */}
      <SearchBox>
        <SearchForm />
      </SearchBox>
    </HoemContainer>
  );
}
