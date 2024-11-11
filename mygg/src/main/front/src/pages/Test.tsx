import { useEffect, useState } from "react";
import { getHello } from "../services/Api";
import { MagnifyingGlassIcon } from "@heroicons/react/20/solid";
import styled from "styled-components";

const SearchIcon = styled(MagnifyingGlassIcon)`
  width: 20px;
  height: 20px;
`;

//테스트 페이지
export default function Test() {
  const [message, setMessage] = useState("");

  //api fetching
  useEffect(() => {
    const ApiData = async () => {
      try {
        const data = await getHello();
        setMessage(data as any);
      } catch (error: any) {
        console.log(error);
      }
    };
    ApiData();
  }, []);

  return (
    <>
      <div>test : {message}</div>
      <SearchIcon />
    </>
  );
}
