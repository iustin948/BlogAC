package blog.AC.controllers;

import blog.AC.domain.dto.AdminReqDto;
import blog.AC.domain.dto.UserDto;
import blog.AC.domain.entities.AdminReqEntity;
import blog.AC.domain.entities.CategoryEntity;
import blog.AC.domain.entities.RoleEntity;
import blog.AC.domain.entities.UserEntity;
import blog.AC.repositories.CategoryRepository;
import blog.AC.services.StaffManagementService;
import blog.AC.services.UserService;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/staff")
@AllArgsConstructor
public class StaffManagementController {

    UserService userService;
    CategoryRepository categoryRepository;
    StaffManagementService staffManagementService;

    @PostMapping("/form")
    public ResponseEntity<AdminReqDto> submitRequest(@RequestBody AdminReqDto dto)
    {
        AdminReqDto form = staffManagementService.submitForm(dto);
        return new ResponseEntity<>(form, HttpStatus.OK);
    }


    @PatchMapping("/accept")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> acceptRequest(@RequestBody AdminReqDto dto)
    {
        UserDto user = staffManagementService.ConfirmStaff(dto);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


}
