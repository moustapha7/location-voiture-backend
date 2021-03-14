import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IVehicule } from 'app/shared/model/vehicule.model';
import { VehiculeService } from './vehicule.service';
import { VehiculeDeleteDialogComponent } from './vehicule-delete-dialog.component';

@Component({
  selector: 'jhi-vehicule',
  templateUrl: './vehicule.component.html',
})
export class VehiculeComponent implements OnInit, OnDestroy {
  vehicules?: IVehicule[];
  eventSubscriber?: Subscription;

  constructor(protected vehiculeService: VehiculeService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.vehiculeService.query().subscribe((res: HttpResponse<IVehicule[]>) => (this.vehicules = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInVehicules();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IVehicule): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInVehicules(): void {
    this.eventSubscriber = this.eventManager.subscribe('vehiculeListModification', () => this.loadAll());
  }

  delete(vehicule: IVehicule): void {
    const modalRef = this.modalService.open(VehiculeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.vehicule = vehicule;
  }
}
