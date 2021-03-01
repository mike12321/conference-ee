package com.conference.dao;

import com.conference.entity.UserEvent;

public interface UserEventDao {

    boolean assignUserToEvent(UserEvent userEvent);
}
