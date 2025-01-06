package blog.AC.repositories;

import blog.AC.domain.entities.AdminReqEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminReqRepository extends JpaRepository<AdminReqEntity, Long> {
}
