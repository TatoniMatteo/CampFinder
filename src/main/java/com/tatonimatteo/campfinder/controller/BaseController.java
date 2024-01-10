package com.tatonimatteo.campfinder.controller;

import com.tatonimatteo.campfinder.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BaseController {

    private final PlaceService placeService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("places", placeService.getTopPlaces(10, 0));
        return "home";
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("places", placeService.getTopPlaces(20, 0));
        return "search";
    }

    @GetMapping("/placedetails")
    public String placeDetails(@RequestParam(name = "id") Long placeId, Model model) {
        model.addAttribute("place", placeService.getPlaceById(placeId));
        return "placedetails";
    }
}
