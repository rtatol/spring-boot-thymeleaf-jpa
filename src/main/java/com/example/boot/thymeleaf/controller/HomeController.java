package com.example.boot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private static final String TEMPLATE_INDEX = "base/index";

    @RequestMapping(value = {"/", "dashboard"}, method = RequestMethod.GET)
    public String view() {
        return TEMPLATE_INDEX;
    }
}
