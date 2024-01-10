package com.tatonimatteo.campfinder.repository;

import com.tatonimatteo.campfinder.entity.Place;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("SELECT DISTINCT p FROM Place p " +
            "JOIN FETCH p.reviews r " +
            "GROUP BY p " +
            "ORDER BY AVG(r.servicesRating + r.overallRating + r.managerRating) DESC, p.lastUpdate DESC")
    List<Place> findTopPlace(Pageable pageable);

    @Query("SELECT DISTINCT p FROM Place p " +
            "JOIN FETCH p.reviews r " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.address) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "GROUP BY p " +
            "ORDER BY AVG(r.servicesRating + r.overallRating + r.managerRating) DESC, p.lastUpdate DESC")
    List<Place> findFilteredPlaces(@Param("searchTerm") String searchTerm, Pageable pageable);
}
