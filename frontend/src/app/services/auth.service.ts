import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment';
// import { User } from '../models/user'; // Uncomment if you have a User model

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // sessionUser!: User; // Uncomment if you have a User model
  private apiUrl = environment.apiUrl;
  redirectUrl: string | null = null;

  constructor(private http: HttpClient) { }

  login(credentials: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, credentials).pipe(
      tap(res => {
        if (res && res.token) {
          localStorage.setItem('token', res.token);
          if (res.userId) {
            localStorage.setItem('userId', res.userId.toString());
          }
        }
      })
    );
  }

  register(user: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/register`, user);
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('userId');
    // this.sessionUser = undefined; // Uncomment if you use sessionUser
  }

  loggedIn(): boolean {
    return !!this.getToken();
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getHeader(): HttpHeaders | null {
    const token = this.getToken();
    return token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : null;
  }

  getUserId(): number | null {
    const userId = localStorage.getItem('userId');
    return userId ? parseInt(userId, 10) : null;
  }

  // Optionally, add a method to get the current user profile from backend
  // getProfile(): Observable<User> {
  //   return this.http.get<User>(`${this.apiUrl}/profile`, { headers: this.getHeader()! });
  // }
}
