import { useParams } from "react-router-dom";
import { getChampionDetail } from "../../services/Api";
import { useEffect, useState } from "react";
import { IChampionDetail } from "../../interfaces/championType";


// '/champion/:id' 라우트 이동시 랜더링 되는 컴포넌트
export default function ChampionDetailPage() {
  const { id } = useParams();
  console.log(id);

  const [championDetail, setChampionDetail] = useState<IChampionDetail | null>(null);


  useEffect(() => {
    const fetchChampionDetail = async () => {
      if (id) {
        const data = await getChampionDetail(id);
        setChampionDetail(data[id]);
      }
    };
    fetchChampionDetail();
  }, [id]);


  console.log(championDetail);
  return (
    <div>
      {championDetail ? (
        <div>
          <h1>{championDetail.name}</h1>
          <p>{championDetail.blurb}</p>
        </div>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
}
