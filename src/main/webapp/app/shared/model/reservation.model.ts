import { Moment } from 'moment';
import { IVehicule } from 'app/shared/model/vehicule.model';
import { IStatusReservation } from 'app/shared/model/status-reservation.model';

export interface IReservation {
  id?: number;
  dateDepart?: Moment;
  dateRetour?: Moment;
  nbreJours?: number;
  client?: string;
  prix?: number;
  vehicule?: IVehicule;
  statusReservation?: IStatusReservation;
}

export class Reservation implements IReservation {
  constructor(
    public id?: number,
    public dateDepart?: Moment,
    public dateRetour?: Moment,
    public nbreJours?: number,
    public client?: string,
    public prix?: number,
    public vehicule?: IVehicule,
    public statusReservation?: IStatusReservation
  ) {}
}
