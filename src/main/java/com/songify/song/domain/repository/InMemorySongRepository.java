package com.songify.song.domain.repository;

import com.songify.song.domain.model.Song;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class InMemorySongRepository implements SongRepository {

    Map<Long, Song> database = new HashMap<>(Map.of(
            1L, new Song("shawnmendes song1", "Shawn Mendes"),
            2L, new Song("ariana grande song2", "Ariana Grande"),
            3L, new Song("ariana grande song21123123", "Ariana Grande"),
            4L, new Song("ariana grande song12312314345cbvbcvb", "Ariana Grande")
    ));

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
