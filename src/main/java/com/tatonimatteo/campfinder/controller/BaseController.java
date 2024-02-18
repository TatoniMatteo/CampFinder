package com.tatonimatteo.campfinder.controller;

import com.tatonimatteo.campfinder.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class BaseController {

    private final PlaceService placeService;
    private static final int ITEM_FOR_PAGE = 20;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("places", placeService.getTopPlaces(10, 0));
        model.addAttribute("styles", new ArrayList<String>() {{
            add("place");
            add("home");
        }});
        model.addAttribute("scripts", new ArrayList<String>() {{
            add("home_animations");
        }});
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
        model.addAttribute("places", placeService.searchPlace(query, tent, bed, ITEM_FOR_PAGE, page - 1));
        model.addAttribute("pages", placeService.getSearchPlacePageNumber(query, tent, bed, ITEM_FOR_PAGE));
        model.addAttribute("styles", new ArrayList<String>() {{
            add("place");
            add("search");
        }});
        return "search";
    }

    @GetMapping("/placedetails")
    public String placeDetails(@RequestParam(name = "id") Long placeId, Model model) {
        model.addAttribute("place", placeService.getPlaceById(placeId));
        model.addAttribute("styles", new ArrayList<String>() {{
            add("placedetails");
        }});
        return "placedetails";
    }
}
