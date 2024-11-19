import { useState } from "react";
import styled from "styled-components";
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

const SearchDetailContainer = styled.div`
  background-color: ${({ theme }) => theme.colors.backgroundWhite};
  width: 45%;
  padding: 5px;
  height: 80px;
  border-radius: 0px 0px 10px 10px;
  border: 1.5px solid ${({ theme }) => theme.colors.primaryGold};
  border-top: none;
`;

const SearchInput = styled.input`
  width: 45%;
  height: 40px;
  border-radius: 10px;
  border: 1.5px solid ${({ theme }) => theme.colors.primaryGold};
  padding-left: 8px;
  background-color: ${({ theme }) => theme.colors.backgroundWhite};

  &:focus {
    outline: none;
    border-bottom: none;
    border-radius: 10px 10px 0px 0px;
  }
`;

const SearchForm = styled.form`
  position: relative;
  width: 45%;
  margin-top: 10px;
  width: 100vw;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const SearchDetailHearder = styled.div`
  width: 100%;
  height: 35px;
  border-radius: 10px;
  background-color: ${({ theme }) => theme.colors.backgroundDarkerGray};
`;

// '/' 라우트 이동시 랜더링 되는 컴포넌트
export default function Home() {
  const [isSearchInputFocused, setIsSearchInputFocused] =
    useState<boolean>(false);

  return (
    <HoemContainer>
      <TitleBox>
        <Title>MY.GG</Title>
      </TitleBox>
      <SearchBox>
        <SearchForm>
          <SearchInput
            onFocus={() => setIsSearchInputFocused(true)}
            onBlur={() => setIsSearchInputFocused(false)}
            placeholder="소환사 이름을 입력해주세요! 소환사#태그"
          ></SearchInput>
          {isSearchInputFocused ? (
            <SearchDetailContainer></SearchDetailContainer>
          ) : null}
        </SearchForm>
      </SearchBox>
    </HoemContainer>
  );
}
