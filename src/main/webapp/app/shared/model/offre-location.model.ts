import { IVehicule } from 'app/shared/model/vehicule.model';

export interface IOffreLocation {
  id?: number;
  libelle?: string;
  nbreJours?: number;
  prix?: number;
  vehicule?: IVehicule;
}

export class OffreLocation implements IOffreLocation {
  constructor(public id?: number, public libelle?: string, public nbreJours?: number, public prix?: number, public vehicule?: IVehicule) {}
}
