package com.songify.domain.crud.song.model;

import com.songify.domain.crud.song.model.Song;

/**
 * Projection for {@link Song}
 */
public interface SongCountAndAverageProjection {
    Long getCount();

    Double getAverageDuration();
}
