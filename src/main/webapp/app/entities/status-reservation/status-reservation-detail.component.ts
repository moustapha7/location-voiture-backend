import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStatusReservation } from 'app/shared/model/status-reservation.model';

@Component({
  selector: 'jhi-status-reservation-detail',
  templateUrl: './status-reservation-detail.component.html',
})
export class StatusReservationDetailComponent implements OnInit {
  statusReservation: IStatusReservation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ statusReservation }) => (this.statusReservation = statusReservation));
  }

  previousState(): void {
    window.history.back();
  }
}
