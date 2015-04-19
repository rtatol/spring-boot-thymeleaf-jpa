package com.example.boot.thymeleaf.controller;

import com.example.boot.thymeleaf.domain.Item;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ItemController {

    private static final String TEMPLATE_FORM = "base/item";

    @RequestMapping(value = {"/item"}, method = RequestMethod.GET)
    public String view(@ModelAttribute Item item) {
        return TEMPLATE_FORM;
    }

    @RequestMapping(value = {"/item"}, method = RequestMethod.POST)
    public ModelAndView save(@Valid Item item, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView(TEMPLATE_FORM, "formErrors", result.getAllErrors());
        }
        redirect.addFlashAttribute("globalMsg", "Successfully created a new item.");
        return new ModelAndView("redirect:/");
    }
}
