import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOffreLocation } from 'app/shared/model/offre-location.model';

type EntityResponseType = HttpResponse<IOffreLocation>;
type EntityArrayResponseType = HttpResponse<IOffreLocation[]>;

@Injectable({ providedIn: 'root' })
export class OffreLocationService {
  public resourceUrl = SERVER_API_URL + 'api/offre-locations';

  constructor(protected http: HttpClient) {}

  create(offreLocation: IOffreLocation): Observable<EntityResponseType> {
    return this.http.post<IOffreLocation>(this.resourceUrl, offreLocation, { observe: 'response' });
  }

  update(offreLocation: IOffreLocation): Observable<EntityResponseType> {
    return this.http.put<IOffreLocation>(this.resourceUrl, offreLocation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOffreLocation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOffreLocation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
