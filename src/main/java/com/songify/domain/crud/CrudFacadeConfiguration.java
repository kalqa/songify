package com.songify.domain.crud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CrudFacadeConfiguration {

    @Bean
    CrudFacade crudFacade(SongRepository songRepository,
                          AlbumRepository albumRepository,
                          GenreRepository genreRepository,
                          ArtistRepository artistRepository) {
        SongAdder songAdder = new SongAdder(songRepository);
        AlbumRetriever albumRetriever = new AlbumRetriever(albumRepository);
        ArtistRetriever artistRetriever = new ArtistRetriever(artistRepository);
        SongRetriever songRetriever = new SongRetriever(songRepository);
        GenreRetriever genreRetriever = new GenreRetriever(genreRepository);
        AlbumUpdater albumUpdater = new AlbumUpdater(albumRetriever, artistRetriever);
        SongUpdater songUpdater = new SongUpdater(songRepository, songRetriever, genreRetriever);
        SongDeleter songDeleter = new SongDeleter(songRepository, songRetriever);
        return new CrudFacade(
                songAdder,
                albumRetriever,
                songRetriever,
                genreRetriever,
                albumUpdater,
                songUpdater,
                songDeleter
        );
    }
}
