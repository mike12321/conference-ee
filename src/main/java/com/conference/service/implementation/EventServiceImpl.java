package com.conference.service.implementation;

import com.conference.dao.DaoFactory;
import com.conference.dao.DataBaseSelector;
import com.conference.dao.EventDao;
import com.conference.dao.UserEventDao;
import com.conference.entity.Event;
import com.conference.entity.UserEvent;
import com.conference.exceptions.*;
import com.conference.service.EventService;
import org.apache.log4j.Logger;

import java.util.List;

public class EventServiceImpl implements EventService {

    private static final Logger log = Logger.getLogger(EventServiceImpl.class);
    private static final DataBaseSelector source = DataBaseSelector.MY_SQL;

    private static DaoFactory daoFactory;
    private static EventDao eventDao;
    private static UserEventDao userEventDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            eventDao = daoFactory.getEventDao();
        } catch (IncorrectPropertyException | DataBaseConnectionException | DataBaseNotSupportedException ex) {
            log.error(ex);
        }
    }

    /** User validation method to check user before storing in DB */

    public boolean validateEventData(Event event) {
        return !(event.getTitle() == null || event.getTitle().isEmpty()
                || event.getDateTime() == null);
    }

    public boolean validateUserEventData(UserEvent userEvent) {
        return !(userEvent.getEventId() <= 0 || userEvent.getUserId() <= 0);
    }

    /** Data access and storing methods */

    @Override
    public Integer calculateEventsNumber() {
        Integer result = 0;
        try {
            daoFactory.beginTransaction();
            eventDao = daoFactory.getEventDao();
            result = eventDao.calculateEventNumber();
            daoFactory.commitTransaction();
        } catch (DataBaseConnectionException | DataNotFoundException ex) {
            log.error(ex);
        }
        return result;
    }

    @Override
    public List<Event> findAllEvents() throws EventServiceException {
        List<Event> events;
        try {
            daoFactory.open();
            eventDao = daoFactory.getEventDao();
            events = eventDao.findAllEventsInDB();
            daoFactory.close();
        } catch (DataBaseConnectionException | DataNotFoundException ex) {
            log.error(ex);
            throw new EventServiceException();
        }
        return events;
    }

    @Override
    public List<Event> findEvents(Integer from, Integer offset) throws EventServiceException {
        List<Event> events;
        try {
            daoFactory.open();
            eventDao = daoFactory.getEventDao();
            events = eventDao.findEventsInDB(from, offset);
            daoFactory.close();
        } catch (DataBaseConnectionException | DataNotFoundException ex) {
            log.error(ex);
            throw new EventServiceException();
        }
        return events;
    }

    @Override
    public Event findEventById(int id) throws EventServiceException {
        Event event;
        try {
            daoFactory.open();
            eventDao = daoFactory.getEventDao();
            event = eventDao.findEventById(id);
            daoFactory.close();
        } catch (DataBaseConnectionException | DataNotFoundException ex) {
            log.error(ex);
            throw new EventServiceException();
        }
        return event;
    }

    @Override
    public Event findEventByTitle(String title) throws EventServiceException {
        Event event = new Event();
        try {
            daoFactory.open();
            eventDao = daoFactory.getEventDao();
            event = eventDao.findEventByTitle(title);
            daoFactory.close();
        } catch (DataBaseConnectionException | DataNotFoundException ex) {
            log.error(ex);
            throw new EventServiceException();
        }
        return event;
    }

    @Override
    public synchronized boolean addEvent(Event event) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            eventDao = daoFactory.getEventDao();
            result = validateEventData(event) && eventDao.addEventToDB(event);
            daoFactory.commitTransaction();
        } catch (DataBaseConnectionException ex) {
            log.error(ex);
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean updateEvent(Event event) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            eventDao = daoFactory.getEventDao();
            result = validateEventData(event) && eventDao.updateEventInDB(event);
            daoFactory.commitTransaction();
        } catch (DataBaseConnectionException ex) {
            log.error(ex);
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean deleteEvent(int id) {
        boolean result;

        try {
            daoFactory.beginTransaction();
            eventDao = daoFactory.getEventDao();
            result = eventDao.deleteEventFromDB(id);
            daoFactory.commitTransaction();
        } catch (DataBaseConnectionException ex) {
            log.error(ex);
            return false;
        }

        return result;
    }

    @Override
    public boolean assignUserToEvent(UserEvent userEvent) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            userEventDao = daoFactory.getUserEventDao();
            result = validateUserEventData(userEvent) && userEventDao.assignUserToEvent(userEvent);
            daoFactory.commitTransaction();
        } catch (DataBaseConnectionException ex) {
            log.error(ex);
            return false;
        }
        return result;
    }
}