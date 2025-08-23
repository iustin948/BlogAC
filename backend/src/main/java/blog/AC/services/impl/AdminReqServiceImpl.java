package blog.AC.services.impl;

import blog.AC.domain.dto.AdminReqDto;
import blog.AC.domain.entities.AdminReqEntity;
import blog.AC.domain.mappers.impl.AdminReqMapperImpl;
import blog.AC.repositories.AdminReqRepository;
import blog.AC.services.AdminReqService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AdminReqServiceImpl implements AdminReqService {

    private final AdminReqRepository adminReqRepository;
    private final AdminReqMapperImpl adminReqMapper;

    @Override
    public void save(AdminReqDto adminReqDto) {
        AdminReqEntity adminReqEntity = adminReqMapper.mapFrom(adminReqDto);
        adminReqEntity.setTimeStamp(LocalDateTime.now());
        adminReqRepository.save(adminReqEntity);
    }
}
