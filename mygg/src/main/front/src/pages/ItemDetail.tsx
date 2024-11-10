import { useParams } from "react-router-dom";

export default function ItemDetail() {
  const { id } = useParams();
  return <div>Item detail {id}</div>;
}
