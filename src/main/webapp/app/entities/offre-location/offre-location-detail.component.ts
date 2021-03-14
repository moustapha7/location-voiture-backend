import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOffreLocation } from 'app/shared/model/offre-location.model';

@Component({
  selector: 'jhi-offre-location-detail',
  templateUrl: './offre-location-detail.component.html',
})
export class OffreLocationDetailComponent implements OnInit {
  offreLocation: IOffreLocation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offreLocation }) => (this.offreLocation = offreLocation));
  }

  previousState(): void {
    window.history.back();
  }
}
