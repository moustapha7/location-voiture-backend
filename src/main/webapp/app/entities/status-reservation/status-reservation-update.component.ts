import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IStatusReservation, StatusReservation } from 'app/shared/model/status-reservation.model';
import { StatusReservationService } from './status-reservation.service';

@Component({
  selector: 'jhi-status-reservation-update',
  templateUrl: './status-reservation-update.component.html',
})
export class StatusReservationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
  });

  constructor(
    protected statusReservationService: StatusReservationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ statusReservation }) => {
      this.updateForm(statusReservation);
    });
  }

  updateForm(statusReservation: IStatusReservation): void {
    this.editForm.patchValue({
      id: statusReservation.id,
      name: statusReservation.name,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const statusReservation = this.createFromForm();
    if (statusReservation.id !== undefined) {
      this.subscribeToSaveResponse(this.statusReservationService.update(statusReservation));
    } else {
      this.subscribeToSaveResponse(this.statusReservationService.create(statusReservation));
    }
  }

  private createFromForm(): IStatusReservation {
    return {
      ...new StatusReservation(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStatusReservation>>): void {
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
}
