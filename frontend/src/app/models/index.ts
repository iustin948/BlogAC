export interface CommentModel {
  user: string;
  content: string;
}

export interface ArticleModel {
  id: number;
  title: string;
  content: string;
  author: string;
  date: string;
  category: string;
  likes: number;
  comments: CommentModel[];
}

export { CommentModel as Comment };
export { ArticleModel as Article };
export * from './user';
export * from './admin-req';
