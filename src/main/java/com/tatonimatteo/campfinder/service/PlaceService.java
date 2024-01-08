package com.tatonimatteo.campfinder.service;

import com.tatonimatteo.campfinder.entity.Place;
import com.tatonimatteo.campfinder.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository repository;

    public List<Place> getTopPlaces(int numberOfResults) {
        Pageable pageable = PageRequest.of(0, numberOfResults);
        return repository.findTopPlace(pageable);
    }

    public Place getPlaceById(long id) {
        return repository.findById(id).orElse(null);
    }
}
