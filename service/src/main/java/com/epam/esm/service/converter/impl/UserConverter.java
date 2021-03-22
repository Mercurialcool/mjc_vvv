package com.epam.esm.service.converter.impl;

import com.epam.esm.service.converter.Converter;
import com.epam.esm.service.dto.UserDto;
import com.epam.esm.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter implements Converter<User, UserDto> {

    @Autowired
    private ModelMapper dtoRouter;

    @Override
    public User convertDtoToObject(UserDto dto) {
        User user = new User();
        dtoRouter.map(dto, user);
        return user;
    }

    @Override
    public UserDto convertObjectToDto(User entity) {
        UserDto userDto = new UserDto();
        dtoRouter.map(entity, userDto);
        return userDto;
    }

    @Override
    public List<User> convertDtoToObjectList(List<UserDto> dtoList) {
        return dtoList.stream().map(this::convertDtoToObject).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> convertObjectListToDto(List<User> objectList) {
        return objectList.stream().map(this::convertObjectToDto).collect(Collectors.toList());
    }
}
