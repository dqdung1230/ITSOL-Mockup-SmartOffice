import { Injectable, Injector } from '@angular/core';
import {HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthenticationService} from '../authentication.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(private authenticationService: AuthenticationService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // add auth header with jwt if user is logged in and request is to api url
    const currentUser = this.authenticationService.currentUserValue;
    const isLoggedIn = currentUser && currentUser.token;
    const jwtInterceptor = localStorage.getItem('Authorization');
    const isApiUrl = request.url.startsWith('api');

    const headersConfig = {
      'Content-Type': 'application/json',
      Accept: 'application/json',
      Authorization: undefined
    };

    if (isLoggedIn && isApiUrl) {
      headersConfig.Authorization = jwtInterceptor;
    }

    const req =  request.clone({ setHeaders: headersConfig });

    return next.handle(req);
  }
}
