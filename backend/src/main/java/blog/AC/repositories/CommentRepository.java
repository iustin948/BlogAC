package blog.AC.repositories;

import blog.AC.domain.entities.ArticleEntity;
import blog.AC.domain.entities.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    ArticleEntity findArticleById(Long articleId);

    Page<CommentEntity> findByArticleId(Long articleId, Pageable pageable);
}
