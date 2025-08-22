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
    @Column(length = 65535)
    private String content;
    private LocalDateTime postedDate = LocalDateTime.now();

    @ManyToOne
    private UserEntity author;
    @ManyToOne
    private CategoryEntity category;
}
