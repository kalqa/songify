package com.songify.song.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Entity
@Table(name = "song")
@Getter
@Setter
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    Long id;

    @Column(nullable = false)
    String name;

    @ManyToMany
    @JoinTable(
            name = "artists_songs",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    Set<Artist> artistsSongs;

    public Song() {
    }

    public Song(String name, Set<Artist> artistsSongs) {
        this.name = name;
        this.artistsSongs = artistsSongs;
    }

    public Song(Long id, String name, Set<Artist> artistsSongs) {
        this.id = id;
        this.name = name;
        this.artistsSongs = artistsSongs;
    }
}
