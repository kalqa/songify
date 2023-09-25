package com.songify.song.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Entity
@Setter
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String name;

//    @OneToMany
//    @JoinTable(
//            joinColumns = { @JoinColumn(name = "song_id")},
//            inverseJoinColumns = { @JoinColumn(name = "artist_id")}
//    )
    @OneToMany
    Set<Artist> artists;

    public Song() {

    }

    public Song(String name, Set<Artist> artists) {
        this.name = name;
        this.artists = artists;
    }

    public Song(Long id, String name, Set<Artist> artists) {
        this.id = id;
        this.name = name;
        this.artists = artists;
    }

    //    public Song(String name, String artist) {
//        this.name = name;
//        this.artist = artist;
//    }
//
//    public Song(Long id, String name, String artist) {
//        this.id = id;
//        this.name = name;
//        this.artist = artist;
//    }

//    public Song(Long id, String name, String artist, Set<Artist> artists) {
//        this.id = id;
//        this.name = name;
//        this.artist = artist;
//        this.artists = artists;
//    }
}
