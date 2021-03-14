import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LocationVoitureTestModule } from '../../../test.module';
import { OffreLocationUpdateComponent } from 'app/entities/offre-location/offre-location-update.component';
import { OffreLocationService } from 'app/entities/offre-location/offre-location.service';
import { OffreLocation } from 'app/shared/model/offre-location.model';

describe('Component Tests', () => {
  describe('OffreLocation Management Update Component', () => {
    let comp: OffreLocationUpdateComponent;
    let fixture: ComponentFixture<OffreLocationUpdateComponent>;
    let service: OffreLocationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LocationVoitureTestModule],
        declarations: [OffreLocationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OffreLocationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OffreLocationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OffreLocationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OffreLocation(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new OffreLocation();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
