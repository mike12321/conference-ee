package com.conference.service;

import com.conference.service.implementation.*;

public class ServiceFactory {

    public ServiceFactory() {
    }

    public static UserService getUserService() {
        return new UserServiceImpl();
    }

    public static EventService getEventService() {
        return new EventServiceImpl();
    }

    public static TopicService getTopicService() {
        return new TopicServiceImpl();
    }
}
