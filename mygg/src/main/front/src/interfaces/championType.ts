interface ChampionInfo {
    attack: number;
    defense: number;
    magic: number;
    difficulty: number;
}

interface ChampionStats {
    attackdamageperlevel: number;
    hpperlevel: number;
    hp: number;
    mpregenperlevel: number;
    critperlevel: number;
    movespeed: number;
    mpperlevel: number;
    attackdamage: number;
    attackspeed: number;
    mpregen: number;
    attackrange: number;
    mp: number;
    hpregen: number;
    spellblock: number;
    armor: number;
    armorperlevel: number;
    crit: number;
    spellblockperlevel: number;
    attackspeedperlevel: number;
    hpregenperlevel: number;
}

interface ChampionSpell {
    id: string;
    name: string;
    description: string;
    maxrank: string;
    cooldownBurn: string;
    costBurn: string;
}

export interface IChampionDetail {
    id: string;
    key: string;
    name: string;
    title: string;
    blurb: string;
    info: ChampionInfo;
    stats: ChampionStats;
    spells: ChampionSpell[];
}

