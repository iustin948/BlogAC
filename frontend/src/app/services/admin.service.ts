import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AdminReq } from '../models/admin-req';
import { environment } from '../../environments/environment';
import { User } from '../models';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private apiUrl = `${environment.apiUrl}/admin`;

  constructor(private http: HttpClient) { }

  getRequests(): Observable<AdminReq[]> {
    return this.http.get<AdminReq[]>(`${this.apiUrl}/requests`);
  }

  approveRequest(id: number): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/approve/${id}`, {});
  }

  rejectRequest(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/reject/${id}`);
  }
}
