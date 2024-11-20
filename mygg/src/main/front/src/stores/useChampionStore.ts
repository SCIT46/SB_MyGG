import { create } from "zustand";
import { Ichamps } from "../pages/Champion/typs";

interface IChampionStore {
  champions: Ichamps[] | null;
  setChampions: (newData: Ichamps[]) => void;
}

const useChampionStore = create<IChampionStore>((set) => ({
  champions: null,
  setChampions: (newData) => set({ champions: newData }),
}));

export default useChampionStore;
