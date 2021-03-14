import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IStatusReservation, StatusReservation } from 'app/shared/model/status-reservation.model';
import { StatusReservationService } from './status-reservation.service';
import { StatusReservationComponent } from './status-reservation.component';
import { StatusReservationDetailComponent } from './status-reservation-detail.component';
import { StatusReservationUpdateComponent } from './status-reservation-update.component';

@Injectable({ providedIn: 'root' })
export class StatusReservationResolve implements Resolve<IStatusReservation> {
  constructor(private service: StatusReservationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStatusReservation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((statusReservation: HttpResponse<StatusReservation>) => {
          if (statusReservation.body) {
            return of(statusReservation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new StatusReservation());
  }
}

export const statusReservationRoute: Routes = [
  {
    path: '',
    component: StatusReservationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'StatusReservations',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StatusReservationDetailComponent,
    resolve: {
      statusReservation: StatusReservationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'StatusReservations',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: StatusReservationUpdateComponent,
    resolve: {
      statusReservation: StatusReservationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'StatusReservations',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: StatusReservationUpdateComponent,
    resolve: {
      statusReservation: StatusReservationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'StatusReservations',
    },
    canActivate: [UserRouteAccessService],
  },
];
