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
  image: {
    full: string;
    sprite: string;
    group: string;
  };
  tags: string[];
}
