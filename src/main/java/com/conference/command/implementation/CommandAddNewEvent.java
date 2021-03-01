package com.conference.command.implementation;

import com.conference.command.Command;
import com.conference.command.Security;
import com.conference.config.Configuration;
import com.conference.controller.Direction;
import com.conference.controller.ExecutionResult;
import com.conference.controller.SessionRequestContent;
import com.conference.entity.Event;
import com.conference.entity.UserRole;
import com.conference.service.EventService;
import com.conference.service.ServiceFactory;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommandAddNewEvent implements Command {

    private static final Logger log = Logger.getLogger(CommandAddNewEvent.class);

    @Override
    public ExecutionResult execute(SessionRequestContent content) {
        Configuration conf = Configuration.getInstance();
        ExecutionResult result = new ExecutionResult();
        EventService serv = ServiceFactory.getEventService();
        result.setDirection(Direction.FORWARD);
        try {
            if (!Security.checkSecurity(content, UserRole.ROLE_ADMIN)) {
                result.setPage(conf.getPage("securityError"));

                return result;
            }

            Event event = new Event();
            event.setTitle(content.getRequestParameter("title")[0]);
            event.setDateTime(LocalDateTime.parse(content.getRequestParameter("date")[0], DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a")));

            if (serv.addProduct(event)) {
                result.setPage(conf.getPage("redirect_manageProducts"));
            }
            else {
                result.addRequestAttribute("errorMessage", conf.getErrorMessage("saveNewProductErr"));
                result.setPage(Configuration.getInstance().getPage("error"));
            }
        } catch (Exception exception) {
            log.error(exception);
            result.addRequestAttribute("errorMessage", conf.getErrorMessage("saveNewProductErr"));
            result.setPage(Configuration.getInstance().getPage("error"));
        }

        return result;
    }
}
