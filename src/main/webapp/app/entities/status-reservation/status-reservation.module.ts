import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LocationVoitureSharedModule } from 'app/shared/shared.module';
import { StatusReservationComponent } from './status-reservation.component';
import { StatusReservationDetailComponent } from './status-reservation-detail.component';
import { StatusReservationUpdateComponent } from './status-reservation-update.component';
import { StatusReservationDeleteDialogComponent } from './status-reservation-delete-dialog.component';
import { statusReservationRoute } from './status-reservation.route';

@NgModule({
  imports: [LocationVoitureSharedModule, RouterModule.forChild(statusReservationRoute)],
  declarations: [
    StatusReservationComponent,
    StatusReservationDetailComponent,
    StatusReservationUpdateComponent,
    StatusReservationDeleteDialogComponent,
  ],
  entryComponents: [StatusReservationDeleteDialogComponent],
})
export class LocationVoitureStatusReservationModule {}
