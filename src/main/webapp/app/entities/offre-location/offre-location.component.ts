import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOffreLocation } from 'app/shared/model/offre-location.model';
import { OffreLocationService } from './offre-location.service';
import { OffreLocationDeleteDialogComponent } from './offre-location-delete-dialog.component';

@Component({
  selector: 'jhi-offre-location',
  templateUrl: './offre-location.component.html',
})
export class OffreLocationComponent implements OnInit, OnDestroy {
  offreLocations?: IOffreLocation[];
  eventSubscriber?: Subscription;

  constructor(
    protected offreLocationService: OffreLocationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.offreLocationService.query().subscribe((res: HttpResponse<IOffreLocation[]>) => (this.offreLocations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOffreLocations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOffreLocation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOffreLocations(): void {
    this.eventSubscriber = this.eventManager.subscribe('offreLocationListModification', () => this.loadAll());
  }

  delete(offreLocation: IOffreLocation): void {
    const modalRef = this.modalService.open(OffreLocationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.offreLocation = offreLocation;
  }
}
