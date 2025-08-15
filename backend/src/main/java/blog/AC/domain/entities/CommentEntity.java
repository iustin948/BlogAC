package blog.AC.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ArticleEntity article;

    @ManyToOne
    private CommentEntity parent;
    private String content;
    private LocalDateTime createdAt;
    private Long userId;


}
