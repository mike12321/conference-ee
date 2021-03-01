package com.conference.dao.implementation;

import com.conference.dao.GenericAbstractDao;
import com.conference.dao.Mapper;
import com.conference.dao.UserEventDao;
import com.conference.entity.UserEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class UserEventImpl extends GenericAbstractDao<UserEvent> implements UserEventDao {
    private Connection connection;

    private static String SQL_assignUserToEvent =   "INSERT INTO user_event (user_id, event_id) " +
                                                    "VALUES (?,?);";

    private Mapper<UserEvent, PreparedStatement> mapperToDB = (UserEvent userEvent, PreparedStatement preparedStatement) -> {
        preparedStatement.setInt(1, userEvent.getUserId());
        preparedStatement.setInt(2, userEvent.getEventId());
    };

    private Mapper<ResultSet, UserEvent> mapperFromDB = (ResultSet resultSet, UserEvent userEvent) -> {
        userEvent.setUserId(resultSet.getInt("user_id"));
        userEvent.setEventId(resultSet.getInt("event_id"));
        userEvent.setVisited(resultSet.getBoolean("visited"));
    };

    public UserEventImpl(Connection connection) {
        super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
        this.connection = connection;
    }

    @Override
    public boolean assignUserToEvent(UserEvent userEvent) {
        return addToDB(connection, userEvent, SQL_assignUserToEvent);
    }
}
