export interface IStatusReservation {
  id?: number;
  name?: string;
}

export class StatusReservation implements IStatusReservation {
  constructor(public id?: number, public name?: string) {}
}
