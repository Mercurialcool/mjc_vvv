package com.epam.esm.service;

import com.epam.esm.dao.SearchQuery;
import com.epam.esm.model.User;
import com.epam.esm.service.dto.UserDto;
import com.epam.esm.service.exception.ServiceException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    /**
     * Finds list of users
     * @param searchQuery
     * @return list of users
     * @throws ServiceException
     */
    List<UserDto> getAll(SearchQuery searchQuery) throws ServiceException;

    /**
     * Finds a user by id
     * @param id
     * @return user entity object
     * @throws ServiceException
     */
    UserDto getById(Long id) throws ServiceException;

    /**
     * Finds user by name
     * @param name
     * @return user entity object
     * @throws ServiceException
     */
    List<UserDto> getUserByName(String name) throws ServiceException;
}
