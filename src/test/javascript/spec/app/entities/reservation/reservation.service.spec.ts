import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ReservationService } from 'app/entities/reservation/reservation.service';
import { IReservation, Reservation } from 'app/shared/model/reservation.model';

describe('Service Tests', () => {
  describe('Reservation Service', () => {
    let injector: TestBed;
    let service: ReservationService;
    let httpMock: HttpTestingController;
    let elemDefault: IReservation;
    let expectedResult: IReservation | IReservation[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ReservationService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Reservation(0, currentDate, currentDate, 0, 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateDepart: currentDate.format(DATE_TIME_FORMAT),
            dateRetour: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Reservation', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateDepart: currentDate.format(DATE_TIME_FORMAT),
            dateRetour: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDepart: currentDate,
            dateRetour: currentDate,
          },
          returnedFromService
        );

        service.create(new Reservation()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Reservation', () => {
        const returnedFromService = Object.assign(
          {
            dateDepart: currentDate.format(DATE_TIME_FORMAT),
            dateRetour: currentDate.format(DATE_TIME_FORMAT),
            nbreJours: 1,
            client: 'BBBBBB',
            prix: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDepart: currentDate,
            dateRetour: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Reservation', () => {
        const returnedFromService = Object.assign(
          {
            dateDepart: currentDate.format(DATE_TIME_FORMAT),
            dateRetour: currentDate.format(DATE_TIME_FORMAT),
            nbreJours: 1,
            client: 'BBBBBB',
            prix: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDepart: currentDate,
            dateRetour: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Reservation', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
