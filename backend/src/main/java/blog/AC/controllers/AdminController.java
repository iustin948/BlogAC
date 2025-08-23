package blog.AC.controllers;

import blog.AC.domain.dto.AdminReqDto;
import blog.AC.domain.dto.UserDto;
import blog.AC.domain.entities.UserEntity;
import blog.AC.services.UserService;
import blog.AC.services.AdminReqService;
import blog.AC.services.StaffManagementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    UserService userService;
    AdminReqService adminReqService;
    StaffManagementService staffManagementService;

    @PostMapping("/approve/{id}")
    public ResponseEntity<UserDto> acceptRequest(@PathVariable Long id)
    {
        UserDto user = staffManagementService.ConfirmStaff(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/requests")
    public ResponseEntity<List<AdminReqDto>> getAllRequests() {
        return new ResponseEntity<>(adminReqService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/reject/{id}")
    public ResponseEntity<Void> rejectRequest(@PathVariable Long id) {
        adminReqService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
