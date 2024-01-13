package com.tatonimatteo.campfinder.service;

import com.tatonimatteo.campfinder.entity.Place;
import com.tatonimatteo.campfinder.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository repository;

    public Place getPlaceById(long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Place> getTopPlaces(int resultForPage, int page) {
        return repository.findTopPlacePaged(PageRequest.of(page, resultForPage));
    }

    public List<Place> findFilteredPlaces(String query, int resultForPage, int page) {
        return repository.findSearchPaged(query, PageRequest.of(page, resultForPage));
    }

    public List<Place> searchPlace(String query, boolean tent, boolean bed, int resultForPage, int page) {
        List<Place> result;
        if (query.isEmpty()) result = getTopPlaces(resultForPage, page);
        else result = findFilteredPlaces(query, resultForPage, page);
        return filter(tent, bed, result);
    }

    public int getSearchPlacePageNumber(String query, boolean tent, boolean bed, int resultForPage) {
        List<Place> result = query.isEmpty() ? repository.findTopPlace() : repository.findSearch(query);
        return (int) Math.ceil((double) filter(tent, bed, result).size() / resultForPage);
    }

    private List<Place> filter(boolean tent, boolean bed, List<Place> places) {
        return places.stream().filter(place -> (!tent || place.isTent()) && (!bed || place.isStructure())).toList();
    }
}
