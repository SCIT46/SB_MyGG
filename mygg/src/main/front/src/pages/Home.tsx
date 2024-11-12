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
  margin-top: 170px;
  width: 100vw;
`;

const Title = styled.div`
  color: ${({ theme }) => theme.colors.text};
  font-size: 64px;
  font-weight: 600;
`;

const SearchBox = styled.div`
  margin-top: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100vw;
`;

const SearchInput = styled.input`
  width: 45%;
  height: 40px;
  border-radius: 10px;
  border: none;
  padding-left: 8px;
  background-color: ${({ theme }) => theme.colors.text};
  box-shadow: 3px 3px 3px 3px rgba(0, 0, 0, 0.2);

  &:focus {
    outline: none;
    border: none;
    border-radius: 10px 10px 0px 0px;
  }
`;

const SearchDetailContainer = styled.div`
  background-color: ${({ theme }) => theme.colors.text};
  width: 45%;
  padding: 5px;
  height: 80px;
  border-radius: 0px 0px 10px 10px;
`;

const SearchForm = styled.form`
  margin-top: 10px;
  width: 100vw;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
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
          {isSearchInputFocused ? <SearchDetailContainer /> : null}
        </SearchForm>
      </SearchBox>
    </HoemContainer>
  );
}
