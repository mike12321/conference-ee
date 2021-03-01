package com.conference.dao.implementation;

import com.conference.dao.GenericAbstractDao;
import com.conference.dao.UserDao;
import com.conference.dao.Mapper;
import com.conference.entity.User;
import com.conference.entity.UserRole;
import com.conference.exceptions.DataNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl extends GenericAbstractDao<User> implements UserDao {
    private Connection connection;
    private static String SQL_selectById =      "SELECT u.*, r.name as 'role' FROM users u \n" +
                                                "JOIN user_roles ur ON ur.user_id=u.id\n" +
                                                "join roles r ON ur.role_id=r.id\n" +
                                                "WHERE u.id=?;";
    private static String SQL_selectByName =    "SELECT u.*, r.name as 'role' FROM users u \n" +
                                                "JOIN user_roles ur ON ur.user_id=u.id\n" +
                                                "JOIN roles r ON ur.role_id=r.id\n" +
                                                "WHERE u.username=?;";
    private static String SQL_selectByRole = "SELECT * FROM users JOIN user_roles ON users.role_id=user_roles.role_id " +
            "WHERE user_roles.role_description=?;";
    private static String SQL_addNew =          "INSERT INTO users " +
                                                "(username, password) " +
                                                "VALUES (?,?);";
    private static String SQL_setRole =         "INSERT INTO user_roles (user_id, role_id) " +
                                                "SELECT u.id, '1' FROM users u " +
                                                "WHERE u.username = ? AND u.password = ?";
    private static String SQL_updateByName = "UPDATE project.users SET " +
            "user_name=?, user_password=?, user_phone=?, user_email=?, user_address=?, role_id=?, user_notes=? " +
            "WHERE user_name=?;";
    private static String SQL_updateById = "UPDATE project.users SET " +
            "user_name=?, user_password=?, user_phone=?, user_email=?, user_address=?, role_id=?, user_notes=? " +
            "WHERE user_id=?;";
    private static String SQL_deleteUser = "DELETE FROM project.users WHERE user_name=?;";

    private Mapper<User, PreparedStatement> mapperToDB = (User user, PreparedStatement preparedStatement) -> {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
    };

    private Mapper<ResultSet, User> mapperFromDB = (ResultSet resultSet, User user) -> {
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setUserRole(UserRole.valueOf(resultSet.getString("role")));
    };

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
        super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
    }

//    @Override
//    public List<User> findUserByRole(UserRole role) throws DataNotFoundException {
//        return findAsListBy(connection, User.class, SQL_selectByRole, role.toString());
//    }

    @Override
    public User findUserById(Integer id) throws DataNotFoundException {
        return findBy(connection, User.class, SQL_selectById, id);
    }

    @Override
    public User findUserByName(String name) throws DataNotFoundException {
        return findBy(connection, User.class, SQL_selectByName, name);
    }

    @Override
    public boolean addUserToDB(User user) {
        return addToDB(connection, user, SQL_addNew);
    }

    @Override
    public boolean setUserRole(User user) {
        return addToDB(connection, user, SQL_setRole);
    }
}