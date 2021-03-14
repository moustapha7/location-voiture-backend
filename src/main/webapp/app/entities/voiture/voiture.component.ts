import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IVoiture } from 'app/shared/model/voiture.model';
import { VoitureService } from './voiture.service';
import { VoitureDeleteDialogComponent } from './voiture-delete-dialog.component';

@Component({
  selector: 'jhi-voiture',
  templateUrl: './voiture.component.html',
})
export class VoitureComponent implements OnInit, OnDestroy {
  voitures?: IVoiture[];
  eventSubscriber?: Subscription;

  constructor(protected voitureService: VoitureService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.voitureService.query().subscribe((res: HttpResponse<IVoiture[]>) => (this.voitures = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInVoitures();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IVoiture): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInVoitures(): void {
    this.eventSubscriber = this.eventManager.subscribe('voitureListModification', () => this.loadAll());
  }

  delete(voiture: IVoiture): void {
    const modalRef = this.modalService.open(VoitureDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.voiture = voiture;
  }
}
