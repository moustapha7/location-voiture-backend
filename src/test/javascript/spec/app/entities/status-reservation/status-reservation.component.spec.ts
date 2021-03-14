import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LocationVoitureTestModule } from '../../../test.module';
import { StatusReservationComponent } from 'app/entities/status-reservation/status-reservation.component';
import { StatusReservationService } from 'app/entities/status-reservation/status-reservation.service';
import { StatusReservation } from 'app/shared/model/status-reservation.model';

describe('Component Tests', () => {
  describe('StatusReservation Management Component', () => {
    let comp: StatusReservationComponent;
    let fixture: ComponentFixture<StatusReservationComponent>;
    let service: StatusReservationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LocationVoitureTestModule],
        declarations: [StatusReservationComponent],
      })
        .overrideTemplate(StatusReservationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StatusReservationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StatusReservationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new StatusReservation(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.statusReservations && comp.statusReservations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
