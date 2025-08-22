import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ArticleService } from '../../services/article.service';
import { AuthService } from '../../services/auth.service';
import { Category, CategoryService } from '../../services/category.service';
import { Article } from '../../models/article';

@Component({
  selector: 'app-article-add',
  templateUrl: './article-add.component.html',
  styleUrls: ['./article-add.component.css']
})
export class ArticleAddComponent implements OnInit {
  article: { title: string; content: string; category: string | null } = {
    title: '',
    content: '',
    category: null
  };
  categories: Category[] = [];

  constructor(
    private articleService: ArticleService,
    private router: Router,
    private authService: AuthService,
    private categoryService: CategoryService
  ) { }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(categories => {
      this.categories = categories;
    });
  }

  onSubmit() {
    const userId = this.authService.getUserId();
    if (this.authService.loggedIn()) {
      const articleData = {
        title: this.article.title,
        content: this.article.content,
        category: this.article.category,
        authorId: userId,
        postedDate: new Date().toISOString()
      };
        this.articleService.addArticle(articleData).subscribe(() => {
          
        this.router.navigate(['/']);
      });
    } else {
      // Handle case where user is not logged in
      this.router.navigate(['/login']);
    }
  }
}
