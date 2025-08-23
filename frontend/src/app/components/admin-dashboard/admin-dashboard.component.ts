import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { AdminReq } from '../../models/admin-req';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  requests: AdminReq[] = [];

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    this.loadRequests();
  }

  loadRequests(): void {
    this.adminService.getRequests().subscribe(data => {
      this.requests = data;
    });
  }

  approve(id: number): void {
    this.adminService.approveRequest(id).subscribe(() => {
      this.loadRequests();
    });
  }

  reject(id: number): void {
    this.adminService.rejectRequest(id).subscribe(() => {
      this.loadRequests();
    });
  }
}
