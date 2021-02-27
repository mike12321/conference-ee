package com.conference.command.implementation;

import com.conference.command.Command;
import com.conference.config.Configuration;
import com.conference.controller.Direction;
import com.conference.controller.ExecutionResult;
import com.conference.controller.SessionRequestContent;
import org.apache.log4j.Logger;

public class CommandLogout implements Command {

    private static final Logger log = Logger.getLogger(CommandLogout.class);

    @Override
    public ExecutionResult execute(SessionRequestContent content) {
        Configuration conf = Configuration.getInstance();
        ExecutionResult result = new ExecutionResult();

        result.setDirection(Direction.REDIRECT);

        try {
            result.invalidateSession();
            result.setPage("/project");
        }
        catch (Exception uue) {
            log.error(uue);
            result.addRequestAttribute("errorMessage", conf.getErrorMessage("generalErr"));
            result.setPage(conf.getPage("error"));
        }

        return result;
    }
}
