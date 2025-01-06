package blog.AC.repositories;

import blog.AC.domain.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface    CategoryRepository extends JpaRepository<CategoryEntity,Long> {

    @Query(value = "SELECT * FROM category_entity c WHERE LOWER(c.name) = LOWER(:name)", nativeQuery = true)
    CategoryEntity findByName(@Param("name") String name);
}
