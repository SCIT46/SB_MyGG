import { create } from "zustand";
import { IChampion } from "../types/type";

interface IChampionStore {
  champions: IChampion[] | null;
  setChampions: (newData: IChampion[]) => void;
}

const useChampionStore = create<IChampionStore>((set) => ({
  champions: null,
  setChampions: (newData) => set({ champions: newData }),
}));

export default useChampionStore;
