package com.songify.song.domain.repository;

import com.songify.song.domain.model.Artist;
import com.songify.song.domain.model.Song;
import org.springframework.data.domain.Pageable;

import java.util.*;


public class InMemorySongRepository implements SongRepository {

    Map<Long, Song> database;

    InMemorySongRepository() {
        Song song1 = new Song("shawnmendes song1", Collections.emptySet());
        Song song2 = new Song("ariana grande song2 feat. Shawn Mendes", Collections.emptySet());
        Artist artist1 = new Artist(1L, "Shawn Mendes", Collections.emptySet());
        Artist artist2 = new Artist(2L, "Ariana Grande", Collections.emptySet());
        song1.setArtistsSongs(Set.of(artist1));
        song2.setArtistsSongs(Set.of(artist1, artist2));
        database = new HashMap<>(Map.of(
                1L, song1,
                2L, song2
        ));
    }

    public Song saveToDatabase(Song song) {
        database.put(database.size() + 1L, song);
        return song;
    }

    @Override
    public Optional<Song> findById(Long id) {
        return Optional.empty();
    }


    @Override
    public void deleteById(Long id) {
        database.remove(id);
    }

    @Override
    public void updateById(Long id, Song newSong) {

    }

    @Override
    public List<Song> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Song save(Song song) {
        saveToDatabase(song);
        return song;
    }
}
