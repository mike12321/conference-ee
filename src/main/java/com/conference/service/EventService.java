package com.conference.service;

import com.conference.entity.Event;
import com.conference.entity.UserEvent;
import com.conference.exceptions.EventServiceException;

import java.util.List;

public interface EventService {

    /**
     * Calculates total number of events records in DB
     * @return total number of events stored in DB
     */
    Integer calculateEventsNumber();

    /**
     * Finds all Products in DB
     * @return List of all events
     * @throws EventServiceException if unable to retrieve information for certain reasons
     */
    List<Event> findAllEvents() throws EventServiceException;

    /**
     * Finds first @param=offset events starts from @param=from row
     * @param from - first table line number
     * @param offset - number of records to find
     * @return List of events
     * @throws EventServiceException if unable to retrieve information for certain reasons
     */
    List<Event> findEvents(Integer from, Integer offset) throws EventServiceException;

    /**
     * Finds a event by event code
     * @param id - event id
     * @return
     * @throws EventServiceException if unable to retrieve information for certain reasons
     */
    Event findEventById(int id) throws EventServiceException;

    /**
     * Finds a event by event code
     * @param title - event title
     * @return
     * @throws EventServiceException if unable to retrieve information for certain reasons
     */
    Event findEventByTitle(String title) throws EventServiceException;

    /**
     * Adds a new event in DB
     * @param event Event to add
     * @return true if operation success and false if fails
     */
    boolean addEvent(Event event);

    /**
     * Updates an existent event in DB
     * @param event - Event to update
     * @return true if operation success and false if fails
     */

    boolean updateEvent(Event event);

    /**
     * Deletes event from DB
     * @param event - Event to delete
     * @return true if operation success and false if fails
     */
//    boolean deleteProduct(Event event);

    /**
     * Deletes event from DB by code
     * @param id event id
     * @return true if operation success and false if fails
     */

    boolean deleteEvent(int id);

    boolean assignUserToEvent(UserEvent userEvent);
}
