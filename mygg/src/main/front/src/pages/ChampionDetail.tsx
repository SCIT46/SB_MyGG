import { useParams } from "react-router-dom";

export default function ChampionDetail() {
  const { id } = useParams();
  console.log(id);

  return <div>champion detail {id}</div>;
}
