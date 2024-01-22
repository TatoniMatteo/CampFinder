package com.tatonimatteo.campfinder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Table(name = "images")
@Entity
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String path;
    private LocalDate creation;

    @ManyToOne
    private Place place;

    @PrePersist
    public void prePersist() {
        this.creation = (this.creation != null) ? this.creation : LocalDate.now();
    }

    public Image() {
        this.creation = LocalDate.now();
    }
}
