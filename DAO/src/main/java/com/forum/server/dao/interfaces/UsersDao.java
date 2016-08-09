package com.forum.server.dao.interfaces;

import com.forum.server.models.user.User;

/**
 * 09.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface UsersDao {
    User findByToken(String token);
}
