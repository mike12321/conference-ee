package com.conference.command.implementation;

import com.conference.command.Command;
import com.conference.config.Configuration;
import com.conference.controller.Direction;
import com.conference.controller.ExecutionResult;
import com.conference.controller.SessionRequestContent;
import com.conference.entity.UserEvent;
import com.conference.service.EventService;
import com.conference.service.ServiceFactory;
import org.apache.log4j.Logger;

public class CommandAssignUserToEvent implements Command {

    private static final Logger log = Logger.getLogger(CommandRegisterUser.class);

    @Override
    public ExecutionResult execute(SessionRequestContent content) {
        Configuration conf = Configuration.getInstance();
        ExecutionResult result = new ExecutionResult();

        try {
            int userId = Integer.parseInt(content.getRequestParameter("userId")[0]);
            int eventId = Integer.parseInt(content.getRequestParameter("eventId")[0]);
            UserEvent userEvent = new UserEvent(userId, eventId);

            result.setDirection(Direction.FORWARD);
            EventService eventService = ServiceFactory.getEventService();

            eventService.assignUserToEvent(userEvent);

            result.setPage(conf.getPage("redirect_home"));
        }
        catch (Exception uue) {
            log.error(uue);
            result.addRequestAttribute("errorMessage", conf.getErrorMessage("saveNewUserErr")); // user already assigned
            result.setPage(Configuration.getInstance().getPage("error"));
        }

        return result;
    }
}
