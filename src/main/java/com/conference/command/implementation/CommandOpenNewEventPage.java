package com.conference.command.implementation;

import com.conference.command.Command;
import com.conference.config.Configuration;
import com.conference.controller.Direction;
import com.conference.controller.ExecutionResult;
import com.conference.controller.SessionRequestContent;

public class CommandOpenNewEventPage implements Command {

    @Override
    public ExecutionResult execute(SessionRequestContent content) {
        ExecutionResult result = new ExecutionResult();
        result.setPage(Configuration.getInstance().getPage("newProduct"));
        result.setDirection(Direction.FORWARD);
        return result;
    }
}
