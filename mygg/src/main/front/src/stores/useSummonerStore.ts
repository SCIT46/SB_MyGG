import { create } from "zustand";
import { IItem } from "../pages/Item/type";

interface ISummonerStore {
  summoner: IItem[] | null;
  setSummoner: (newData: IItem[]) => void;
}

const useSummonerStore = create<ISummonerStore>((set) => ({
  summoner: null,
  setSummoner: (newData) => set({ summoner: newData }),
}));

export default useSummonerStore;
