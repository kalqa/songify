package com.songify.domain.crud.song;

/**
 * Projection for {@link Song}
 */
public interface SongCountAndAverageProjection {
    Long getCount();

    Double getAverageDuration();
}
