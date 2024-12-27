import { create } from "zustand";
import { IItem } from "../interfaces/itemType";

interface IItemStore {
  items: IItem[] | null;
  setItems: (newData: IItem[]) => void;
}

const useItemStore = create<IItemStore>((set) => ({
  items: null,
  setItems: (newData) => set({ items: newData }),
}));

export default useItemStore;
