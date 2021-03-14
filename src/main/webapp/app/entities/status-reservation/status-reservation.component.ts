import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IStatusReservation } from 'app/shared/model/status-reservation.model';
import { StatusReservationService } from './status-reservation.service';
import { StatusReservationDeleteDialogComponent } from './status-reservation-delete-dialog.component';

@Component({
  selector: 'jhi-status-reservation',
  templateUrl: './status-reservation.component.html',
})
export class StatusReservationComponent implements OnInit, OnDestroy {
  statusReservations?: IStatusReservation[];
  eventSubscriber?: Subscription;

  constructor(
    protected statusReservationService: StatusReservationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.statusReservationService
      .query()
      .subscribe((res: HttpResponse<IStatusReservation[]>) => (this.statusReservations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInStatusReservations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IStatusReservation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInStatusReservations(): void {
    this.eventSubscriber = this.eventManager.subscribe('statusReservationListModification', () => this.loadAll());
  }

  delete(statusReservation: IStatusReservation): void {
    const modalRef = this.modalService.open(StatusReservationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.statusReservation = statusReservation;
  }
}
