import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, EMPTY, of} from 'rxjs';
import {switchMap} from 'rxjs/operators';
import {AuthService} from "../services/auth/auth.service";

@Injectable()
export class HttpClientInterceptor implements HttpInterceptor {

  API = "http://localhost:8080/api/v1/auth";
  skipAuthUrls = ['/login', '/register'];

  constructor(private authService: AuthService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const skipAuth = this.skipAuthUrls.some(url => request.url.includes(url));
    const token$: Observable<string | null> = skipAuth ? of(null) : this.authService.getToken();

    // Handle the auth token as an Observable
    return token$.pipe(switchMap((authToken: string | null) => {
        // Clone the request to add new headers
        const modifiedRequest = request.clone({
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': authToken ? `Bearer ${authToken}` : '' // Add the token to the Authorization header
          }),
          url: `${this.API}/${request.url}`
        });

        // Forward the modified request
        return next.handle(modifiedRequest);
      }));
  }
}
