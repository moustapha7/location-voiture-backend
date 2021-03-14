import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LocationVoitureTestModule } from '../../../test.module';
import { VoitureComponent } from 'app/entities/voiture/voiture.component';
import { VoitureService } from 'app/entities/voiture/voiture.service';
import { Voiture } from 'app/shared/model/voiture.model';

describe('Component Tests', () => {
  describe('Voiture Management Component', () => {
    let comp: VoitureComponent;
    let fixture: ComponentFixture<VoitureComponent>;
    let service: VoitureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LocationVoitureTestModule],
        declarations: [VoitureComponent],
      })
        .overrideTemplate(VoitureComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VoitureComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VoitureService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Voiture(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.voitures && comp.voitures[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
