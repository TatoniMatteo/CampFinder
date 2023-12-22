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
import java.util.List;

@Table(name = "places")
@Entity
@Data
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @PrePersist
    public void prePersist() {
        this.lastUpdate = (this.lastUpdate != null) ? this.lastUpdate : LocalDate.now();
    }
}