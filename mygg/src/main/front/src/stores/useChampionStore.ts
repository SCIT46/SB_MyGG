import { create } from "zustand";
import { IChampion } from "../interfaces/type";

interface IChampionStore {
  champions: IChampion[] | null;
  setChampions: (newData: IChampion[]) => void;
}

const useChampionStore = create<IChampionStore>((set) => ({
  champions: null,
  setChampions: (newData) => set({ champions: newData }),
}));

export default useChampionStore;
