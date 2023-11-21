package com.songify.domain.crud.song;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

interface SongRepository extends Repository<Song, Long> {

    @Query("SELECT s FROM Song s")
    List<Song> findAll(Pageable pageable);

//    @Query("SELECT s FROM Song s")
//    List<Song> findAll(Pageable pageable);

    @Query("SELECT s FROM Song s WHERE s.id =:id")
    Optional<Song> findById(Long id);

    @Modifying
    @Query("DELETE FROM Song s WHERE s.id = :id")
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Song s SET s.name = :#{#newSong.name} WHERE s.id = :id")
    void updateById(Long id, Song newSong);

//    @Query(value = "Select COUNT(s) AS count, AVG (s.duration) AS averageDuration from Song s")
//    SongCountAndAverageProjection coundAndAverageDuration();
//
//    @Query(value = "Select COUNT(s) as count, s.album.id as albumId from Song s group by s.album.id")
//    List<SongCountAndAlbumIdProjection> countAndIdOfAlbum(Long albumId);
//

    // todo check if it works
    @Query(value = "Select s from Song s " +
            "left join s.genreId g " +
//            "left join s.album a " +
            "order by s.id")
    List<Song> findSongsWithGenresUsingTwoJoins();

    Song save(Song song);

//    @Modifying
//    @Query(value = "INSERT INTO Song (name, duration, release_date, album_id, genre_id) VALUES ('Bailando', 190, '2023-01-20 05:45:00', 9, 2)", nativeQuery = true)
//    void saveBig();

//    @Modifying
//    @Query(value = "Insert INTO Song (name, duration, album_id) VALUES (:#{#song.name}, :#{#song.duration}, :#{#song.album.id}")
//    Song saveWithAlbumId(Song song);

    boolean existsById(Long id);
}
