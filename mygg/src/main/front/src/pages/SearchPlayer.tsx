import { useParams } from "react-router-dom";

// '/search/:id 라우트 이동시 랜더링 되는 컴포넌트
export default function SearchPlayer() {
  const { id } = useParams();
  return <div>Search player {id}</div>;
}
