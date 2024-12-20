import { useParams } from "react-router-dom";

// '/champion/:id' 라우트 이동시 랜더링 되는 컴포넌트
export default function ChampionDetailPage() {
  const { id } = useParams();
  console.log(id);

  return <div>champion detail {id}</div>;
}
