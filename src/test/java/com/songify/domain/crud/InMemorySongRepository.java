package com.songify.domain.crud;

import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

class InMemorySongRepository implements SongRepository{
    @Override
    public int deleteByIdIn(final Collection<Long> ids) {
        return 0;
    }

    @Override
    public List<Song> findAll(final Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Song> findById(final Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(final Long id) {

    }

    @Override
    public void updateById(final Long id, final Song newSong) {

    }

    @Override
    public Song save(final Song song) {
        return null;
    }

    @Override
    public boolean existsById(final Long id) {
        return false;
    }
}
