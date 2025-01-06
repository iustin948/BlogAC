package blog.AC.repositories;

import blog.AC.domain.entities.RoleEntity;
import blog.AC.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findByName(String roleAdmin);
}
