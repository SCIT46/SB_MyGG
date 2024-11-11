import { useEffect, useState } from "react";

// '/' 라우트 이동시 랜더링 되는 컴포넌트

export default function Home() {
  const [message, setMessage] = useState("");

  //api fetch 코드
  useEffect(() => {
    fetch("/api/hello")
      .then((response) => response.text())
      .then((message) => {
        setMessage(message);
      });
  }, []);

  return (
    <div>
      <div>Home</div>
      <div>This is from API! : {message}</div>
    </div>
  );
}
