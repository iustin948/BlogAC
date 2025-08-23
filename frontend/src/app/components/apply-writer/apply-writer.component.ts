import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminReq } from '../../models/admin-req';
import { ApplyService } from '../../services/apply.service';
import { Category, CategoryService } from '../../services/category.service';

@Component({
  selector: 'app-apply-writer',
  templateUrl: './apply-writer.component.html',
  styleUrls: ['./apply-writer.component.css']
})
export class ApplyWriterComponent implements OnInit {

  application: AdminReq = {
    firstName: '',
    secondName: '',
    currentYear: 1,
    currentStudyCycle: 'Bachelor',
    email: '',
    category: '',
    coverLetter: ''
  };
  categories: Category[] = [];

  constructor(
    private applyService: ApplyService,
    private categoryService: CategoryService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(categories => {
      this.categories = categories;
      if (this.categories.length > 0) {
        this.application.category = this.categories[0].name;
      }
    });
  }

  apply(): void {
    this.applyService.apply(this.application).subscribe(() => {
      // Optionally, show a success message
      this.router.navigate(['/']);
    });
  }
}
