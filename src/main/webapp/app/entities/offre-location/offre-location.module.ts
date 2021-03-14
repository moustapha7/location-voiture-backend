import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LocationVoitureSharedModule } from 'app/shared/shared.module';
import { OffreLocationComponent } from './offre-location.component';
import { OffreLocationDetailComponent } from './offre-location-detail.component';
import { OffreLocationUpdateComponent } from './offre-location-update.component';
import { OffreLocationDeleteDialogComponent } from './offre-location-delete-dialog.component';
import { offreLocationRoute } from './offre-location.route';

@NgModule({
  imports: [LocationVoitureSharedModule, RouterModule.forChild(offreLocationRoute)],
  declarations: [OffreLocationComponent, OffreLocationDetailComponent, OffreLocationUpdateComponent, OffreLocationDeleteDialogComponent],
  entryComponents: [OffreLocationDeleteDialogComponent],
})
export class LocationVoitureOffreLocationModule {}
