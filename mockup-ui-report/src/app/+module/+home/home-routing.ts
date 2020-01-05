import {ModuleWithProviders, NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home.component';
import {MonitorComponent} from './monitor/monitor.component';

const homeRoutes: Routes = [
  {
    path: '',
    component: HomeComponent,
    data: {pageTitle: 'Home'},
    children: [
      {
        path: '',
        redirectTo: 'monitor',
        pathMatch: 'full'
      },
      { path: 'monitor',
        component: MonitorComponent
      }
    ]
  }
];

export const HomeRouting: ModuleWithProviders = RouterModule.forChild(homeRoutes);
