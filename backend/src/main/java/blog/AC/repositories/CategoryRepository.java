package blog.AC.repositories;

import blog.AC.domain.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface    CategoryRepository extends JpaRepository<CategoryEntity,Long> {
}
