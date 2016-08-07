package com.forum.server.controller;

import com.forum.server.dto.response.QueryResultDto;
import com.forum.server.dto.theme.ThemesDto;
import com.forum.server.dto.user.UsersShortDto;
import com.forum.server.services.interfaces.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.forum.server.utils.ResponseBuilder.buildResponseGet;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * 06.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/themes", method = GET)
    public ResponseEntity<QueryResultDto> searchThemes(@RequestParam(name = "find") String keyword,
                                                       @RequestParam(name = "section-id", required = false) Integer sectionId,
                                                       @RequestParam(name = "subsection-id", required = false) Integer subsectionId) {
        ThemesDto result = searchService.searchThemes(keyword, sectionId, subsectionId);
        return buildResponseGet(result);
    }

    @RequestMapping(value = "/themes", method = GET)
    public ResponseEntity<QueryResultDto> searchUsers(@RequestParam(name = "find") String keyword,
                                                       @RequestParam(name = "Auth-Token") String token) {
        UsersShortDto result = searchService.searchUsers(keyword, token);
        return buildResponseGet(result);
    }
}
