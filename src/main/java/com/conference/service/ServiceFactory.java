package com.conference.service;

import com.conference.service.implementation.*;

public class ServiceFactory {

    public ServiceFactory() {
    }

    public static IUserServ getUserService() {
        return new UserService();
    }

    public static IProductServ getProductService() {
        return new ProductService();
    }

    public static TopicService getTopicService() {
        return new TopicServiceImpl();
    }
}
