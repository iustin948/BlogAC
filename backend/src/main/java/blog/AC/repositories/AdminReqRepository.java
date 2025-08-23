package blog.AC.repositories;

import blog.AC.domain.entities.AdminReqEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminReqRepository extends JpaRepository<AdminReqEntity, Long> {
    Optional<AdminReqEntity> findByEmail(String email);
}
