package blog.AC.domain.dto;

import java.time.LocalDateTime;

public class ArticleDto {
    private String title;
    private String content;
    private LocalDateTime postedDate = LocalDateTime.now();
    private Long Category;
}
