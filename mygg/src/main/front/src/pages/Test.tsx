import { MagnifyingGlassIcon } from "@heroicons/react/20/solid";
import styled, { keyframes } from "styled-components";

import RuneImage from "../components/RuneImage";
import StyledRuneImage from "../components/StyledRuneImage";
import SummonerImage from "../components/SummonerImage";
import { useState, useEffect } from "react";
import { getSearchedResult } from "../services/Api";
import { useDebounce } from "../hooks/useDebounce";
import { ISuggestion } from "./Home/type";

const SearchIcon = styled(MagnifyingGlassIcon)`
  width: 20px;
  height: 20px;
`;

const GradientBackground = styled.div`
  margin: 0;
  min-height: 100vh;

  color: #333;
  font-family: sans-serif;
`;

const Spinner = styled.div`
  height: 30px;
  width: 30px;
  border: 1px solid ${({ theme }) => theme.colors.brand.sky.main};
  border-radius: 50%;
  border-top: none;
  border-right: none;
  margin: 16px auto;
`;

const TestInput = styled.input`
  width: 360px;
  height: 30px;
  margin: 16px auto;
  border: 1px solid ${({ theme }) => theme.colors.brand.sky.main};
  border-radius: 5px;
  padding: 0 10px;
`;

//테스트 페이지
export default function Test() {
  const [query, setQuery] = useState("");
  const debouncedQuery = useDebounce(query, 300);
  const [suggestions, setSuggestions] = useState<any>();
  useEffect(() => {
    if (debouncedQuery) {
      const fetchSuggestions = async () => {
        try {
          console.log("debouncedQuery:", debouncedQuery);
          const suggestion: ISuggestion = await getSearchedResult(
            debouncedQuery
          );
          setSuggestions(suggestion);
          console.log("Suggestions:", suggestions);
        } catch (error) {
          console.error("Error fetching suggestions:", error);
        }
      };
      fetchSuggestions();
    } else {
      setSuggestions([]);
    }
  }, [debouncedQuery]);

  const onChangeQuery = (e: React.ChangeEvent<HTMLInputElement>) => {
    setQuery(e.target.value);
  };

  return (
    <GradientBackground>
      <SearchIcon />
      <Spinner />

      <RuneImage styleRuneId={8300} runeId={8351} />
      <StyledRuneImage runeId={8300} />
      <SummonerImage summonerId="21" />
      <TestInput type="text" value={query} onChange={onChangeQuery} />
      <div>{suggestions?.item?.map((item: any) => item.name)}</div>
      <div>{suggestions?.champion?.map((item: any) => item.name)}</div>
      <div>{suggestions?.user?.map((item: any) => item.gameName)}</div>
    </GradientBackground>
  );
}
