package com.conference.command.implementation;

import com.conference.command.Command;
import com.conference.config.Configuration;
import com.conference.controller.Direction;
import com.conference.controller.ExecutionResult;
import com.conference.controller.SessionRequestContent;
import com.conference.entity.Event;
import com.conference.exceptions.EventServiceException;
import com.conference.service.EventService;
import com.conference.service.ServiceFactory;
import com.conference.service.TopicService;

public class CommandOpenEventEditPage implements Command {

    @Override
    public ExecutionResult execute(SessionRequestContent content) {
        ExecutionResult result = new ExecutionResult();

        try {
            EventService prodServ = ServiceFactory.getEventService();
            TopicService topicService = ServiceFactory.getTopicService();
            int id = Integer.parseInt(content.getRequestParameter("id")[0]);
            Event event = prodServ.findEventById(id);

            result.setPage(Configuration.getInstance().getPage("login"));
            result.setDirection(Direction.FORWARD);
        } catch (EventServiceException e) {
            e.printStackTrace();
        }
        return result;
    }
}
