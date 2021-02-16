package com.epam.esm.controller;


import com.epam.esm.model.User;
import com.epam.esm.service.UserService;
import com.epam.esm.service.dto.UserDto;
import com.epam.esm.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> getAll() {
        try {
            return userService.getAll();
        } catch (ServiceException e) {
            throw e;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public UserDto getUser(@PathVariable Long id) {
        try {
            return userService.getById(id);
        } catch (ServiceException e) {
            throw e;
        }
    }

}
