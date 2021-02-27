package com.conference.command.implementation;

import com.conference.command.Command;
import com.conference.config.Configuration;
import com.conference.controller.Direction;
import com.conference.controller.ExecutionResult;
import com.conference.controller.SessionRequestContent;
import com.conference.entity.User;
import com.conference.exceptions.UnknownUserException;
import com.conference.service.ServiceFactory;
import org.apache.log4j.Logger;

public class CommandLogin implements Command {

    private static final Logger log = Logger.getLogger(CommandLogin.class);

    @Override
    public ExecutionResult execute(SessionRequestContent content) {
        ExecutionResult result = new ExecutionResult();
        Configuration configuration = Configuration.getInstance();
        String username = content.getRequestParameter("username")[0];
        String password = content.getRequestParameter("password")[0];

        result.setDirection(Direction.FORWARD);

        try {
            User user = ServiceFactory.getUserService().findUser(username, password);

            result.addSessionAttribute("user", user);
            result.setPage(configuration.getPage("redirect_home"));
        } catch (UnknownUserException uue) {
            log.error(uue);
            result.addRequestAttribute("errorMessage", configuration.getErrorMessage("validateUserErr"));
            result.setPage(Configuration.getInstance().getPage("error"));
        }

        return result;
    }
}
