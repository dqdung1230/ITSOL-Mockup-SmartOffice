import {NgModule} from '@angular/core';
import {AuthComponent} from './auth.component';
import {SharedModule} from '../../shared/shared.module';
import {LoginComponent} from './login/login.component';
import {AuthRouting} from './auth-routing';
import {AuthenticationService} from '../../_services/authentication.service';

@NgModule({
  imports:[ SharedModule, AuthRouting ],
  declarations:[ AuthComponent, LoginComponent ],
  exports:[],
  providers: [ AuthenticationService ]
})
export class AuthModule {}
