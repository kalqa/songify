package com.songify.domain.crud;

import com.songify.domain.crud.dto.AlbumDto;
import com.songify.domain.crud.dto.AlbumRequestDto;
import com.songify.domain.crud.dto.ArtistDto;
import com.songify.domain.crud.dto.ArtistRequestDto;
import com.songify.domain.crud.dto.SongDto;
import com.songify.domain.crud.dto.SongLanguageDto;
import com.songify.domain.crud.dto.SongRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SongifyCrudFacadeTest {

    SongifyCrudFacade songifyCrudFacade = SongifyCrudFacadeConfiguration.createSongifyCrud(
            new InMemorySongRepository(),
            new InMemoryGenreRepository(),
            new InMemoryArtistRepository(),
            new InMemoryAlbumRepository()
    );

    @Test
    @DisplayName("Should add artist 'amigo' with id:0 When amigo was sent")
    public void should_add_artist_amigo_with_id_zero_when_amigo_was_sent() {
        // given
        ArtistRequestDto artist = ArtistRequestDto.builder()
                .name("amigo")
                .build();
        Set<ArtistDto> allArtists = songifyCrudFacade.findAllArtists(Pageable.unpaged());
        assertTrue(allArtists.isEmpty());
        // when
        ArtistDto result = songifyCrudFacade.addArtist(artist);
        // then
        assertThat(result.id()).isEqualTo(0L);
        assertThat(result.name()).isEqualTo("amigo");
        int size = songifyCrudFacade.findAllArtists(Pageable.unpaged()).size();
        assertThat(size).isEqualTo(1);
    }

    @Test
    @DisplayName("Should add artist 'shawn mendes' with id:0 When shawn mendes was sent")
    public void should_add_artist_shawn_mendes_with_id_zero_when_shawn_mendes_was_sent() {
        // given
        ArtistRequestDto shawnMendes = ArtistRequestDto.builder()
                .name("shawn mendes")
                .build();
        Set<ArtistDto> allArtists = songifyCrudFacade.findAllArtists(Pageable.unpaged());
        assertTrue(allArtists.isEmpty());
        // when
        ArtistDto response = songifyCrudFacade.addArtist(shawnMendes);
        // then
        assertThat(response.id()).isEqualTo(0L);
        assertThat(response.name()).isEqualTo("shawn mendes");
        int size = songifyCrudFacade.findAllArtists(Pageable.unpaged()).size();
        assertThat(size).isEqualTo(1);
    }

    @Test
    @DisplayName("Should throw exception ArtistNotFound When id: 0")
    public void should_throw_exception_artist_not_found_when_id_was_zero() {
        // given
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged())).isEmpty();
        // when
        Throwable throwable = catchThrowable(() -> songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(0L));
        // then
        assertThat(throwable).isInstanceOf(ArtistNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("artist with id: 0 not found");
    }

    @Test
    @DisplayName("Should delete artist by id When he have no albums")
    public void should_delete_artist_by_id_when_he_have_no_albums() {
        // given
        ArtistRequestDto shawnMendes = ArtistRequestDto.builder()
                .name("shawn mendes")
                .build();
        Long artistId = songifyCrudFacade.addArtist(shawnMendes).id();
        assertThat(songifyCrudFacade.findAlbumsByArtistId(artistId)).isEmpty();
        // when
        songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(artistId);
        // then
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged())).isEmpty();
    }

    @Test
    @DisplayName("Should delete artist with album and songs by id When artist had one album and he was the only artist in album")
    public void should_delete_artist_with_album_and_songs_by_id_when_artist_had_one_album_and_he_was_the_only_artist_in_album() {
        // given
        ArtistRequestDto shawnMendes = ArtistRequestDto.builder()
                .name("shawn mendes")
                .build();
        Long artistId = songifyCrudFacade.addArtist(shawnMendes).id();
        SongRequestDto song = SongRequestDto.builder()
                .name("song1")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(song);
        Long songId = songDto.id();
        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(AlbumRequestDto
                .builder()
                .songId(songId)
                .title("album title 1")
                .build());
        Long albumId = albumDto.id();
        songifyCrudFacade.addArtistToAlbum(artistId, albumId);
        assertThat(songifyCrudFacade.findAlbumsByArtistId(artistId).size()).isEqualTo(1);
        assertThat(songifyCrudFacade.countArtistsByAlbumId(albumId)).isEqualTo(1);
        // when
        songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(artistId);
        // then
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged())).isEmpty();
        Throwable throwable = catchThrowable(() -> songifyCrudFacade.findSongDtoById(songId));
        assertThat(throwable).isInstanceOf(SongNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Song with id 0 not found");
        Throwable throwable2 = catchThrowable(() -> songifyCrudFacade.findAlbumById(albumId));
        assertThat(throwable2).isInstanceOf(AlbumNotFoundException.class);
        assertThat(throwable2.getMessage()).isEqualTo("Album with id: 0 not found");
    }

    @Test
    @DisplayName("todo")
    public void should_add_album_with_song() {
        // TODO homework
    }

    @Test
    @DisplayName("todo")
    public void should_add_song() {
        // TODO homework
        // given
        SongRequestDto song = SongRequestDto.builder()
                .name("song1")
                .language(SongLanguageDto.ENGLISH)
                .build();
        // when
        SongDto songDto = songifyCrudFacade.addSong(song);
        // then
    }

    @Test
    @DisplayName("todo")
    public void should_add_artist_to_album() {
        // TODO homework
    }

    @Test
    @DisplayName("todo")
    public void should_return_album_by_id() {
        // TODO homework
    }

    @Test
    @DisplayName("todo")
    public void should_throw_exception_when_album_not_found_by_id() {
        // TODO homework
    }

    @Test
    @DisplayName("todo")
    public void should_throw_exception_when_song_not_found_by_id() {
        // TODO homework
    }

    @Test
    @DisplayName("Should delete only artist from album by id When there were more than 1 artist in album")
    public void should_delete_only_artist_from_album_by_when_there_were_more_than_one_artist_in_album() {
//        assertThat(songifyCrudFacade.findAlbumByIdWithArtistsAndSongs(albumId)
//                .getArtists()
//                .size()).isGreaterThanOrEqualTo(2);
        // TODO homework
    }

    @Test
    @DisplayName("should_delete_artist_with_albums_and_songs_by_id_when_artist_was_the_only_artist_in_albums")
    public void should_delete_artist_with_albums_and_songs_by_id_when_artist_was_the_only_artist_in_albums() {
        // TODO homework
    }

}
