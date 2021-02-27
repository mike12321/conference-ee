package com.conference.dao.implementation;

import com.conference.dao.GenericAbstractDao;
import com.conference.dao.Mapper;
import com.conference.dao.TopicDao;
import com.conference.entity.Topic;
import com.conference.entity.User;
import com.conference.entity.UserRole;
import com.conference.exceptions.DataNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class TopicDaoImpl extends GenericAbstractDao<Topic> implements TopicDao {

    private Connection connection;
    private static String SQL_findByEventId = "SELECT * from topic where event_id = ?;";
    private static String SQL_updateTopic = "UPDATE topic SET title=?, speaker_id=?, " +
            "event_id=?, approved=? WHERE id=?;";

    private Mapper<Topic, PreparedStatement> mapperToDB = (Topic topic, PreparedStatement preparedStatement) -> {
        preparedStatement.setString(1, topic.getTitle());
        preparedStatement.setInt(2, topic.getSpeakerId());
        preparedStatement.setInt(3, topic.getEventId());
        preparedStatement.setBoolean(4, topic.isApproved());
    };

    private Mapper<ResultSet, Topic> mapperFromDB = (ResultSet resultSet, Topic topic) -> {
        topic.setId(resultSet.getInt("id"));
        topic.setTitle(resultSet.getString("title"));
        topic.setSpeakerId(resultSet.getInt("speaker_id"));
        topic.setEventId(resultSet.getInt("event_id"));
        topic.setApproved(resultSet.getBoolean("approved"));
    };

    public TopicDaoImpl(Connection connection) {
        super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
        this.connection = connection;
    }

    @Override
    public List<Topic> findByEventId(int eventId) throws DataNotFoundException {
        return findAsListBy(connection, Topic.class, SQL_findByEventId, eventId);
    }

    @Override
    public boolean updateTopic(Topic topic) {
        Integer id = topic.getId();
        return updateInDB(connection, topic, SQL_updateTopic, 5, id);
    }

//    @Override
//    public boolean setApproved(int topicId, boolean approved) {
//        return false;
//    }
}
