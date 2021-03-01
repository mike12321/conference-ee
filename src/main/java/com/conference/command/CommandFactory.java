package com.conference.command;

import com.conference.command.implementation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static CommandFactory instance;
    private Map<String, Command> commands = new HashMap<>();

    public CommandFactory()
    {
        commands.put("openLoginPage",        new CommandOpenLoginPage());
        commands.put("login",                new CommandLogin());
        commands.put("main",                 new CommandOpenMainPage());
        commands.put("logout",               new CommandLogout());
        commands.put("changeLang",           new CommandChangeLang());
        commands.put("registerUser",         new CommandRegisterUser());
        commands.put("openRegistrationPage", new CommandOpenRegistrationPage());
        commands.put("openNewEventPage",     new CommandOpenNewEventPage());
        commands.put("addNewEvent",          new CommandAddNewEvent());
        commands.put("openEventDetailsPage", new CommandOpenEventDetailsPage());
        commands.put("updateTopic",          new CommandUpdateTopic());
        commands.put("updateEvent",          new CommandUpdateEvent());
        commands.put("deleteEvent",          new CommandDeleteEvent());
        commands.put("assignUser",           new CommandAssignUserToEvent());
    }

    public Command getCommand(HttpServletRequest request) {
        Command command = commands.get(request.getParameter("command"));

        if (command == null)
            command = new CommandMissing();

        return command;
    }

    public static CommandFactory getInstance() {
        if (instance == null)
            instance = new CommandFactory();
        return instance;
    }
}
