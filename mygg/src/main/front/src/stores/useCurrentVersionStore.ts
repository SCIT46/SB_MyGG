import { create } from "zustand";

interface ICurrentVersionStore {
  version: String;
  setVersion: (newData: String) => void;
}

const useCurrentVersionStore = create<ICurrentVersionStore>((set) => ({
  version: "",
  setVersion: (newData) => set({ version: newData }),
}));

export default useCurrentVersionStore;
