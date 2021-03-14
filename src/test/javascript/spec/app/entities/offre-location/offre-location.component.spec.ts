import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LocationVoitureTestModule } from '../../../test.module';
import { OffreLocationComponent } from 'app/entities/offre-location/offre-location.component';
import { OffreLocationService } from 'app/entities/offre-location/offre-location.service';
import { OffreLocation } from 'app/shared/model/offre-location.model';

describe('Component Tests', () => {
  describe('OffreLocation Management Component', () => {
    let comp: OffreLocationComponent;
    let fixture: ComponentFixture<OffreLocationComponent>;
    let service: OffreLocationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LocationVoitureTestModule],
        declarations: [OffreLocationComponent],
      })
        .overrideTemplate(OffreLocationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OffreLocationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OffreLocationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new OffreLocation(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.offreLocations && comp.offreLocations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
