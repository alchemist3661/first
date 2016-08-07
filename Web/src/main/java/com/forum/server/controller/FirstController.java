package com.forum.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 02.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@RestController
public class FirstController {

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to best forum's server!";
    }

    @RequestMapping("/about")
    public String easterEgg() {
        return "Rafikov Dinar \nSalin Aleksandr \nVarfaev Emil";
    }
}
