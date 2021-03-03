package com.conference.command.implementation;

import com.conference.command.Command;
import com.conference.config.Configuration;
import com.conference.controller.Direction;
import com.conference.controller.ExecutionResult;
import com.conference.controller.SessionRequestContent;
import com.conference.entity.Event;
import com.conference.service.EventService;
import com.conference.service.ServiceFactory;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;

public class CommandUpdateEvent implements Command {

    private static final Logger log = Logger.getLogger(CommandUpdateTopic.class);

    @Override
    public ExecutionResult execute(SessionRequestContent content) {
        Configuration configuration = Configuration.getInstance();
        ExecutionResult result = new ExecutionResult();

        try {
            EventService eventService = ServiceFactory.getEventService();

            Event event = new Event();

            event.setId(Integer.parseInt(content.getRequestParameter("id")[0]));
            event.setDateTime(LocalDateTime.parse(content.getRequestParameter("date")[0]));
            event.setTitle(content.getRequestParameter("title")[0]);

            eventService.updateEvent(event);
            result.setDirection(Direction.FORWARD);
            result.setPage(configuration.getPage("redirect_home"));
        } catch (Exception exception) {
            log.error(exception);
            result.setPage(Configuration.getInstance().getPage("error"));
        }

        return result;
    }
}
