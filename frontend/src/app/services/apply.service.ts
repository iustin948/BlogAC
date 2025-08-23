import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AdminReq } from '../models/admin-req';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApplyService {
  private apiUrl = `${environment.apiUrl}/apply`;

  constructor(private http: HttpClient) { }

  apply(application: AdminReq): Observable<void> {
    return this.http.post<void>(this.apiUrl, application);
  }
}
