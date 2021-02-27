package com.conference.command;

import com.conference.controller.ExecutionResult;
import com.conference.controller.SessionRequestContent;

public interface Command {
    /**
     * @param content - object that contains session and request attributes and parameters
     * @return object that contains new request and session attributes and parameters as the result of command execution
     */
    ExecutionResult execute(SessionRequestContent content);

}
