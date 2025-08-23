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

  loggedIn(): { isLoggedIn: boolean; roles: string[] } {
    const token = this.getToken();
    if (!token) {
      return { isLoggedIn: false, roles: [] };
    }

    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      const roles = payload.roles || [];
      return { isLoggedIn: true, roles };
    } catch (error) {
      console.error('Error decoding token:', error);
      return { isLoggedIn: false, roles: [] };
    }
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

  isAdmin(): boolean {
    const token = this.getToken();
    if (!token) {
      return false;
    }
    const payload = JSON.parse(atob(token.split('.')[1]));
    return payload.roles.includes('ROLE_ADMIN');
  }

  // Optionally, add a method to get the current user profile from backend
  // getProfile(): Observable<User> {
  //   return this.http.get<User>(`${this.apiUrl}/profile`, { headers: this.getHeader()! });
  // }
}
