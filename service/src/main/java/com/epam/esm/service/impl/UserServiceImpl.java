package com.epam.esm.service.impl;

import com.epam.esm.dao.*;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.User;
import com.epam.esm.service.UserService;
import com.epam.esm.service.converter.Converter;
import com.epam.esm.service.dto.UserDto;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.exception.user.UserNotFoundException;
import com.epam.esm.service.utils.SearchQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private CertificateDao certificateDao;
    private TagDao tagDao;
    private UserDao userDao;
    private Converter<User, UserDto> userConverter;
    private CustomUserRepository customUserRepository;

    @Autowired
    public UserServiceImpl(CertificateDao certificateDao,
                           TagDao tagDao,
                           UserDao userDao,
                           Converter<User, UserDto> userConverter,
                           CustomUserRepository customUserRepository) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
        this.userDao = userDao;
        this.userConverter = userConverter;
        this.customUserRepository = customUserRepository;
    }

    @Override
    public List<UserDto> getUserByName(String name) throws ServiceException, DaoException {
        List<User> user = userDao.getUserByName(name);
        if(user == null) {
            throw new UserNotFoundException("User not found");
        }
        return userConverter.convertObjectListToDto(userDao.getUserByName(name));
    }

    @Override
    public UserDto getById(Long id) throws ServiceException {
        User user =
        Optional.ofNullable(userDao.getById(id)).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userConverter.convertObjectToDto(userDao.getById(id));
    }

    @Override
    public List<UserDto> getAll(SearchQuery searchQuery) throws ServiceException, DaoException {
        return userConverter.convertObjectListToDto(customUserRepository.findAll(SearchQueryUtil.getPage(searchQuery)).toList());
    }
}
