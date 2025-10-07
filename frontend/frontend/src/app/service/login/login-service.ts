import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) { }
  login(email: string, password: string): Observable<String> {
     const body = new HttpParams()
    .set('email', email)
    .set('password', password);
    return this.http.post('http://localhost:8080/login', body, { responseType: 'text' })
  }

}
