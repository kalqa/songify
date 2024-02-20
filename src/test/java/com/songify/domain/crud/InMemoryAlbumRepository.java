package com.songify.domain.crud;

import com.songify.domain.crud.dto.AlbumInfo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

class InMemoryAlbumRepository implements AlbumRepository {

    Map<Long, Album> db = new HashMap<>();

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
        return db.values().stream()
                .filter(album -> album.getArtists().stream()
                        .anyMatch(artist -> artist.getId().equals(id)))
                .collect(Collectors.toSet());
    }
}
