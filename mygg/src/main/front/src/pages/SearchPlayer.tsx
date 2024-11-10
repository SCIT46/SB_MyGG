import { useParams } from "react-router-dom";

export default function SearchPlayer() {
  const { id } = useParams();
  return <div>Search player {id}</div>;
}
