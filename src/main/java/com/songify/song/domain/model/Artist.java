package com.songify.song.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String name;

//    @ManyToOne(fetch = FetchType.LAZY)
//    Song song;

    public Artist(Long id) {
        this.id = id;
    }

    public Artist(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Artist() {

    }

    public Artist(String name) {
        this.name = name;
    }
}
