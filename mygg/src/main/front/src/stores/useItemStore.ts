import { create } from "zustand";
import { Ichamps } from "../pages/Champion/typs";

interface IItemStore {
  item: Ichamps[] | null;
  setitem: (newData: Ichamps[]) => void;
}

const useItemStore = create<IItemStore>((set) => ({
  item: null,
  setitem: (newData) => set({ item: newData }),
}));

export default useItemStore;
