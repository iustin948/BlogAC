export interface PaginatedComment {
  content: Comment[];
}

export interface Comment {
  id: number;
  articleId: number;
  userId: number;
  parentId: number | null;
  content: string;
  createdAt: string;
  children?: Comment[];
}