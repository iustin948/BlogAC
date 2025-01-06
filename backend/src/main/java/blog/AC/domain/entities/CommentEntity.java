package blog.AC.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private ArticleEntity article;

    @ManyToOne
    private CommentEntity parent;
    private String content;
    private LocalDateTime createdAt;
    private int userId;


}
