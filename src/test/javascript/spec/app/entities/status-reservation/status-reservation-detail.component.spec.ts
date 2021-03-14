import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LocationVoitureTestModule } from '../../../test.module';
import { StatusReservationDetailComponent } from 'app/entities/status-reservation/status-reservation-detail.component';
import { StatusReservation } from 'app/shared/model/status-reservation.model';

describe('Component Tests', () => {
  describe('StatusReservation Management Detail Component', () => {
    let comp: StatusReservationDetailComponent;
    let fixture: ComponentFixture<StatusReservationDetailComponent>;
    const route = ({ data: of({ statusReservation: new StatusReservation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LocationVoitureTestModule],
        declarations: [StatusReservationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(StatusReservationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StatusReservationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load statusReservation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.statusReservation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
