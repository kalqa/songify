package com.songify.song.infrastructure.controller;

import java.util.List;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
@Log4j2
public class ArtistSaver {

    List<String> artists;
    String saverName = UUID.randomUUID().toString();

    ArtistSaver(List<String> artists) {
        this.artists = artists;
    }

    void addArtist(String artist) {
        artists.add(artist);
    }

    void printArtistsSize() {
        log.info("actual size is: " + artists.size());
    }

    void printSaverName() {
        log.info("actual saverName is: " + saverName);
    }

    public void printArtists() {
        artists.forEach(log::info);
    }
}
