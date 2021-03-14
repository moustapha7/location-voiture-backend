import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IReservation, Reservation } from 'app/shared/model/reservation.model';
import { ReservationService } from './reservation.service';
import { IVehicule } from 'app/shared/model/vehicule.model';
import { VehiculeService } from 'app/entities/vehicule/vehicule.service';
import { IStatusReservation } from 'app/shared/model/status-reservation.model';
import { StatusReservationService } from 'app/entities/status-reservation/status-reservation.service';

type SelectableEntity = IVehicule | IStatusReservation;

@Component({
  selector: 'jhi-reservation-update',
  templateUrl: './reservation-update.component.html',
})
export class ReservationUpdateComponent implements OnInit {
  isSaving = false;
  vehicules: IVehicule[] = [];
  statusreservations: IStatusReservation[] = [];

  editForm = this.fb.group({
    id: [],
    dateDepart: [],
    dateRetour: [],
    nbreJours: [],
    client: [],
    prix: [],
    vehicule: [],
    statusReservation: [],
  });

  constructor(
    protected reservationService: ReservationService,
    protected vehiculeService: VehiculeService,
    protected statusReservationService: StatusReservationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reservation }) => {
      if (!reservation.id) {
        const today = moment().startOf('day');
        reservation.dateDepart = today;
        reservation.dateRetour = today;
      }

      this.updateForm(reservation);

      this.vehiculeService.query().subscribe((res: HttpResponse<IVehicule[]>) => (this.vehicules = res.body || []));

      this.statusReservationService
        .query()
        .subscribe((res: HttpResponse<IStatusReservation[]>) => (this.statusreservations = res.body || []));
    });
  }

  updateForm(reservation: IReservation): void {
    this.editForm.patchValue({
      id: reservation.id,
      dateDepart: reservation.dateDepart ? reservation.dateDepart.format(DATE_TIME_FORMAT) : null,
      dateRetour: reservation.dateRetour ? reservation.dateRetour.format(DATE_TIME_FORMAT) : null,
      nbreJours: reservation.nbreJours,
      client: reservation.client,
      prix: reservation.prix,
      vehicule: reservation.vehicule,
      statusReservation: reservation.statusReservation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reservation = this.createFromForm();
    if (reservation.id !== undefined) {
      this.subscribeToSaveResponse(this.reservationService.update(reservation));
    } else {
      this.subscribeToSaveResponse(this.reservationService.create(reservation));
    }
  }

  private createFromForm(): IReservation {
    return {
      ...new Reservation(),
      id: this.editForm.get(['id'])!.value,
      dateDepart: this.editForm.get(['dateDepart'])!.value ? moment(this.editForm.get(['dateDepart'])!.value, DATE_TIME_FORMAT) : undefined,
      dateRetour: this.editForm.get(['dateRetour'])!.value ? moment(this.editForm.get(['dateRetour'])!.value, DATE_TIME_FORMAT) : undefined,
      nbreJours: this.editForm.get(['nbreJours'])!.value,
      client: this.editForm.get(['client'])!.value,
      prix: this.editForm.get(['prix'])!.value,
      vehicule: this.editForm.get(['vehicule'])!.value,
      statusReservation: this.editForm.get(['statusReservation'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReservation>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
