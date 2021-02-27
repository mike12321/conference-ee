package com.conference.dao;

import com.conference.entity.Topic;
import com.conference.exceptions.DataNotFoundException;

import java.util.List;

public interface TopicDao {

    List<Topic> findByEventId(int eventId) throws DataNotFoundException;

    boolean updateTopic(Topic topic);
}
