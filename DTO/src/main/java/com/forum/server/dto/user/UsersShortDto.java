package com.forum.server.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.forum.server.dto.Data;

import java.util.List;

/**
 * 07.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class UsersShortDto implements Data {
    private List<UsersShortDto> users;

    @JsonValue
    public List<UsersShortDto> getThemes() {
        return users;
    }

    public UsersShortDto(List<UsersShortDto> users) {
        this.users = users;
    }
}
