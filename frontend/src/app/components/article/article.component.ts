import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticleService } from '../../services/article.service';
import { Article } from '../../models/article';
import { Comment } from '../../models/comment';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})
export class ArticleComponent implements OnInit {
  article: Article | undefined;
  newComment: string = '';

  constructor(
    private route: ActivatedRoute,
    private articleService: ArticleService
  ) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.articleService.getArticle(id).subscribe(article => {
      this.article = article;
    });
  }

  like(): void {
    if (this.article) {
      this.articleService.likeArticle(this.article.id);
    }
  }

  addComment(): void {
    if (this.article && this.newComment) {
      const comment: Comment = { user: 'CurrentUser', content: this.newComment };
      this.articleService.addComment(this.article.id, comment);
      this.newComment = '';
    }
  }
}
