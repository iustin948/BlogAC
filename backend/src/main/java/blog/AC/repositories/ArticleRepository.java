package blog.AC.repositories;
import blog.AC.domain.entities.ArticleEntity;
import blog.AC.domain.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    @Query("""
       SELECT a FROM ArticleEntity a
       WHERE (:author IS NULL OR a.author = :author)
         AND (:category IS NULL OR a.category = :category)
       """)
    Page<ArticleEntity> findByUserAndCategory(
            @Param("author") UserEntity author,
            @Param("category") String category,
            Pageable pageable);
}
