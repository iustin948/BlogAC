package blog.AC.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String content;
    private String createdAt;
    private Long authorId;
    private String authorName;
    private Long articleId;
    private Long parentId; // Nullable for root comments



}
