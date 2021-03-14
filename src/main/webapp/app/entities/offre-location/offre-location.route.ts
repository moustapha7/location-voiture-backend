import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOffreLocation, OffreLocation } from 'app/shared/model/offre-location.model';
import { OffreLocationService } from './offre-location.service';
import { OffreLocationComponent } from './offre-location.component';
import { OffreLocationDetailComponent } from './offre-location-detail.component';
import { OffreLocationUpdateComponent } from './offre-location-update.component';

@Injectable({ providedIn: 'root' })
export class OffreLocationResolve implements Resolve<IOffreLocation> {
  constructor(private service: OffreLocationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOffreLocation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((offreLocation: HttpResponse<OffreLocation>) => {
          if (offreLocation.body) {
            return of(offreLocation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OffreLocation());
  }
}

export const offreLocationRoute: Routes = [
  {
    path: '',
    component: OffreLocationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'OffreLocations',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OffreLocationDetailComponent,
    resolve: {
      offreLocation: OffreLocationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'OffreLocations',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OffreLocationUpdateComponent,
    resolve: {
      offreLocation: OffreLocationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'OffreLocations',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OffreLocationUpdateComponent,
    resolve: {
      offreLocation: OffreLocationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'OffreLocations',
    },
    canActivate: [UserRouteAccessService],
  },
];
