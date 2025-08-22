package blog.AC.domain.dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class ArticleDto {
    private String title;
    private String content;
    private LocalDateTime postedDate = LocalDateTime.now();
    private Long category;
    private Long id;
    private Long authorId;
    private String authorName;
    private String categoryName;
}
