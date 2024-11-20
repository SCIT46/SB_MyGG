import useChampionStore from "../../stores/useChampionStore";

// '/champion' 라우트 이동시 랜더링 되는 컴포넌트
export default function Champions() {
  const champions = useChampionStore((state) => state.champions);

  return (
    <div>
      {champions?.map((item, index) => (
        <div key={index}>{item.name}</div>
      ))}
    </div>
  );
}
