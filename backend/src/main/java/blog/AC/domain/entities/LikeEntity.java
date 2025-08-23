package blog.AC.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "article_likes", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"article_id", "user_id"})
})
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity article;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    
    public LikeEntity(ArticleEntity article, UserEntity user) {
        this.article = article;
        this.user = user;
    }
}
