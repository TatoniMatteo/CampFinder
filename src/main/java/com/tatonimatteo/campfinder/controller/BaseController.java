package com.tatonimatteo.campfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/search")
    public String search(Model model) {
        return "search";
    }
}
