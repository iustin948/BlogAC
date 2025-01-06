package blog.AC.repositories;

import blog.AC.domain.entities.PrivilegeEntity;
import blog.AC.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<PrivilegeEntity,Long> {
    PrivilegeEntity findByName(String name);
}
