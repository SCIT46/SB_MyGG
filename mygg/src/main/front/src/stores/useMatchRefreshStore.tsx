import { create } from "zustand";

const useMatchRefreshStore = create<any>()((set) => ({
  refreshKey: 0,
  setRefreshKey: () =>
    set((state: any) => ({ refreshKey: state.refreshKey + 1 })),
}));

export default useMatchRefreshStore;
