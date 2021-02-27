package com.conference.service.implementation;

import com.conference.entity.User;
import com.conference.exceptions.UnknownUserException;
import com.conference.service.UserService2;
public class UserServiceImpl implements UserService2 {
    @Override
    public User findUser(String username, String password) throws UnknownUserException {
        return null;
    }
}
