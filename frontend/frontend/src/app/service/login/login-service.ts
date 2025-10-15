
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, BehaviorSubject, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private loggedIn = new BehaviorSubject<boolean>(this.hasSession());
  loggedIn$ = this.loggedIn.asObservable();

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) {}

  login(email: string, password: string): Observable<string> {
    const body = new HttpParams()
      .set('email', email)
      .set('password', password);

    return this.http.post('http://localhost:8080/login', body, { responseType: 'text' })
        .pipe(
    tap((response: any) => {
      const respuestas = response.split(',');
      const user = respuestas[0];
      const id = respuestas[1];

      localStorage.setItem('session', 'true');
      localStorage.setItem('id', id);
      localStorage.setItem('tipoUsuario', user); 

      this.loggedIn.next(true);
    })
  );
    }
  logout() {
    localStorage.removeItem('session');
    localStorage.removeItem('id');
    localStorage.removeItem('tipoUsuario');
    this.loggedIn.next(false);
    this.router.navigate(['/login']);
  }

  private hasSession(): boolean {
    return localStorage.getItem('session') === 'true';
  }
}