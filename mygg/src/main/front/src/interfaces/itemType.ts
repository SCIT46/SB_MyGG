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
  };
}

export interface IItemDetail {
  name: string;
  description: string;
  id: string;
  gold: {
    base: number;
    purchasable: boolean;
    total: number;
    sell: number;
  };
}
