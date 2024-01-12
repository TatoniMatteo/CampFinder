package com.tatonimatteo.campfinder.repository;

import com.tatonimatteo.campfinder.entity.Place;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("SELECT DISTINCT p FROM Place p " +
            "JOIN FETCH p.reviews r " +
            "GROUP BY p " +
            "ORDER BY AVG(r.servicesRating + r.overallRating + r.managerRating) DESC, p.lastUpdate DESC")
    List<Place> findTopPlace(Pageable pageable);

    List<Place> findAllByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(
            String name, String address, Pageable pageable);

    long countByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String name, String address);
}
