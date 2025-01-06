package blog.AC.controllers;

import blog.AC.configs.UserAuthProvider;
import blog.AC.domain.dto.CredentialsDto;
import blog.AC.domain.dto.SignUpDto;
import blog.AC.domain.dto.UserDto;
import blog.AC.domain.entities.UserEntity;
import blog.AC.services.MyUserDetailsService;
import blog.AC.services.UserService;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;
    private final MyUserDetailsService myUserDetailsService;

    @PostMapping(path = "/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto){
        UserDto user = userService.login(credentialsDto);

        UserEntity userEntity = userService.findByEmail(user.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<String> privileges = myUserDetailsService.getPrivileges(userEntity.getRoles());

        user.setToken(userAuthProvider.createToken(user, privileges));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto)
    {
        UserDto userDto = userService.register(signUpDto);

        UserEntity userEntity = userService.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<String> privileges = myUserDetailsService.getPrivileges(userEntity.getRoles());

        userDto.setToken(userAuthProvider.createToken(userDto, privileges));

        return ResponseEntity.created(URI.create("/users" + userDto.getId())).body(userDto);
    }


}