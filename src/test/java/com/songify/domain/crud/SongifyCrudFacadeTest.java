package com.songify.domain.crud;

import com.songify.domain.crud.dto.ArtistDto;
import com.songify.domain.crud.dto.ArtistRequestDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class SongifyCrudFacadeTest {

    SongifyCrudFacade songifyCrudFacade = SongifyCrudFacadeConfiguration.createSongifyCrud(
            new InMemorySongRepository(),
            new InMemoryGenreRepository(),
            new InMemoryArtistRepository(),
            new InMemoryAlbumRepository()
    );

    @Test
    public void first() {
        ArtistRequestDto shawnMendes = ArtistRequestDto.builder()
                .name("amigo")
                .build();
        ArtistDto response = songifyCrudFacade.addArtist(shawnMendes);

        assertThat(response.id()).isEqualTo(0L);
        assertThat(response.name()).isEqualTo("amigo");
    }

    @Test
    public void first_wariant_B() {
        ArtistRequestDto shawnMendes = ArtistRequestDto.builder()
                .name("shawn mendes")
                .build();
        ArtistDto response = songifyCrudFacade.addArtist(shawnMendes);

        assertThat(response.id()).isEqualTo(0L);
        assertThat(response.name()).isEqualTo("shawn mendes");
    }

//    @Test
//    public void second() {
//
//    }
//
//    @Test
//    public void third() {
//
//    }
}
