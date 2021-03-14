import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LocationVoitureTestModule } from '../../../test.module';
import { StatusReservationUpdateComponent } from 'app/entities/status-reservation/status-reservation-update.component';
import { StatusReservationService } from 'app/entities/status-reservation/status-reservation.service';
import { StatusReservation } from 'app/shared/model/status-reservation.model';

describe('Component Tests', () => {
  describe('StatusReservation Management Update Component', () => {
    let comp: StatusReservationUpdateComponent;
    let fixture: ComponentFixture<StatusReservationUpdateComponent>;
    let service: StatusReservationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LocationVoitureTestModule],
        declarations: [StatusReservationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(StatusReservationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StatusReservationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StatusReservationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new StatusReservation(123);
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
        const entity = new StatusReservation();
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
