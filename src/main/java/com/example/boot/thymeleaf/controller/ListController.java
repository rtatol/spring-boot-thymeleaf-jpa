package com.example.boot.thymeleaf.controller;

import com.example.boot.thymeleaf.service.ItemService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ListController {

    private static final String TEMPLATE_LIST = "base/list";
    private static final String MODEL_ITEM_LIST = "itemList";

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute(MODEL_ITEM_LIST, itemService.getAll());
        return TEMPLATE_LIST;
    }

    @RequestMapping(value = {"/list/search"}, method = RequestMethod.GET)
    public String viewSearch(Model model, @RequestParam String name) {
        model.addAttribute(MODEL_ITEM_LIST, itemService.findByName(Optional.ofNullable(name)));
        return TEMPLATE_LIST;
    }
}
