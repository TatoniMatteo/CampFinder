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
    public String search(@RequestParam(required = false, defaultValue = "") String query,
                         @RequestParam(required = false, defaultValue = "false") boolean tent,
                         @RequestParam(required = false, defaultValue = "false") boolean bed,
                         @RequestParam(required = false, defaultValue = "1") int page,
                         Model model) {
        model.addAttribute("query", query);
        model.addAttribute("tent", tent);
        model.addAttribute("bed", bed);
        model.addAttribute("page", page);
        model.addAttribute("places", placeService.searchPlace(query, tent, bed, 20, 0));
        model.addAttribute("pages", 5);
        return "search";
    }

    @GetMapping("/placedetails")
    public String placeDetails(@RequestParam(name = "id") Long placeId, Model model) {
        model.addAttribute("place", placeService.getPlaceById(placeId));
        return "placedetails";
    }
}
