import { create } from "zustand";

interface IRecentSearch {
  gameName: string;
  tagLine: string;
  profileIconId: number;
}

interface IRecentSearchStore {
  recentSearch: IRecentSearch[];
  setRecentSearch: (newData: IRecentSearch[]) => void;
}

const useRecentSearchStore = create<IRecentSearchStore>((set) => ({
  recentSearch: JSON.parse(localStorage.getItem("recentSearch") || "[]"),
  setRecentSearch: (newData) => {
    // Remove duplicates
    const uniqueData = newData.filter((item, index, self) =>
      index === self.findIndex((t) => (
        t.gameName === item.gameName && t.tagLine === item.tagLine && t.profileIconId === item.profileIconId
      ))
    );

    // Limit to 10 items
    const limitedData = uniqueData.slice(0, 10);

    set({ recentSearch: limitedData });
    localStorage.setItem("recentSearch", JSON.stringify(limitedData));
  },
}));

export default useRecentSearchStore;
