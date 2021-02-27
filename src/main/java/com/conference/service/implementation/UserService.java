package com.conference.service.implementation;

import com.conference.exceptions.*;
import com.conference.service.Button;
import com.conference.service.IUserServ;
import com.conference.dao.DaoFactory;
import com.conference.dao.DataBaseSelector;
import com.conference.dao.IUserDao;
import com.conference.entity.User;
import org.apache.log4j.Logger;

public class UserService implements IUserServ {

    private static final DataBaseSelector source = DataBaseSelector.MY_SQL;
    private static final Logger log = Logger.getLogger(UserService.class);
    private static DaoFactory daoFactory;
    private static IUserDao userDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            userDao = daoFactory.getUserDao();
        } catch (IncorrectPropertyException | DataBaseConnectionException | DataBaseNotSupportedException ex) {
            log.error(ex);
        }
    }

    @Button
    @Override
    public User findUser(String name, String password) throws UnknownUserException {
        User user;
        try {
            daoFactory.open();
            userDao = daoFactory.getUserDao();
            user = userDao.findUserByName(name);
            daoFactory.close();

            if (!user.getPassword().equals(password))
                throw new UnknownUserException();

            return user;
        } catch (DataBaseConnectionException | DataNotFoundException ex) {
            log.error(ex);
            throw new UnknownUserException();
        }
    }

    /** User validation method to check user before storing in DB */

    private boolean validateUserData(User user) {
        return !(user.getName() == null
                || user.getName().isEmpty()
                || user.getPassword() == null
                || user.getPassword().isEmpty());
    }

    @Button
    @Override
    public synchronized boolean addUser(User user) {
        boolean result;
        try {
            daoFactory.open();
            userDao = daoFactory.getUserDao();
            result = validateUserData(user) && userDao.addUserToDB(user) && userDao.setUserRole(user);
            daoFactory.close();
        } catch (DataBaseConnectionException ex) {
            log.error(ex);
            return false;
        }

        return result;
    }
}