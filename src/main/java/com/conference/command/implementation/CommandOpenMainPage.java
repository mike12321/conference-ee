package com.conference.command.implementation;

import com.conference.command.Command;
import com.conference.config.Configuration;
import com.conference.controller.Direction;
import com.conference.controller.ExecutionResult;
import com.conference.controller.SessionRequestContent;
import com.conference.entity.Event;
import com.conference.service.EventService;
import com.conference.service.ServiceFactory;
import org.apache.log4j.Logger;

import java.util.List;

public class CommandOpenMainPage implements Command {

    private static final Logger log = Logger.getLogger(CommandOpenMainPage.class);
    private static final int EVENTS_PER_PAGE = 3;

    @Override
    public ExecutionResult execute(SessionRequestContent content) {
        Configuration conf = Configuration.getInstance();
        ExecutionResult result = new ExecutionResult();
        result.setDirection(Direction.FORWARD);
        try {
            EventService productServ = ServiceFactory.getEventService();
            int totalPages = (int) Math.floor(productServ.calculateProductsNumber() / EVENTS_PER_PAGE) + 1;
            int pageNum = content.checkRequestParameter("pageNum") ?
                    Integer.parseInt(content.getRequestParameter("pageNum")[0]) : 1;
            List<Event> events = productServ.findProducts((pageNum - 1) * EVENTS_PER_PAGE, EVENTS_PER_PAGE);
            result.addRequestAttribute("events", events);
            result.addRequestAttribute("totalPages", totalPages);
            result.addRequestAttribute("pageNum", pageNum);
            result.setPage(conf.getPage("main"));
        }
        catch (Exception e) {
            log.error(e);
            result.addRequestAttribute("errorMessage", conf.getErrorMessage("showMainPageErr"));
            result.setPage(conf.getPage("error"));
        }

        return result;
    }
}
