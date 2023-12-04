import {Injectable} from '@angular/core';
import {map, mergeMap, Observable, Subject, tap} from "rxjs";
import {HttpClient} from '@angular/common/http';
import {AuthRequest} from "./auth.request";
import {SignUpRequest} from "./sign-up.request";
import {AuthResponse} from "./auth.response";
import {LogoutRequest} from "./logout.request";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  token$ = new Subject<any>();
  constructor(private http: HttpClient) {
  }

  signUp(request: SignUpRequest): Observable<void> {
    const response$ = this.http.post<AuthResponse>(`sign-up`, request)
      .pipe(tap(r => console.debug(r)));
    response$.subscribe(token => this.token$.next(token));
    return response$.pipe(mergeMap(() => new Observable<void>()));
  }

  login(request: AuthRequest): Observable<void> {
    const response$ = this.http.post<AuthResponse>(`login`, request);
    response$.subscribe(token => this.token$.next(token));
    return response$.pipe(mergeMap(() => new Observable<void>()));
  }

  getToken(): Observable<string> {
    return this.token$.asObservable();
  }
}
