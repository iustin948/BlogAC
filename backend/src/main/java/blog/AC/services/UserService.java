package blog.AC.services;


import blog.AC.domain.dto.CredentialsDto;
import blog.AC.domain.dto.SignUpDto;
import blog.AC.domain.dto.UserDto;
import blog.AC.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity save(UserEntity userEntity);
    UserEntity findUserById(Long id);
    List<UserEntity> findAll();
    void deleteUser(Long id);
    boolean existsId(Long id);
    public UserDto login(CredentialsDto credentialsDto);
    public UserDto register(SignUpDto signUpDto);
    public Optional<UserEntity> findByEmail(String email);
    public void changeRole(String email,String role);
}