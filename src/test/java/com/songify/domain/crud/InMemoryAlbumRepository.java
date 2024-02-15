package com.songify.domain.crud;

import com.songify.domain.crud.dto.AlbumInfo;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

class InMemoryAlbumRepository implements AlbumRepository {
    @Override
    public Optional<Album> findById(final Long id) {
        return Optional.empty();
    }

    @Override
    public int deleteByIdIn(final Collection<Long> ids) {
        return 0;
    }

    @Override
    public Album save(final Album album) {
        return null;
    }

    @Override
    public Optional<AlbumInfo> findAlbumByIdWithSongsAndArtists(final Long id) {
        return Optional.empty();
    }

    @Override
    public Set<Album> findAllAlbumsByArtistId(final Long id) {
        return null;
    }
}
