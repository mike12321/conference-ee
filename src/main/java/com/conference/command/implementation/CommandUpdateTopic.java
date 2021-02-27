package com.conference.command.implementation;

import com.conference.command.Command;
import com.conference.config.Configuration;
import com.conference.controller.Direction;
import com.conference.controller.ExecutionResult;
import com.conference.controller.SessionRequestContent;
import com.conference.entity.Topic;
import com.conference.service.ServiceFactory;
import com.conference.service.TopicService;
import org.apache.log4j.Logger;

public class CommandUpdateTopic implements Command {

    private static final Logger log = Logger.getLogger(CommandUpdateTopic.class);

    @Override
    public ExecutionResult execute(SessionRequestContent content) {
        Configuration configuration = Configuration.getInstance();
        ExecutionResult result = new ExecutionResult();

        try {
            TopicService topicService = ServiceFactory.getTopicService();
            Topic topic = new Topic();

            topic.setId(Integer.parseInt(content.getRequestParameter("id")[0]));
            topic.setEventId(Integer.parseInt(content.getRequestParameter("eventId")[0]));
            topic.setSpeakerId(Integer.parseInt(content.getRequestParameter("speakerId")[0]));
            topic.setApproved(false);
            topic.setTitle(content.getRequestParameter("title")[0]);

            topicService.updateTopic(topic);
            result.setDirection(Direction.FORWARD);
            result.setPage(configuration.getPage("redirect_home"));
        }
        catch (Exception exception) {
            log.error(exception);
            result.setPage(Configuration.getInstance().getPage("error"));
        }

        return result;
    }
}
