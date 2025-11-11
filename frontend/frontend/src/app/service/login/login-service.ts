
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, BehaviorSubject, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private loggedIn = new BehaviorSubject<boolean>(this.hasToken());
  loggedIn$ = this.loggedIn.asObservable();
  private api = 'http://localhost:8080';

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) {}

  login(username: string, password: string): Observable<string> {
    return this.http.post(this.api + '/auth/login', { username, password }, { responseType: 'text' })
      .pipe(
        tap((token: string) => {
          localStorage.setItem('token', token);
          localStorage.setItem('session', 'true');
          this.loggedIn.next(true);
        })
      );
  }

  signup(username: string, password: string, role: string): Observable<string> {
    return this.http.post(this.api + '/auth/signup', { username, password, role }, { responseType: 'text' })
      .pipe(
        tap(() => {
          this.loggedIn.next(false);
        })
      );
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('session');
    localStorage.removeItem('id');
    localStorage.removeItem('tipoUsuario');
    this.loggedIn.next(false);
    this.router.navigate(['/login']);
  }

  private hasToken(): boolean {
    return !!localStorage.getItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }
}