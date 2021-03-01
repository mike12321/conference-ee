package com.conference.command.implementation;

import com.conference.command.Command;
import com.conference.config.Configuration;
import com.conference.controller.Direction;
import com.conference.controller.ExecutionResult;
import com.conference.controller.SessionRequestContent;
import com.conference.service.EventService;
import com.conference.service.implementation.EventServiceImpl;
import org.apache.log4j.Logger;

public class CommandDeleteEvent implements Command {

    private static final Logger log = Logger.getLogger(CommandDeleteEvent.class);

    @Override
    public ExecutionResult execute(SessionRequestContent content) {
        ExecutionResult result = new ExecutionResult();
        Configuration configuration = Configuration.getInstance();

        try {
            EventService productServ = new EventServiceImpl();
            int id = Integer.parseInt(content.getRequestParameter("id")[0]);

            productServ.deleteProduct(id);
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
