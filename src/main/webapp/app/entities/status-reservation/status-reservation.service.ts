import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IStatusReservation } from 'app/shared/model/status-reservation.model';

type EntityResponseType = HttpResponse<IStatusReservation>;
type EntityArrayResponseType = HttpResponse<IStatusReservation[]>;

@Injectable({ providedIn: 'root' })
export class StatusReservationService {
  public resourceUrl = SERVER_API_URL + 'api/status-reservations';

  constructor(protected http: HttpClient) {}

  create(statusReservation: IStatusReservation): Observable<EntityResponseType> {
    return this.http.post<IStatusReservation>(this.resourceUrl, statusReservation, { observe: 'response' });
  }

  update(statusReservation: IStatusReservation): Observable<EntityResponseType> {
    return this.http.put<IStatusReservation>(this.resourceUrl, statusReservation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStatusReservation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStatusReservation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
