package com.songify.song.domain.repository;

import com.songify.song.domain.model.Song;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends Repository<Song, Long> {

    Song save(Song song);

    List<Song> findAll();

    Optional<Song> findById(Long id);

    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Song s SET s.name = :#{#newSong.name}, s.artist = :#{#newSong.artist} WHERE s.id = :id")
    void updateById(Long id, Song newSong);

    boolean existsById(Long id);
}
