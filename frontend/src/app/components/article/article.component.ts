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
  comments: Comment[] = [];
  newComment: string = '';
  isLoading: boolean = false;
  commentError: string = '';
  isCommentEmpty: boolean = true;
  private routeArticleId: number | null = null;
  replyingToComment: number | null = null;
  replyText: string = '';

  constructor(
    private route: ActivatedRoute,
    private articleService: ArticleService
  ) { 
    console.log('ArticleComponent instantiated');
  }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    const id = idParam !== null ? Number(idParam) : NaN;
  if (!Number.isFinite(id) || id <= 0) {
      console.error('Invalid article id from route:', idParam);
      return;
    }
  this.routeArticleId = id;
    this.articleService.getArticle(id).subscribe(article => {
      this.article = article;
      console.log('Article loaded:', this.article);
      this.loadComments(id);
    });
  }

  loadComments(articleId: number): void {
    if (!Number.isFinite(articleId) || articleId <= 0) {
      console.error('Refusing to load comments with invalid articleId:', articleId);
      return;
    }
    this.articleService.getComments(articleId).subscribe({
      next: (resp: any) => {
        // Accept both raw arrays and paginated responses with { content: Comment[] }
        const allComments = Array.isArray(resp) ? resp : (resp?.content ?? []);
        
        // Organize comments into a hierarchy (parent comments and their children)
        this.comments = this.organizeCommentsHierarchy(allComments);
      },
      error: (err) => {
        console.error('Failed to load comments', err);
      }
    });
  }
  
  organizeCommentsHierarchy(comments: Comment[]): Comment[] {
    const commentMap = new Map<number, Comment>();
    const rootComments: Comment[] = [];
    
    // First pass: Create a map of all comments by ID
    comments.forEach(comment => {
      // Ensure comment has an empty children array
      comment.children = [];
      commentMap.set(comment.id, comment);
    });
    
    // Second pass: Build the hierarchy
    comments.forEach(comment => {
      if (comment.parentId === null) {
        // This is a root comment (no parent)
        rootComments.push(comment);
      } else {
        // This is a child comment (has parent)
        const parent = commentMap.get(comment.parentId);
        if (parent) {
          if (!parent.children) {
            parent.children = [];
          }
          parent.children.push(comment);
        } else {
          // If parent comment doesn't exist, treat as root comment
          rootComments.push(comment);
        }
      }
    });
    
    return rootComments;
  }

  onCommentInput(text: string): void {
    // Update the isCommentEmpty flag based on whether the trimmed text is empty
    this.isCommentEmpty = !text || text.trim() === '';
  }

  addComment(): void {
    // Prevent duplicate submissions and empty comments
    if (this.isLoading) { 
      console.warn('Blocked: already loading');
      return; 
    }
    const text = (this.newComment || '').trim();
    if (!this.article) { 
      console.warn('Blocked: no article loaded');
      return; 
    }
    if (!text) {
      console.warn('Blocked: empty comment');
      return;
    }
    const articleId = Number.isFinite(this.article.id) && this.article.id > 0
      ? this.article.id
      : (this.routeArticleId ?? NaN);
    if (!Number.isFinite(articleId) || articleId <= 0) { 
      console.error('Blocked: invalid article id', articleId);
      return; 
    }

    console.log('Posting comment for article', articleId, 'text:', text);
    this.isLoading = true;
    this.commentError = '';

    this.articleService.addComment(articleId, text).subscribe({
      next: (newComment: Comment) => {
        this.comments.push(newComment);
        this.newComment = '';
        this.isCommentEmpty = true;
        this.isLoading = false;
      },
      error: (err) => {
        this.commentError = 'Failed to add comment. Please try again.';
        this.isLoading = false;
        console.error('Error adding comment:', err);
      }
    });
  }
  
  startReply(commentId: number): void {
    this.replyingToComment = commentId;
    this.replyText = '';
  }
  
  cancelReply(): void {
    this.replyingToComment = null;
    this.replyText = '';
  }
  
  submitReply(parentComment: Comment): void {
    if (this.isLoading || !this.replyText.trim()) {
      return;
    }
    
    const articleIdValue = this.article && this.article.id;
    const articleId = Number.isFinite(articleIdValue as number) && (articleIdValue as number) > 0
      ? (articleIdValue as number)
      : (this.routeArticleId ?? NaN);
      
    if (!Number.isFinite(articleId) || articleId <= 0) {
      console.error('Blocked: invalid article id for reply', articleId);
      return;
    }
    
    const parentId = parentComment.id;
    if (!Number.isFinite(parentId) || parentId <= 0) {
      console.error('Blocked: invalid parent comment id', parentId);
      return;
    }
    
    const text = this.replyText.trim();
    
    this.isLoading = true;
    this.commentError = '';
    
    // Add comment with parentId
    this.articleService.addComment(articleId, text, parentId).subscribe({
      next: (newSubcomment: Comment) => {
        // Add to parent's children array
        if (!parentComment.children) {
          parentComment.children = [];
        }
        parentComment.children.push(newSubcomment);
        
        // Reset reply state
        this.replyingToComment = null;
        this.replyText = '';
        this.isLoading = false;
      },
      error: (error: any) => {
        this.commentError = 'Failed to add reply. Please try again.';
        this.isLoading = false;
        console.error('Error adding reply:', error);
      }
    });
  }

  likeArticle(): void {
    console.log('Like button clicked');
    
    if (!this.article) {
      console.log('No article loaded to like');
      return;
    }

    console.log('Article ID:', this.article.id);

    if (this.article.id) {
      this.articleService.likeArticle(this.article.id).subscribe({
        next: () => {
          console.log('Like/unlike successful');
          if (this.article) {
            // Toggle the like status
            this.article.userHasLiked = !this.article.userHasLiked;
            // Update the likes count
            this.article.likes = this.article.userHasLiked 
              ? this.article.likes + 1 
              : this.article.likes - 1;
          }
        },
        error: (err) => {
          console.log('Failed to like/unlike article:', err);
        }
      });
    }
  }

  isArticleIdValid(): boolean {
    return this.article?.id !== undefined && Number.isFinite(this.article.id);
  }
}
