import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IVehicule, Vehicule } from 'app/shared/model/vehicule.model';
import { VehiculeService } from './vehicule.service';

@Component({
  selector: 'jhi-vehicule-update',
  templateUrl: './vehicule-update.component.html',
})
export class VehiculeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    matricule: [null, [Validators.required]],
    modele: [null, [Validators.required]],
    marque: [null, [Validators.required]],
    prix: [null, [Validators.required]],
    image: [],
  });

  constructor(protected vehiculeService: VehiculeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vehicule }) => {
      this.updateForm(vehicule);
    });
  }

  updateForm(vehicule: IVehicule): void {
    this.editForm.patchValue({
      id: vehicule.id,
      matricule: vehicule.matricule,
      modele: vehicule.modele,
      marque: vehicule.marque,
      prix: vehicule.prix,
      image: vehicule.image,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const vehicule = this.createFromForm();
    if (vehicule.id !== undefined) {
      this.subscribeToSaveResponse(this.vehiculeService.update(vehicule));
    } else {
      this.subscribeToSaveResponse(this.vehiculeService.create(vehicule));
    }
  }

  private createFromForm(): IVehicule {
    return {
      ...new Vehicule(),
      id: this.editForm.get(['id'])!.value,
      matricule: this.editForm.get(['matricule'])!.value,
      modele: this.editForm.get(['modele'])!.value,
      marque: this.editForm.get(['marque'])!.value,
      prix: this.editForm.get(['prix'])!.value,
      image: this.editForm.get(['image'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVehicule>>): void {
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
