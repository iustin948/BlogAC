package blog.AC.domain.mappers.impl;

import blog.AC.domain.dto.AdminReqDto;
import blog.AC.domain.dto.UserDto;
import blog.AC.domain.entities.AdminReqEntity;
import blog.AC.domain.entities.UserEntity;
import blog.AC.domain.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AdminReqMapperImpl implements Mapper<AdminReqEntity, AdminReqDto> {

    @Override
    public AdminReqDto mapTo(AdminReqEntity adminReqEntity) {
        return null;
    }

    @Override
    public AdminReqEntity mapFrom(AdminReqDto adminReqDto) {
        return null;
    }


    public AdminReqDto mapToDto(AdminReqEntity entity) {
        AdminReqDto dto = new AdminReqDto();

        // Manually map each field from Entity to DTO
        dto.setFirstName(entity.getFirstName());
        dto.setSecondName(entity.getSecondName());
        dto.setCurrentYear(entity.getCurrentYear());
        dto.setCurrentStudyCycle(entity.getCurrentStudyCycle());
        dto.setEmail(entity.getEmail());
        dto.setCategory(entity.getCategory());
        dto.setCoverLetter(entity.getCoverLetter());
        dto.setTimeStamp(entity.getTimeStamp());
        return dto;
    }


    public AdminReqEntity mapToEntity(AdminReqDto dto) {
        AdminReqEntity entity = new AdminReqEntity();

        // Manually map each field from DTO to Entity
        entity.setFirstName(dto.getFirstName());
        entity.setSecondName(dto.getSecondName());
        entity.setCurrentYear(dto.getCurrentYear());
        entity.setCurrentStudyCycle(dto.getCurrentStudyCycle());
        entity.setEmail(dto.getEmail());
        entity.setCategory(dto.getCategory());
        entity.setCoverLetter(dto.getCoverLetter());
        entity.setTimeStamp(dto.getTimeStamp());
        return entity;
    }
}

