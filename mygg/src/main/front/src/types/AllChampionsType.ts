export interface IAllChampions {
  [key: string]: ChampionDetails;
}

interface ChampionDetails {
  blurb: string;
  id: string;
  key: number;
  name: string;
  title: string;
}
