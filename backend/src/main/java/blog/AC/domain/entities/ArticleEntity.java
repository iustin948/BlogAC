package blog.AC.domain.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String title;
    private String content;
    private LocalDateTime postedDate = LocalDateTime.now();

    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
