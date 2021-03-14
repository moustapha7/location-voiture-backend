import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IVoiture, Voiture } from 'app/shared/model/voiture.model';
import { VoitureService } from './voiture.service';

@Component({
  selector: 'jhi-voiture-update',
  templateUrl: './voiture-update.component.html',
})
export class VoitureUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
  });

  constructor(protected voitureService: VoitureService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ voiture }) => {
      this.updateForm(voiture);
    });
  }

  updateForm(voiture: IVoiture): void {
    this.editForm.patchValue({
      id: voiture.id,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const voiture = this.createFromForm();
    if (voiture.id !== undefined) {
      this.subscribeToSaveResponse(this.voitureService.update(voiture));
    } else {
      this.subscribeToSaveResponse(this.voitureService.create(voiture));
    }
  }

  private createFromForm(): IVoiture {
    return {
      ...new Voiture(),
      id: this.editForm.get(['id'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVoiture>>): void {
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
