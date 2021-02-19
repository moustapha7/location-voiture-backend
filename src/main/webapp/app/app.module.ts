import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { LocationVoitureSharedModule } from 'app/shared/shared.module';
import { LocationVoitureCoreModule } from 'app/core/core.module';
import { LocationVoitureAppRoutingModule } from './app-routing.module';
import { LocationVoitureHomeModule } from './home/home.module';
import { LocationVoitureEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    LocationVoitureSharedModule,
    LocationVoitureCoreModule,
    LocationVoitureHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    LocationVoitureEntityModule,
    LocationVoitureAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class LocationVoitureAppModule {}
