package com.conference.service;

import com.conference.entity.Topic;

import java.util.List;
public interface TopicService {

    List<Topic> findByEventId(int eventId);

    boolean updateTopic(Topic topic);
}
