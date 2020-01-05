import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {HomeComponent} from './home.component';
import {HomeRouting} from './home-routing';
import {MonitorComponent} from './monitor/monitor.component';

@NgModule({
  imports: [
    SharedModule,
    HomeRouting
  ],
  declarations: [
    HomeComponent,
    MonitorComponent
  ],
  exports: []
})
export class HomeModule {}
