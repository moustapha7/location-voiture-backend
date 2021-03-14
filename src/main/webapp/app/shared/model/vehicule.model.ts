export interface IVehicule {
  id?: number;
  matricule?: string;
  modele?: string;
  marque?: string;
  prix?: number;
  image?: string;
}

export class Vehicule implements IVehicule {
  constructor(
    public id?: number,
    public matricule?: string,
    public modele?: string,
    public marque?: string,
    public prix?: number,
    public image?: string
  ) {}
}
