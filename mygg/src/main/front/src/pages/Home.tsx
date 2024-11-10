import { useEffect, useState } from "react";

export default function Home() {
  const [message, setMessage] = useState("");
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
