import axios from "axios";

//api Fetch 함수
export const getHello = async () => {
  const accountResponse = await axios({
    method: "get",
    url: `/api/hello`,
  });
  return accountResponse.data;
};
