package com.songify.song.domain.repository;

import com.songify.song.domain.model.Song;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Log4j2
public class SongRepositoryJdbcTemplateImpl implements SongRepository {

    private final JdbcTemplate jdbc;

    @Override
    public List<Song> findAll(Pageable pageable) {
        log.info("find all jdbc template");
        String sql = "SELECT * FROM song";
        RowMapper<Song> purchaseRowMapper = (r, i) -> {
            Song rowObject = new Song();
            rowObject.setId(r.getLong("id"));
            rowObject.setArtist(r.getString("artist"));
            rowObject.setName(r.getString("name"));
            return rowObject;
        };

        return jdbc.query(sql, purchaseRowMapper);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void updateById(Long id, Song newSong) {

    }

    @Override
    public Song save(Song song) {
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }
}
