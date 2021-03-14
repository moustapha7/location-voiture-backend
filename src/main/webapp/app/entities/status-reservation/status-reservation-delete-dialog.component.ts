import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStatusReservation } from 'app/shared/model/status-reservation.model';
import { StatusReservationService } from './status-reservation.service';

@Component({
  templateUrl: './status-reservation-delete-dialog.component.html',
})
export class StatusReservationDeleteDialogComponent {
  statusReservation?: IStatusReservation;

  constructor(
    protected statusReservationService: StatusReservationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.statusReservationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('statusReservationListModification');
      this.activeModal.close();
    });
  }
}
