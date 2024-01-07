package com.tatonimatteo.campfinder.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Table(name = "places")
@Entity
@Data
public class Place {

    public Place() {
        this.lastUpdate = LocalDate.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String managerName;

    @ManyToMany
    @JoinTable(
            name = "place_contact",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private List<Contact> contacts;

    private double price;
    private boolean tent;
    private boolean structure;
    private String note;
    private LocalDate lastUpdate;
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Review> reviews;
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Image> images;

    @PrePersist
    public void prePersist() {
        this.lastUpdate = (this.lastUpdate != null) ? this.lastUpdate : LocalDate.now();
    }

    public double getAverageRating() {
        double sum = 0.0;
        for (Review review : reviews) sum += review.getGeneralRating();
        return sum / reviews.size();
    }

    public String getLastImage() {
        return images.stream()
                .max(Comparator.comparing(Image::getCreation))
                .map(Image::getPath)
                .orElse(null);
    }
}