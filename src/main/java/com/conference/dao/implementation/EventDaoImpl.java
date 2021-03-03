package com.conference.dao.implementation;

import com.conference.dao.GenericAbstractDao;
import com.conference.dao.Mapper;
import com.conference.entity.Event;
import com.conference.dao.EventDao;
import com.conference.exceptions.DataNotFoundException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class EventDaoImpl extends GenericAbstractDao<Event> implements EventDao {

    private Connection connection;
    private static String SQL_select_base =         "SELECT * FROM event ";
    private static String SQL_selectAll =           "SELECT * FROM event;";
    private static String SQL_selectById =          "SELECT * FROM event WHERE id=?;";
    private static String SQL_selectByTitle =       "SELECT * FROM event WHERE title=?;";
    private static String SQL_addNewEvent =         "INSERT INTO event (title, date_time) " +
                                                    "VALUES (?,?);";
    private static String SQL_updateProductEvent =  "UPDATE event SET title=?, date_time=? " +
                                                    "WHERE id=?;";
    private static String SQL_deleteEventById =     "DELETE FROM event WHERE id=?;";

    /** Private methods for serving methods implementing DAO interface */

    private Mapper<Event, PreparedStatement> mapperToDB = (Event event, PreparedStatement preparedStatement) -> {
        preparedStatement.setString(1, event.getTitle());
        preparedStatement.setDate(2, Date.valueOf(event.getDateTime().toLocalDate()));
    };

    private Mapper<ResultSet, Event> mapperFromDB = (ResultSet resultSet, Event event) -> {
        event.setId(resultSet.getInt("id"));
        event.setTitle(resultSet.getString("title"));
        event.setDateTime(resultSet.getTimestamp("date_time").toLocalDateTime());
    };

    public EventDaoImpl(Connection connection) {
        super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
        this.connection = connection;
    }

    @Override
    public Integer calculateEventNumber() throws DataNotFoundException {
        return calculateRowCounts(connection, "event");
    }

    @Override
    public List<Event> findAllEventsInDB() throws DataNotFoundException {
        List<Event> events = findAll(connection, Event.class, SQL_selectAll);
        return events;
    }

    @Override
    public List<Event> findEventsInDB(Integer first, Integer offset) throws DataNotFoundException {
        return findAllFromTo(connection, Event.class, first, offset, SQL_select_base);
    }

    @Override
    public Event findEventById(Integer id) throws DataNotFoundException {
        return findBy(connection, Event.class, SQL_selectById, id);
    }

    @Override
    public Event findEventByTitle(String title) throws DataNotFoundException {
        return findBy(connection, Event.class, SQL_selectById, title);
    }

    @Override
    public boolean addEventToDB(Event event) {
        return addToDB(connection, event, SQL_addNewEvent);
    }

    @Override
    public boolean updateEventInDB(Event event) {
        Integer id = event.getId();
        return updateInDB(connection, event, SQL_updateProductEvent, 3, id);
    }

    @Override
    public boolean deleteEventFromDB(Integer id) {
        return deleteFromDB(connection, SQL_deleteEventById, id);
    }
}