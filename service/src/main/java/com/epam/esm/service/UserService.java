package com.epam.esm.service;

import com.epam.esm.model.User;
import com.epam.esm.service.dto.UserDto;
import com.epam.esm.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    List<UserDto> getAll() throws ServiceException;
    UserDto getById(Long id) throws ServiceException;
    List<UserDto> getUserByName(String name) throws ServiceException;
}
