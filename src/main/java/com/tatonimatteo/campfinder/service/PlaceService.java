package com.tatonimatteo.campfinder.service;

import com.tatonimatteo.campfinder.entity.Place;
import com.tatonimatteo.campfinder.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return repository.findTopPlace(getPageable(resultForPage, page));
    }

    public List<Place> findFilteredPlaces(String query, int resultForPage, int page) {
        return repository.findFilteredPlaces(query, getPageable(resultForPage, page));
    }

    public List<Place> searchPlace(String query, boolean tent, boolean bed, int resultForPage, int page) {
        List<Place> result;
        if (query.isEmpty()) result = getTopPlaces(resultForPage, page);
        else result = findFilteredPlaces(query, resultForPage, page);
        return filter(tent, bed, result);
    }

    private Pageable getPageable(int resultForPage, int page) {
        int firstResult = resultForPage * page;
        return PageRequest.of(firstResult, firstResult + resultForPage);
    }

    private List<Place> filter(boolean tent, boolean bed, List<Place> places) {
        return places.stream()
                .filter(place -> (!tent || place.isTent()) && (!bed || place.isStructure()))
                .toList();
    }
}
