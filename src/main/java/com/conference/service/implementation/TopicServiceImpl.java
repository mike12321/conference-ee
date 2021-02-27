package com.conference.service.implementation;

import com.conference.dao.DaoFactory;
import com.conference.dao.DataBaseSelector;
import com.conference.dao.TopicDao;
import com.conference.entity.Topic;
import com.conference.exceptions.DataBaseConnectionException;
import com.conference.exceptions.DataBaseNotSupportedException;
import com.conference.exceptions.DataNotFoundException;
import com.conference.exceptions.IncorrectPropertyException;
import com.conference.service.TopicService;
import org.apache.log4j.Logger;

import java.util.List;

public class TopicServiceImpl implements TopicService {

    private static final Logger log = Logger.getLogger(TopicServiceImpl.class);
    private static final DataBaseSelector source = DataBaseSelector.MY_SQL;

    private static DaoFactory daoFactory;
    private static TopicDao topicDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            topicDao = daoFactory.getTopicDao();
        } catch (IncorrectPropertyException | DataBaseConnectionException | DataBaseNotSupportedException ex) {
            log.error(ex);
        }
    }

    @Override
    public List<Topic> findByEventId(int eventId) {
        List<Topic> topics = null;

        try {
            daoFactory.open();
            topicDao = daoFactory.getTopicDao();
            topics = topicDao.findByEventId(eventId);
            daoFactory.close();
        }
        catch (DataBaseConnectionException | DataNotFoundException ex){
            log.error(ex);
        }

        return topics;
    }

    @Override
    public boolean updateTopic(Topic topic) {
        boolean result;

        try {
            daoFactory.beginTransaction();
            topicDao = daoFactory.getTopicDao();
            result = topicDao.updateTopic(topic);
            daoFactory.commitTransaction();
        }
        catch (DataBaseConnectionException exception) {
            log.error(exception);
            return false;
        }

        return result;
    }


}
