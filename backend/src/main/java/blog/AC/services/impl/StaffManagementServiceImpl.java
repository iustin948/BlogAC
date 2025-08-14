package blog.AC.services.impl;

import blog.AC.domain.dto.AdminReqDto;
import blog.AC.domain.dto.UserDto;
import blog.AC.domain.entities.AdminReqEntity;
import blog.AC.domain.entities.CategoryEntity;
import blog.AC.domain.entities.RoleEntity;
import blog.AC.domain.entities.UserEntity;
import blog.AC.domain.mappers.impl.AdminReqMapperImpl;
import blog.AC.domain.mappers.impl.UserMapperImpl;
import blog.AC.repositories.AdminReqRepository;
import blog.AC.repositories.CategoryRepository;
import blog.AC.repositories.RoleRepository;
import blog.AC.repositories.UserRepository;
import blog.AC.services.StaffManagementService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.AnyKeyJavaClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleResult;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StaffManagementServiceImpl implements StaffManagementService {

    AdminReqMapperImpl adminReqMapper;
    AdminReqRepository adminReqRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;
    RoleRepository roleRepository;
    UserMapperImpl userMapper;
    @Override
    public AdminReqDto submitForm(AdminReqDto dto) {
        AdminReqEntity entity = adminReqMapper.mapToEntity(dto);
        entity.setTimeStamp(LocalDateTime.now());
        AdminReqEntity savedEntity = adminReqRepository.save(entity);

        return adminReqMapper.mapToDto(savedEntity);
    }

    @Override
    public UserDto ConfirmStaff(Long id)
    {
        Optional<AdminReqEntity> optional =  adminReqRepository.findById(id);
        if(optional.isEmpty())
            throw new RuntimeException("Request not found");
        AdminReqEntity adminReqEntity = optional.get();
        UserEntity user = userRepository.findByEmail(adminReqEntity.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        CategoryEntity category = categoryRepository.findByName(adminReqEntity.getCategory());
        RoleEntity role  = roleRepository.findByName("ROLE_STAFF");

        user.getRoles().add(role);
        user.getCategories().add(category);
        UserEntity modifiedUser = userRepository.save(user);
        return userMapper.mapTo(modifiedUser);
    }
}
