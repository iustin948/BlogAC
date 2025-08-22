import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Article, PaginatedArticles } from '../models/article';
import { Comment, PaginatedComment } from '../models/comment';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private apiUrl = `${environment.apiUrl}/article`;

  constructor(private http: HttpClient) { }

  getArticles(category?: string, page: number = 0, limit: number = 10): Observable<PaginatedArticles> {
    let params = new HttpParams().set('page', page.toString()).set('size', limit.toString());
    if (category) {
      params = params.set('category', category);
    }
    return this.http.get<PaginatedArticles>(`${this.apiUrl}`, { params });
  }

  getArticle(id: number): Observable<Article> {
    if (!Number.isFinite(id) || id <= 0) {
      throw new Error(`Invalid article id: ${id}`);
    }
    return this.http.get<Article>(`${this.apiUrl}/${id}`);
  }
  
  getComments(articleId: number): Observable<PaginatedComment> {
    if (!Number.isFinite(articleId) || articleId <= 0) {
      throw new Error(`Invalid article id for comments: ${articleId}`);
    }
    return this.http.get<PaginatedComment>(`${this.apiUrl}/${articleId}/comments`);
  }

  addComment(articleId: number, content: string, parentId?: number): Observable<Comment> {
    if (!Number.isFinite(articleId) || articleId <= 0) {
      throw new Error(`Invalid article id for addComment: ${articleId}`);
    }
    const commentData: { content: string; parentId?: number } = { content };
    if (parentId) {
      commentData.parentId = parentId;
    }
    return this.http.post<Comment>(
      `${this.apiUrl}/${articleId}/comments`,
      commentData
    );
  }

  addArticle(article: any): Observable<Article> {
    return this.http.post<Article>(this.apiUrl, article);
  }

  patchArticle(articleId: number, article: Partial<Article>): Observable<Article> {
    // The backend expects PATCH /article/{articleId}?articleId=... with body
    return this.http.patch<Article>(`${this.apiUrl}/${articleId}`, article, {
      params: new HttpParams().set('articleId', articleId)
    });
  }

  deleteArticle(articleId: number): Observable<void> {
    // The backend expects DELETE /article/{articleId}?articleId=...
    return this.http.delete<void>(`${this.apiUrl}/${articleId}`, {
      params: new HttpParams().set('articleId', articleId)
    });
  }
}
