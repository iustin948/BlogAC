import { Comment } from './comment';

export interface Article {
  id: number;
  title: string;
  content: string;
  author: string;
  date: string;
  category: string;
  likes: number;
  comments: Comment[];
}
