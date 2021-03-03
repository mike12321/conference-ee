package com.conference.dao;

import com.conference.entity.Event;
import com.conference.exceptions.DataNotFoundException;

import java.util.List;

/**
 * CRUD operations interface for Event entity
 */
public interface EventDao {

    /**
     * Calculates total event number available in DB
     * @return count of events in DB
     * @throws DataNotFoundException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Integer calculateEventNumber() throws DataNotFoundException;

    /**
     * Finds all events in DB
     * @return List of all events in DB
     * @throws DataNotFoundException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Event> findAllEventsInDB() throws DataNotFoundException;

    /**
     * Finds events in DB from
     * @param first first row number
     * @param offset offset
     * @return List of all events in DB
     * @throws DataNotFoundException if connection is down, broken or unable to retrieve information for certain reasons
     */
    List<Event> findEventsInDB(Integer first, Integer offset) throws DataNotFoundException;

    /**
     * Finds event in DB by id number
     * @param id - event id number
     * @return event by id number
     * @throws DataNotFoundException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Event findEventById(Integer id) throws DataNotFoundException;

    /**
     * Finds event in DB by event title
     * @param title - event title
     * @return event by event title
     * @throws DataNotFoundException if connection is down, broken or unable to retrieve information for certain reasons
     */
    Event findEventByTitle(String title) throws DataNotFoundException;

    /**
     * Adds new event to database
     * @param event - event to add in DB
     * @return true if operation success and false if fails
     */
    boolean addEventToDB(Event event);

    /**
     * Updates existent event to database
     * @param event - event to update in DB
     * @return true if operation success and false if fails
     */
    boolean updateEventInDB(Event event);

    /**
     * Deletes existent event from database by event id number
     * @param id - event id number
     * @return true if operation success and false if fails
     */
    boolean deleteEventFromDB(Integer id);
}