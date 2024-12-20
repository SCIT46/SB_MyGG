import { create } from "zustand";
import { IRune } from "../types/type";

interface IRunesStore {
  runes: IRune[] | null;
  setRunes: (newData: IRune[]) => void;
}

const useRunesStore = create<IRunesStore>((set) => ({
  runes: null,
  setRunes: (newData) => set({ runes: newData }),
}));

export default useRunesStore;
