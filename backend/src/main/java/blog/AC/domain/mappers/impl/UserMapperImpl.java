package blog.AC.domain.mappers.impl;

import blog.AC.domain.dto.SignUpDto;
import blog.AC.domain.dto.UserDto;
import blog.AC.domain.entities.UserEntity;
import blog.AC.domain.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserMapperImpl implements Mapper<UserEntity, UserDto> {
    private final ModelMapper modelMapper;
    public UserMapperImpl(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }
    @Override
    public UserDto mapTo(UserEntity userEntity) {
        return modelMapper.map(userEntity,UserDto.class);
    }

    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return modelMapper.map(userDto,UserEntity.class);
    }

    public UserDto mapSignUpToEntity(SignUpDto signUpDto){
        UserDto userDto = new UserDto();
        userDto.setFirstName(signUpDto.firstName());
        userDto.setEmail(signUpDto.email());
        userDto.setLastName(signUpDto.lastName());
        userDto.setPassword(Arrays.toString(signUpDto.password()));
        return userDto;
    }
}