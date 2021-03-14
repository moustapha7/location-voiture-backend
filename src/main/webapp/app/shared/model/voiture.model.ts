export interface IVoiture {
  id?: number;
}

export class Voiture implements IVoiture {
  constructor(public id?: number) {}
}
