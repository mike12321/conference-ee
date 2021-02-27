package com.conference.command.implementation;

import com.conference.command.Command;
import com.conference.config.Configuration;
import com.conference.controller.Direction;
import com.conference.controller.ExecutionResult;
import com.conference.controller.SessionRequestContent;
import com.conference.domain.Product;
import com.conference.exceptions.ProductServiceException;
import com.conference.service.IProductServ;
import com.conference.service.ServiceFactory;
import com.conference.service.TopicService;

public class CommandOpenEventEditPage implements Command {

    @Override
    public ExecutionResult execute(SessionRequestContent content) {
        ExecutionResult result = new ExecutionResult();

        try {
            IProductServ prodServ = ServiceFactory.getProductService();
            TopicService topicService = ServiceFactory.getTopicService();
            int id = Integer.parseInt(content.getRequestParameter("id")[0]);
            Product product = prodServ.findProductById(id);

            result.setPage(Configuration.getInstance().getPage("login"));
            result.setDirection(Direction.FORWARD);
        } catch (ProductServiceException e) {
            e.printStackTrace();
        }
        return result;
    }
}
