package blog.AC.repositories;

import blog.AC.domain.entities.ArticleEntity;
import blog.AC.domain.entities.LikeEntity;
import blog.AC.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    Optional<LikeEntity> findByArticleAndUser(ArticleEntity article, UserEntity user);
    boolean existsByArticleAndUser(ArticleEntity article, UserEntity user);
    void deleteByArticleAndUser(ArticleEntity article, UserEntity user);
}
