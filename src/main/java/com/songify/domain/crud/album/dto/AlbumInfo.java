package com.songify.domain.crud.album.dto;

import java.time.Instant;

/**
 * Projection for {@link com.songify.domain.crud.album.Album}
 */
public interface AlbumInfo {
    Long getId();

    String getTitle();

    Instant getReleaseDate();
}
