import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Article } from '../models/article';
import { Comment } from '../models/comment';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private articles: Article[] = [
    {
      id: 1,
      title: 'Getting Started with Angular',
      content: 'This is a comprehensive guide to start your journey with Angular.',
      author: 'John Doe',
      date: '2025-08-15',
      category: 'Programming',
      likes: 15,
      comments: [
        { user: 'Alice', content: 'Great article!' },
        { user: 'Bob', content: 'Very helpful, thanks!' }
      ]
    },
    {
      id: 2,
      title: 'University Life Hacks',
      content: 'A collection of tips and tricks to make your university life easier.',
      author: 'Jane Smith',
      date: '2025-08-16',
      category: 'University',
      likes: 30,
      comments: []
    },
    {
      id: 3,
      title: 'The Future of AI',
      content: 'An exploration of the future of Artificial Intelligence and its impact on society.',
      author: 'Peter Jones',
      date: '2025-08-17',
      category: 'Technology',
      likes: 50,
      comments: [
        { user: 'Charlie', content: 'Fascinating read.' }
      ]
    }
  ];

  constructor() { }

  getArticles(category?: string): Observable<Article[]> {
    if (category) {
      return of(this.articles.filter(article => article.category === category));
    }
    return of(this.articles);
  }

  getArticle(id: number): Observable<Article | undefined> {
    return of(this.articles.find(article => article.id === id));
  }

  likeArticle(id: number): void {
    const article = this.articles.find(article => article.id === id);
    if (article) {
      article.likes++;
    }
  }

  addComment(id: number, comment: Comment): void {
    const article = this.articles.find(article => article.id === id);
    if (article) {
      article.comments.push(comment);
    }
  }
}
