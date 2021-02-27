package com.conference.command.implementation;

import com.conference.command.Command;
import com.conference.config.Configuration;
import com.conference.controller.Direction;
import com.conference.controller.ExecutionResult;
import com.conference.controller.SessionRequestContent;
import com.conference.domain.Product;
import com.conference.service.IProductServ;
import com.conference.service.ServiceFactory;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommandUpdateEvent implements Command {

    private static final Logger log = Logger.getLogger(CommandUpdateTopic.class);

    @Override
    public ExecutionResult execute(SessionRequestContent content) {
        Configuration configuration = Configuration.getInstance();
        ExecutionResult result = new ExecutionResult();

        try {
            IProductServ productServ = ServiceFactory.getProductService();
            Product product = new Product();

            product.setId(Integer.parseInt(content.getRequestParameter("id")[0]));
            product.setDateTime(LocalDateTime.parse(content.getRequestParameter("date")[0]));
            product.setTitle(content.getRequestParameter("title")[0]);

            productServ.updateProduct(product);
            result.setDirection(Direction.FORWARD);
            result.setPage(configuration.getPage("redirect_home"));
        } catch (Exception exception) {
            log.error(exception);
            result.setPage(Configuration.getInstance().getPage("error"));
        }

        return result;
    }
}
