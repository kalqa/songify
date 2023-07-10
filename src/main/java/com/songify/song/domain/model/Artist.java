package com.songify.song.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Entity
@Table(name = "artist")
@Getter
@Setter
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "artistsSongs")
    private Set<Song> songs;

    public Artist() {

    }

    public Artist(String name, Set<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    public Artist(Long id, String name, Set<Song> songs) {
        this.id = id;
        this.name = name;
        this.songs = songs;
    }
}
