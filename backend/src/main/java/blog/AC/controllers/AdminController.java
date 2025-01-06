package blog.AC.controllers;

import blog.AC.domain.dto.AdminReqDto;
import blog.AC.domain.entities.UserEntity;
import blog.AC.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    UserService userService;

    @PostMapping
    public ResponseEntity<UserEntity> acceptReq(AdminReqDto dto)
    {
            userService.changeRole(dto.getEmail(),"ADMIN");
            return new ResponseEntity<>(userService.findByEmail(dto.getEmail()).get(), HttpStatus.OK);
    }

    @GetMapping
    public String ret()
    {
        return "Hello";
    }

}
