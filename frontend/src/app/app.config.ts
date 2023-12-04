import {ApplicationConfig, importProvidersFrom} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientInterceptor} from "./config/http-client.interceptor";

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes),
    importProvidersFrom(HttpClientModule),
    ReactiveFormsModule,
    {provide: HTTP_INTERCEPTORS, useClass: HttpClientInterceptor, multi: true}]
};
