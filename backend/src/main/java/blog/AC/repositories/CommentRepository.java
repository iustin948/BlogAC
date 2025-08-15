package blog.AC.repositories;

import blog.AC.domain.entities.ArticleEntity;
import blog.AC.domain.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    ArticleEntity findArticleById(Long articleId);
}
