package blog.AC.services.impl;

import blog.AC.domain.dto.CredentialsDto;
import blog.AC.domain.dto.SignUpDto;
import blog.AC.domain.dto.UserDto;
import blog.AC.domain.entities.UserEntity;
import blog.AC.exceptions.AppException;
import blog.AC.domain.mappers.impl.UserMapperImpl;
import blog.AC.repositories.RoleRepository;
import blog.AC.repositories.UserRepository;
import blog.AC.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final UserRepository  userRepository;
    final PasswordEncoder passwordEncoder;
    final UserMapperImpl userMapper;
    final RoleRepository roleRepository;

    @Override
    public UserDto login(CredentialsDto credentialsDto)
    {
        UserEntity user = userRepository.findByEmail(credentialsDto.email())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword()))
            return userMapper.mapTo(user);
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UserDto register(SignUpDto signUpDto) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(signUpDto.email());

        if(userEntity.isPresent())
        {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = userMapper.mapFrom(userMapper.mapSignUpToEntity(signUpDto));
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
        user.setRegistrationDate(new Date());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        UserEntity savedUser = userRepository.save(user);

        return userMapper.mapTo(savedUser);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void changeRole(String email, String role) {
        UserEntity user = userRepository.findByEmail(email).get();
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_STAFF")));
        userRepository.save(user);
    }


    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findUserById(Long id) {
        Optional<UserEntity> byId = userRepository.findById(id);
        return byId.orElse(null);
    }
    @Transactional
    @Override
    public List<UserEntity> findAll() {
        return new ArrayList<>(userRepository
                .findAll());
    }



    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsId(Long id) {
        return userRepository.existsById(id);
    }

}