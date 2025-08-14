package blog.AC.services;

import blog.AC.domain.dto.AdminReqDto;
import blog.AC.domain.dto.UserDto;
import org.springframework.stereotype.Service;


public interface StaffManagementService {

    public AdminReqDto submitForm(AdminReqDto dto);
    public UserDto ConfirmStaff(Long id);

}
