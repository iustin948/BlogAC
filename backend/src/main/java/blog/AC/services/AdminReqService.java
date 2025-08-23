package blog.AC.services;

import blog.AC.domain.dto.AdminReqDto;

import java.util.List;

public interface AdminReqService {
    void save(AdminReqDto adminReqDto);

    List<AdminReqDto> getAll();

    void delete(String email);

    void deleteById(Long id);
}
