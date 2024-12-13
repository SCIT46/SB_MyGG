interface IQueueIdObj {
  map: string;
  description: string | null;
  notes: string | null;
}

export const queueIdObj: { [key: number]: IQueueIdObj } = {
  0: {
    map: "커스텀 게임",
    description: null,
    notes: null,
  },
  2: {
    map: "소환사의 협곡",
    description: "5대5 블라인드 픽 게임",
    notes: "Deprecated in patch 7.19 in favor of queueId 430",
  },
  4: {
    map: "소환사의 협곡",
    description: "5대5 랭크 솔로 게임",
    notes: "Deprecated in favor of queueId 420",
  },
  6: {
    map: "소환사의 협곡",
    description: "5대5 랭크 프리메이드 게임",
    notes: "Game mode deprecated",
  },
  7: {
    map: "소환사의 협곡",
    description: "AI 상대 대전",
    notes: "Deprecated in favor of queueId 32 and 33",
  },
  8: {
    map: "Twisted Treeline",
    description: "3v3 Normal games",
    notes: "Deprecated in patch 7.19 in favor of queueId 460",
  },
  9: {
    map: "Twisted Treeline",
    description: "3v3 Ranked Flex games",
    notes: "Deprecated in patch 7.19 in favor of queueId 470",
  },
  14: {
    map: "소환사의 협곡",
    description: "5대5 드래프트 픽 게임",
    notes: "Deprecated in favor of queueId 400",
  },
  16: {
    map: "Crystal Scar",
    description: "5v5 Dominion Blind Pick games",
    notes: "Game mode deprecated",
  },
  17: {
    map: "Crystal Scar",
    description: "5v5 Dominion Draft Pick games",
    notes: "Game mode deprecated",
  },
  25: {
    map: "Crystal Scar",
    description: "Dominion Co-op vs AI games",
    notes: "Game mode deprecated",
  },
  31: {
    map: "소환사의 협곡",
    description: "AI 상대 초보 봇 대전",
    notes: "Deprecated in patch 7.19 in favor of queueId 830",
  },
  32: {
    map: "소환사의 협곡",
    description: "AI 상대 초보 봇 대전",
    notes: "Deprecated in patch 7.19 in favor of queueId 840",
  },
  33: {
    map: "소환사의 협곡",
    description: "AI 상대 중급 봇 대전",
    notes: "Deprecated in patch 7.19 in favor of queueId 850",
  },
  41: {
    map: "Twisted Treeline",
    description: "3v3 랭크 팀 대전",
    notes: "Game mode deprecated",
  },
  42: {
    map: "소환사의 협곡",
    description: "5대5 랭크 팀 대전",
    notes: "Game mode deprecated",
  },
  52: {
    map: "Twisted Treeline",
    description: "AI 상대 대전",
    notes: "Deprecated in patch 7.19 in favor of queueId 800",
  },
  61: {
    map: "소환사의 협곡",
    description: "5대5 팀 빌더 대전",
    notes: "Game mode deprecated",
  },
  65: {
    map: "Howling Abyss",
    description: "5v5 ARAM games",
    notes: "Deprecated in patch 7.19 in favor of queueId 450",
  },
  67: {
    map: "Howling Abyss",
    description: "ARAM Co-op vs AI games",
    notes: "Game mode deprecated",
  },
  70: {
    map: "소환사의 협곡",
    description: "One for All games",
    notes: "Deprecated in patch 8.6 in favor of queueId 1020",
  },
  72: {
    map: "Howling Abyss",
    description: "1v1 Snowdown Showdown games",
    notes: null,
  },
  73: {
    map: "Howling Abyss",
    description: "2v2 Snowdown Showdown games",
    notes: null,
  },
  75: {
    map: "소환사의 협곡",
    description: "헥사킬 모드",
    notes: null,
  },
  76: {
    map: "소환사의 협곡",
    description: "우르프 모드",
    notes: null,
  },
  78: {
    map: "Howling Abyss",
    description: "One For All: Mirror Mode games",
    notes: null,
  },
  83: {
    map: "소환사의 협곡",
    description: "AI 상대 우르프 모드",
    notes: null,
  },
  91: {
    map: "소환사의 협곡",
    description: "둠 봇 랭크 1",
    notes: "Deprecated in patch 7.19 in favor of queueId 950",
  },
  92: {
    map: "소환사의 협곡",
    description: "둠 봇 랭크 2",
    notes: "Deprecated in patch 7.19 in favor of queueId 950",
  },
  93: {
    map: "소환사의 협곡",
    description: "둠 봇 랭크 5",
    notes: "Deprecated in patch 7.19 in favor of queueId 950",
  },
  96: {
    map: "Crystal Scar",
    description: "Ascension games",
    notes: "Deprecated in patch 7.19 in favor of queueId 910",
  },
  98: {
    map: "Twisted Treeline",
    description: "6v6 Hexakill games",
    notes: null,
  },
  100: {
    map: "Butcher's Bridge",
    description: "5v5 ARAM games",
    notes: null,
  },
  300: {
    map: "Howling Abyss",
    description: "Legend of the Poro King games",
    notes: "Deprecated in patch 7.19 in favor of queueId 920",
  },
  310: {
    map: "소환사의 협곡",
    description: "네메시스 모드",
    notes: null,
  },
  313: {
    map: "소환사의 협곡",
    description: "블랙 마켓 브라우저 모드",
    notes: null,
  },
  315: {
    map: "소환사의 협곡",
    description: "넥서스 시지 모드",
    notes: "Deprecated in patch 7.19 in favor of queueId 940",
  },
  317: {
    map: "Crystal Scar",
    description: "Definitely Not Dominion games",
    notes: null,
  },
  318: {
    map: "소환사의 협곡",
    description: "ARURF 모드",
    notes: "Deprecated in patch 7.19 in favor of queueId 900",
  },
  325: {
    map: "소환사의 협곡",
    description: "모든 랜덤 모드",
    notes: null,
  },
  400: {
    map: "소환사의 협곡",
    description: "5대5 드래프트 픽 게임",
    notes: null,
  },
  410: {
    map: "소환사의 협곡",
    description: "5대5 랭크 다이나믹 모드",
    notes: "Game mode deprecated in patch 6.22",
  },
  420: {
    map: "소환사의 협곡",
    description: "개인/2인 랭크 게임",
    notes: null,
  },
  430: {
    map: "소환사의 협곡",
    description: "5대5 블라인드 픽 게임",
    notes: null,
  },
  440: {
    map: "소환사의 협곡",
    description: "자유 랭크 게임",
    notes: null,
  },
  450: {
    map: "Howling Abyss",
    description: "무작위 총력전",
    notes: null,
  },
  460: {
    map: "Twisted Treeline",
    description: "3v3 Blind Pick games",
    notes: "Deprecated in patch 9.23",
  },
  470: {
    map: "Twisted Treeline",
    description: "3v3 Ranked Flex games",
    notes: "Deprecated in patch 9.23",
  },
  490: {
    map: "소환사의 협곡",
    description: "노말",
    notes: null,
  },
  600: {
    map: "소환사의 협곡",
    description: "블러드 헌트 어새신 모드",
    notes: null,
  },
  610: {
    map: "Cosmic Ruins",
    description: "Dark Star: Singularity games",
    notes: null,
  },
  700: {
    map: "소환사의 협곡",
    description: "소환사의 협곡 클래시 모드",
    notes: null,
  },
  720: {
    map: "Howling Abyss",
    description: "ARAM Clash games",
    notes: null,
  },
  800: {
    map: "Twisted Treeline",
    description: "Co-op vs. AI Intermediate Bot games",
    notes: "Deprecated in patch 9.23",
  },
  810: {
    map: "Twisted Treeline",
    description: "Co-op vs. AI Intro Bot games",
    notes: "Deprecated in patch 9.23",
  },
  820: {
    map: "Twisted Treeline",
    description: "Co-op vs. AI Beginner Bot games",
    notes: null,
  },
  830: {
    map: "Summoner's Rift",
    description: "Co-op vs. AI Intro Bot games",
    notes: "Deprecated in March 2024 in favor of queueId 870",
  },
  840: {
    map: "Summoner's Rift",
    description: "Co-op vs. AI Beginner Bot games",
    notes: "Deprecated in March 2024 in favor of queueId 880",
  },
  850: {
    map: "Summoner's Rift",
    description: "Co-op vs. AI Intermediate Bot games",
    notes: "Deprecated in March 2024 in favor of queueId 890",
  },
  870: {
    map: "Summoner's Rift",
    description: "Co-op vs. AI Intro Bot games",
    notes: null,
  },
  880: {
    map: "Summoner's Rift",
    description: "Co-op vs. AI Beginner Bot games",
    notes: null,
  },
  890: {
    map: "Summoner's Rift",
    description: "Co-op vs. AI Intermediate Bot games",
    notes: null,
  },
  900: {
    map: "Summoner's Rift",
    description: "ARURF games",
    notes: null,
  },
  910: {
    map: "Crystal Scar",
    description: "Ascension games",
    notes: null,
  },
  920: {
    map: "Howling Abyss",
    description: "Legend of the Poro King games",
    notes: null,
  },
  940: {
    map: "Summoner's Rift",
    description: "Nexus Siege games",
    notes: null,
  },
  950: {
    map: "Summoner's Rift",
    description: "Doom Bots Voting games",
    notes: null,
  },
  960: {
    map: "Summoner's Rift",
    description: "Doom Bots Standard games",
    notes: null,
  },
  980: {
    map: "Valoran City Park",
    description: "Star Guardian Invasion: Normal games",
    notes: null,
  },
  990: {
    map: "Valoran City Park",
    description: "Star Guardian Invasion: Onslaught games",
    notes: null,
  },
  1000: {
    map: "Overcharge",
    description: "PROJECT: Hunters games",
    notes: null,
  },
  1010: {
    map: "Summoner's Rift",
    description: "Snow ARURF games",
    notes: null,
  },
  1020: {
    map: "Summoner's Rift",
    description: "One for All games",
    notes: null,
  },
  1030: {
    map: "Crash Site",
    description: "Odyssey Extraction: Intro games",
    notes: null,
  },
  1040: {
    map: "Crash Site",
    description: "Odyssey Extraction: Cadet games",
    notes: null,
  },
  1050: {
    map: "Crash Site",
    description: "Odyssey Extraction: Crewmember games",
    notes: null,
  },
  1060: {
    map: "Crash Site",
    description: "Odyssey Extraction: Captain games",
    notes: null,
  },
  1070: {
    map: "Crash Site",
    description: "Odyssey Extraction: Onslaught games",
    notes: null,
  },
  1090: {
    map: "Convergence",
    description: "Teamfight Tactics games",
    notes: null,
  },
  1100: {
    map: "Convergence",
    description: "Ranked Teamfight Tactics games",
    notes: null,
  },
  1110: {
    map: "Convergence",
    description: "Teamfight Tactics Tutorial games",
    notes: null,
  },
  1111: {
    map: "Convergence",
    description: "Teamfight Tactics test games",
    notes: null,
  },
  1200: {
    map: "Nexus Blitz",
    description: "Nexus Blitz games",
    notes: "Deprecated in patch 9.2",
  },
  1210: {
    map: "Convergence",
    description: "Teamfight Tactics Choncc's Treasure Mode",
    notes: "null",
  },
  1300: {
    map: "Nexus Blitz",
    description: "Nexus Blitz games",
    notes: null,
  },
  1400: {
    map: "Summoner's Rift",
    description: "Ultimate Spellbook games",
    notes: null,
  },
  1700: {
    map: "Rings of Wrath",
    description: "Arena",
    notes: null,
  },
  1710: {
    map: "Rings of Wrath",
    description: "Arena",
    notes: "16 player lobby",
  },
  1810: {
    map: "Swarm",
    description: "Swarm Mode Games",
    notes: "Swarm Mode 1 player",
  },
  1820: {
    map: "Swarm Mode Games",
    description: "Swarm",
    notes: "Swarm Mode 2 players",
  },
  1830: {
    map: "Swarm Mode Games",
    description: "Swarm",
    notes: "Swarm Mode 3 players",
  },
  1840: {
    map: "Swarm Mode Games",
    description: "Swarm",
    notes: "Swarm Mode 4 players",
  },
  1900: {
    map: "Summoner's Rift",
    description: "Pick URF games",
    notes: null,
  },
  2000: {
    map: "Summoner's Rift",
    description: "Tutorial 1",
    notes: null,
  },
  2010: {
    map: "Summoner's Rift",
    description: "Tutorial 2",
    notes: null,
  },
  2020: {
    map: "Summoner's Rift",
    description: "Tutorial 3",
    notes: null,
  },
};
