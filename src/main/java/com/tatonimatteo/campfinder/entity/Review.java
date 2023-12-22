package com.tatonimatteo.campfinder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Table(name = "reviews")
@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double servicesRating;
    private double managerRating;
    private double overallRating;
    private String comment;
    private LocalDate date;

    @PrePersist
    public void prePersist() {
        this.date = (this.date != null) ? this.date : LocalDate.now();
    }

    public Double getGeneralRating() {
        return (servicesRating + managerRating + overallRating) / 3;
    }

}