package com.example.boot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FormController {

    private static final String TEMPLATE_FORM = "base/form";

    @RequestMapping(value = {"/form"}, method = RequestMethod.GET)
    public String view() {
        return TEMPLATE_FORM;
    }
}
