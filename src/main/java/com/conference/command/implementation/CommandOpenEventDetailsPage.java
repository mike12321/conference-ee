package com.conference.command.implementation;

import com.conference.command.Command;
import com.conference.config.Configuration;
import com.conference.controller.Direction;
import com.conference.controller.ExecutionResult;
import com.conference.controller.SessionRequestContent;
import com.conference.entity.Event;
import com.conference.entity.Topic;
import com.conference.service.EventService;
import com.conference.service.ServiceFactory;
import com.conference.service.TopicService;
import org.apache.log4j.Logger;

import java.util.List;

public class CommandOpenEventDetailsPage implements Command {

    private static final Logger log = Logger.getLogger(CommandOpenEventDetailsPage.class);

    @Override
    public ExecutionResult execute(SessionRequestContent content) {
        Configuration conf = Configuration.getInstance();
        ExecutionResult result = new ExecutionResult();

        try {
            EventService prodServ = ServiceFactory.getEventService();
            TopicService topicService = ServiceFactory.getTopicService();
            int id = Integer.parseInt(content.getRequestParameter("id")[0]);
            Event event = prodServ.findEventById(id);
            List<Topic> topics = topicService.findByEventId(id);

            result.setDirection(Direction.FORWARD);
            result.addRequestAttribute("topics", topics);
            result.addRequestAttribute("event", event);
            result.setPage(conf.getPage("eventDetails"));
        }
        catch (Exception e) {
            log.error(e);
            result.addRequestAttribute("errorMessage", conf.getErrorMessage("showInvoiceDetailsErr"));
            result.setPage(conf.getPage("error"));
        }

        return result;
    }
}
