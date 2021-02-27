package com.conference.service;

import com.conference.entity.User;
import com.conference.exceptions.UnknownUserException;

public interface UserService2 {
    /**
     * Finds user by User name and password
     * @param username - User username
     * @param password - user password
     * @return user found by username and password
     * @throws UnknownUserException if unable to retrieve information for certain reasons
     */
    User findUser(String username, String password) throws UnknownUserException;
}
