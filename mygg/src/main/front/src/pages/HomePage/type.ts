export interface ISuggestion {
  champion: IChampionSuggestion[];
  item: IItemSuggestion[];
  user: IUserSuggestion[];
}

interface IChampionSuggestion {
  name: string;
  id: string;
}

interface IItemSuggestion {
  name: string;
  id: string;
}

interface IUserSuggestion {
  gameName: string;
  profileIconId: number;
  tagLine: string;
}
