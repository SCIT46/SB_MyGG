export interface IItems {
  type: string;
  version: string;
  data: { [itemId: string]: IItem };
}

export interface IItem {
  name: string;
  description: string;
  colloq: string;
  plaintext: string;
  into: string[];
  inStore?: boolean;
  image: {
    full: string;
    sprite: string;
    group: string;
  };
  tags: string[];
  maps: {
    [mapId: string]: boolean;
  };
  gold: {
    base: number;
    total: number;
    sell: number;
    purchasable: boolean;
  };
}

export interface IItemDetail {
  name: string;
  description: string;
  id: string;

  from: string[];
  into: string[];
  gold: {
    base: number;
    purchasable: boolean;
    total: number;
    sell: number;
  };
  stats: {
    [key: string]: number;
  };
}
