import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {EmployeeComponent} from './employee.component';
import {EmployeeRouting} from './employee.routing';
import {EmployeeMngComponent} from './manage/employee-mng.component';
import { InsertComponent } from './manage/insert/insert.component';
import { DeleteComponent } from './manage/delete/delete.component';

@NgModule({
  imports: [
    SharedModule,
    EmployeeRouting,
  ],
  declarations: [
    EmployeeComponent,
    EmployeeMngComponent,
    InsertComponent,
    DeleteComponent
  ],
  exports: []
})
export class EmployeeModule {}
