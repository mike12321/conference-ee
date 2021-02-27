package com.conference.command.implementation;

import com.conference.command.Command;
import com.conference.config.Configuration;
import com.conference.controller.Direction;
import com.conference.controller.ExecutionResult;
import com.conference.controller.SessionRequestContent;
import com.conference.domain.UserCart;
import com.conference.entity.User;
import com.conference.exceptions.UnknownUserException;
import com.conference.service.IUserServ;
import com.conference.service.ServiceFactory;
import org.apache.log4j.Logger;

import java.util.Locale;

public class CommandMissing implements Command {

    private static final Logger log = Logger.getLogger(CommandLogout.class);

    @Override
    public ExecutionResult execute(SessionRequestContent content) {
        Configuration conf = Configuration.getInstance();
        ExecutionResult result = new ExecutionResult();
        result.setDirection(Direction.FORWARD);
        try {
            IUserServ userServ = ServiceFactory.getUserService();
            User guest = userServ.findUser("Guest", "1");

            if (!content.checkSessionAttribute("user"))
                result.addSessionAttribute("user", guest);
            if (!content.checkSessionAttribute("local"))
                result.addSessionAttribute("locale", new Locale("en", "EN"));

            result.addRequestAttribute("pageNum", 1);
            result.setPage(conf.getPage("redirect_home"));
        } catch (UnknownUserException uue) {
            log.error(uue);
            result.addRequestAttribute("errorMessage", conf.getErrorMessage("generalErr"));
            result.setPage(conf.getPage("error"));
        }
        return result;
    }
}
