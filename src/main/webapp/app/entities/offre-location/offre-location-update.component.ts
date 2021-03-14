import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOffreLocation, OffreLocation } from 'app/shared/model/offre-location.model';
import { OffreLocationService } from './offre-location.service';
import { IVehicule } from 'app/shared/model/vehicule.model';
import { VehiculeService } from 'app/entities/vehicule/vehicule.service';

@Component({
  selector: 'jhi-offre-location-update',
  templateUrl: './offre-location-update.component.html',
})
export class OffreLocationUpdateComponent implements OnInit {
  isSaving = false;
  vehicules: IVehicule[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [],
    nbreJours: [],
    prix: [],
    vehicule: [],
  });

  constructor(
    protected offreLocationService: OffreLocationService,
    protected vehiculeService: VehiculeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offreLocation }) => {
      this.updateForm(offreLocation);

      this.vehiculeService.query().subscribe((res: HttpResponse<IVehicule[]>) => (this.vehicules = res.body || []));
    });
  }

  updateForm(offreLocation: IOffreLocation): void {
    this.editForm.patchValue({
      id: offreLocation.id,
      libelle: offreLocation.libelle,
      nbreJours: offreLocation.nbreJours,
      prix: offreLocation.prix,
      vehicule: offreLocation.vehicule,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const offreLocation = this.createFromForm();
    if (offreLocation.id !== undefined) {
      this.subscribeToSaveResponse(this.offreLocationService.update(offreLocation));
    } else {
      this.subscribeToSaveResponse(this.offreLocationService.create(offreLocation));
    }
  }

  private createFromForm(): IOffreLocation {
    return {
      ...new OffreLocation(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      nbreJours: this.editForm.get(['nbreJours'])!.value,
      prix: this.editForm.get(['prix'])!.value,
      vehicule: this.editForm.get(['vehicule'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOffreLocation>>): void {
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

  trackById(index: number, item: IVehicule): any {
    return item.id;
  }
}
