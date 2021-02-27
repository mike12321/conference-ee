package com.conference.command.implementation;

import com.conference.command.Command;
import com.conference.config.Configuration;
import com.conference.controller.Direction;
import com.conference.controller.ExecutionResult;
import com.conference.controller.SessionRequestContent;
import com.conference.entity.User;
import com.conference.service.IUserServ;
import com.conference.service.ServiceFactory;
import org.apache.log4j.Logger;

public class CommandRegisterUser implements Command {

    private static final Logger log = Logger.getLogger(CommandRegisterUser.class);

    @Override
    public ExecutionResult execute(SessionRequestContent content) {
        Configuration conf = Configuration.getInstance();
        ExecutionResult result = new ExecutionResult();

        try {
            String username = content.getRequestParameter("username")[0];
            String password = content.getRequestParameter("password")[0];
            User user = new User(username, password);

            result.setDirection(Direction.FORWARD);
            IUserServ userServ = ServiceFactory.getUserService();

            if (userServ.addUser(user)) {
                result.addSessionAttribute("user", userServ.findUser(username, password));
                result.setPage(conf.getPage("redirect_home"));
            }
            else {
                result.addRequestAttribute("errorMessage", conf.getErrorMessage("saveNewUserErr"));
                result.setPage(Configuration.getInstance().getPage("error"));
            }
        }
        catch (Exception uue) {
            log.error(uue);
            result.addRequestAttribute("errorMessage", conf.getErrorMessage("saveNewUserErr"));
            result.setPage(Configuration.getInstance().getPage("error"));
        }

        return result;
    }
}
