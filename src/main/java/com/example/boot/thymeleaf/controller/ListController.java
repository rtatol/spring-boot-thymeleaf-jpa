package com.example.boot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ListController {

    private static final String TEMPLATE_LIST = "base/list";

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String view() {
        return TEMPLATE_LIST;
    }
}
