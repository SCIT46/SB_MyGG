import { useParams } from "react-router-dom";

// '/item/:id' 라우트 이동시 랜더링 되는 컴포넌트

export default function ItemDetail() {
  const { id } = useParams();
  return <div>Item detail {id}</div>;
}
