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
            "LEFT JOIN FETCH p.reviews r " +
            "GROUP BY p " +
            "ORDER BY COALESCE(AVG(r.servicesRating + r.overallRating + r.managerRating), 0) DESC, p.lastUpdate DESC")
    List<Place> findTopPlacePaged(Pageable pageable);

    @Query("SELECT DISTINCT p FROM Place p " +
            "LEFT JOIN FETCH p.reviews r " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.address) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "GROUP BY p " +
            "ORDER BY COALESCE(AVG(r.servicesRating + r.overallRating + r.managerRating), 0) DESC, p.lastUpdate DESC")
    List<Place> findSearchPaged(@Param("query") String query, Pageable pageable);

    @Query("SELECT DISTINCT p FROM Place p " +
            "LEFT JOIN FETCH p.reviews r " +
            "GROUP BY p " +
            "ORDER BY COALESCE(AVG(r.servicesRating + r.overallRating + r.managerRating), 0) DESC, p.lastUpdate DESC")
    List<Place> findTopPlace();

    @Query("SELECT DISTINCT p FROM Place p " +
            "LEFT JOIN FETCH p.reviews r " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.address) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "GROUP BY p " +
            "ORDER BY COALESCE(AVG(r.servicesRating + r.overallRating + r.managerRating), 0) DESC, p.lastUpdate DESC")
    List<Place> findSearch(@Param("query") String query);

}
