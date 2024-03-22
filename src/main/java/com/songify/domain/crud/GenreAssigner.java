package com.songify.domain.crud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
class GenreAssigner {

    private final GenreRetriever genreRetriever;
    private final SongRetriever songRetriever;

    void assignGenreToSong(Long genreId, Long songId) {
        Song song = songRetriever.findSongById(songId);
        Genre genre = genreRetriever.findGenreById(genreId);
        song.setGenre(genre);
    }

    void assignDefaultGenreToSong(Long songId) {
        Song song = songRetriever.findSongById(songId);
        Genre genre = genreRetriever.findGenreById(1L);
        song.setGenre(genre);
    }
}
