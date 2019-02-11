package ru.damir.server.user_storing.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.damir.server.user_storing.controllers.response.UserResponse;
import ru.damir.server.user_storing.dao.UserDao;
import ru.damir.server.user_storing.dao.entities.User;
import ru.damir.server.user_storing.service.mapping.UserDtoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserDao userDao;
    private final UserDtoMapper mapper;

    @Autowired
    public UserService(UserDao userDao, UserDtoMapper mapper) {
        this.userDao = userDao;
        this.mapper = mapper;
    }

    public List<UserResponse> getUsers(int limit, int offset) {
        return userDao.findAllUsers(limit, offset).stream().map(mapper::fromModel).collect(Collectors.toList());
    }

    public UserResponse getUser(String email, int accountNumber) {
        User response;
        if (!email.isEmpty() && accountNumber != 0) {
            response = userDao.findUserByEmailAndAccountNumber(email, accountNumber);
        } else if (!email.isEmpty()) {
            response = userDao.findUserByEmail(email);
        } else {
            response = userDao.findUserByAccountNumber(accountNumber);
        }
        return mapper.fromModel(response);
    }
}
