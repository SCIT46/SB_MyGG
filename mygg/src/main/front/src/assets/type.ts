export interface IChampion {
  [key: string]: ChampionDetails;
}

interface ChampionDetails {
  blurb: string;
  id: string;
  key: number;
  name: string;
  title: string;
}

export interface ISummoner {
  [key: string]: SummonerDetails;
}

interface SummonerDetails {
  key: string;
  name: string;
  description: string;
}

export interface IRune {
  id: number;
  key: string;
  icon: string;
}
