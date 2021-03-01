package com.conference.command.implementation;

import com.conference.command.Command;
import com.conference.config.Configuration;
import com.conference.controller.Direction;
import com.conference.controller.ExecutionResult;
import com.conference.controller.SessionRequestContent;

import java.util.Locale;
import java.util.Map;

public class CommandChangeLang implements Command {

    @Override
    public ExecutionResult execute(SessionRequestContent content) {
        Configuration conf = Configuration.getInstance();
        ExecutionResult result = new ExecutionResult();
        Map<String, String[]> parameters = content.getRequestParameters();
        String lang = parameters.get("lang")[0];

        if (lang.equalsIgnoreCase("en"))
            result.addSessionAttribute("locale", new Locale("en", "EN"));
        if (lang.equalsIgnoreCase("ru"))
            result.addSessionAttribute("locale", new Locale("ru", "RU"));

        parameters.forEach((key, value) -> {
            if (!key.toLowerCase().equals("command"))
                result.addRequestParameter(key, value[0]);});

        result.setDirection(Direction.REDIRECT);
        result.addRequestParameter("command", content.getRequestParameter("returnPage")[0]);
        result.setPage(conf.getPage("base"));
        result.addParametersToPage();

        return result;
    }
}
