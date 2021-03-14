import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LocationVoitureTestModule } from '../../../test.module';
import { OffreLocationDetailComponent } from 'app/entities/offre-location/offre-location-detail.component';
import { OffreLocation } from 'app/shared/model/offre-location.model';

describe('Component Tests', () => {
  describe('OffreLocation Management Detail Component', () => {
    let comp: OffreLocationDetailComponent;
    let fixture: ComponentFixture<OffreLocationDetailComponent>;
    const route = ({ data: of({ offreLocation: new OffreLocation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LocationVoitureTestModule],
        declarations: [OffreLocationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OffreLocationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OffreLocationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load offreLocation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.offreLocation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
