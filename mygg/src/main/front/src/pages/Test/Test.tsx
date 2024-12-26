import styled from "styled-components";

import { useEffect, useState } from "react";




const GradientBackground = styled.div`
  margin: 0;
  min-height: 100vh;

  color: #333;
  font-family: sans-serif;
`;


interface IRecentSearch {
  search: string;
  userName: string;
}

//테스트 페이지
export default function Test() {
  const [recentSearch, setRecentSearch] = useState<IRecentSearch[]>([]);

  useEffect(() => {
    const recentSearch = localStorage.getItem("recentSearch");
    if (recentSearch) {
      setRecentSearch(JSON.parse(recentSearch));
    }
  }, []);

  const handleRecentSearch = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const search = e.currentTarget.search.value;
    const userName = e.currentTarget.userName.value;
    setRecentSearch((prev) => {
      const updatedSearches = [...prev, { search, userName }];
      localStorage.setItem("recentSearch", JSON.stringify(updatedSearches));
      return updatedSearches;
    });
  };

  return (
    <GradientBackground>
      <form onSubmit={handleRecentSearch}>
        <input type="text" name="search" />
        <input type="text" name="userName" />
        <button type="submit">검색</button>
      </form>
      <div>
        {recentSearch.map((searchItem, index) => (
          <div key={`${searchItem.search}-${index}`}>
            {searchItem.search} - {searchItem.userName}
          </div>
        ))}
      </div>
    </GradientBackground>
  );
}
