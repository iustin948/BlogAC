export interface PaginatedComment {
  content: Comment[];
}

export interface Comment {
  id: number;
  articleId: number;
  authorId: number;
  authorName: string;
  parentId: number | null;
  content: string;
  createdAt: string;
  children?: Comment[];
}