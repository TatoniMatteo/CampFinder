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

    private Pageable getPageable(int resultForPage, int page) {
        int firstResult = resultForPage * page;
        return PageRequest.of(firstResult, firstResult + resultForPage);
    }
}
