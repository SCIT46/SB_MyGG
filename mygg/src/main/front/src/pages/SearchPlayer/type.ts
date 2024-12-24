export interface IUser {
  leagueId: string;
  puuid: string;
  summonerId: string;
  profileIconId: number;
  gameName: string;
  tagLine: string;
  summonerLevel: number;
  tier: string;
  rank: string;
  leaguePoints: number;
  wins: number;
  losses: number;
  revisionDate: string;
  matchList: string[];
}

export interface IMatchDetail {
  metadata: {
    dataVersion: string;
    matchId: string;
    participants: string[];
  };
  info: {
    endOfGameResult: string;
    gameDuration: number;
    gameStartTimestamp: number;
    gameEndTimestamp: number;
    gameMode: string;
    gameVersion: string;
    gameType: string;
    queueId: number;
    mapId: number;
    platformId: string;
    participants: IParticipants[];
  };
}

interface IParticipants {
  riotIdGameName: string;
  riotIdTagline: string;
  championName: string;
  lane: string;
  role: string;
  summonerLevel: number;
  puuid: string;
  championId: number;
  champLevel: number;
  item0: number;
  item1: number;
  item2: number;
  item3: number;
  item4: number;
  item5: number;
  item6: number;
  teamId: number;
  win: boolean;
  kills: number;
  deaths: number;
  assists: number;
  visionScore: number;
  visionWardsBoughtInGame: number;
  wardsPlaced: number;
  wardsKilled: number;
  detectorWardsPlaced: number;
  totalMinionsKilled: number;
  neutralMinionsKilled: number;
  totalDamageDealtToChampions: number;
  totalDamageTaken: number;
  summoner1Id: number;
  summoner2Id: number;
  dangerPings: number;
  getBackPings: number;
  holdPings: number;
  needVisionPings: number;
  perks: Perks;
  challenges: Challenges;
}

interface Perks {
  statPerks: StatPerks;
  styles: Style[];
}

interface StatPerks {
  defense: number;
  flex: number;
  offense: number;
}

interface Style {
  description: string;
  selections: Selection[];
  style: number;
}

interface Selection {
  perk: number;
  var1: number;
  var2: number;
  var3: number;
}

interface Challenges {
  controlWardTimeCoverageInRiverOrEnemyHalf: number;
  kda: number;
  controlWardsPlaced: number;
  damagePerMinute: number;
  gameLength: number;
  skillshotsDodged: number;
  skillshotsHit: number;
  teamBaronKills: number;
  teamElderDragonKills: number;
  teamRiftHeraldKills: number;
  visionScorePerMinute: number;
  goldPerMinute: number;
}
