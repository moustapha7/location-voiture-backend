import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'voiture',
        loadChildren: () => import('./voiture/voiture.module').then(m => m.LocationVoitureVoitureModule),
      },
      {
        path: 'vehicule',
        loadChildren: () => import('./vehicule/vehicule.module').then(m => m.LocationVoitureVehiculeModule),
      },
      {
        path: 'status-reservation',
        loadChildren: () => import('./status-reservation/status-reservation.module').then(m => m.LocationVoitureStatusReservationModule),
      },
      {
        path: 'offre-location',
        loadChildren: () => import('./offre-location/offre-location.module').then(m => m.LocationVoitureOffreLocationModule),
      },
      {
        path: 'reservation',
        loadChildren: () => import('./reservation/reservation.module').then(m => m.LocationVoitureReservationModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class LocationVoitureEntityModule {}
