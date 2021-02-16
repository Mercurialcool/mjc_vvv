package com.epam.esm.service.impl;

import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.User;
import com.epam.esm.service.UserService;
import com.epam.esm.service.converter.Converter;
import com.epam.esm.service.dto.UserDto;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.exception.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private CertificateDao certificateDao;
    private TagDao tagDao;
    private UserDao userDao;
    private Converter<User, UserDto> userConverter;

    @Autowired
    public UserServiceImpl(CertificateDao certificateDao,
                           TagDao tagDao,
                           UserDao userDao,
                           Converter<User, UserDto> userConverter) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
        this.userDao = userDao;
        this.userConverter = userConverter;
    }

    @Override
    public List<UserDto> getUserByName(String name) throws ServiceException, DaoException {
        List<User> user = userDao.getUserByName(name);
        if(user == null) {
            throw new UserNotFoundException("User not found");
        }
        return userConverter.objectDtoList(userDao.getUserByName(name));
    }

    @Override
    public UserDto getById(Long id) throws ServiceException {
        User user = userDao.getById(id);
        if(user == null) {
            throw new UserNotFoundException("User not found");
        }
        return userConverter.objectDto(userDao.getById(id));
    }

    @Override
    public List<UserDto> getAll() throws ServiceException, DaoException {
        return userConverter.objectDtoList(userDao.getAll());
    }

}