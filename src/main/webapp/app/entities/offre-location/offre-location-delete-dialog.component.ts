import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOffreLocation } from 'app/shared/model/offre-location.model';
import { OffreLocationService } from './offre-location.service';

@Component({
  templateUrl: './offre-location-delete-dialog.component.html',
})
export class OffreLocationDeleteDialogComponent {
  offreLocation?: IOffreLocation;

  constructor(
    protected offreLocationService: OffreLocationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.offreLocationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('offreLocationListModification');
      this.activeModal.close();
    });
  }
}
