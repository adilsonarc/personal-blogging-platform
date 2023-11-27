import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  API = "http:localhost:8080/api/v1/auth";

  constructor(private http: HttpClient) {
  }

  register(request: RegisterRequestModel): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.API}\\register`, request);
  }

  authenticate(request: AuthenticationRequestModel): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.API}\\authenticate`, request);
  }
}
