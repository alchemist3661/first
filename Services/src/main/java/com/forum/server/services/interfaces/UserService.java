package com.forum.server.services.interfaces;

import com.forum.server.dto.user.UserDto;
import com.forum.server.dto.user.UserShortDto;

/**
 * 08.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public interface UserService {

    UserShortDto getUser(String token, int userId);

    UserDto updateUser(String token, int userId, UserDto userInfo);
}
