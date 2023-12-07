package com.songify.domain.crud;

import com.songify.domain.crud.dto.AlbumInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class AlbumRetriever {

    private final AlbumRepository albumRepository;

    AlbumInfo findAlbumByIdWithArtistsAndSongs(final Long id) {
        return albumRepository.findAlbumByIdWithSongsAndArtists(id)
                .orElseThrow(() -> new AlbumNotFoundException("Album with id: " + id + " not found"));

//        Set<Artist> artists = album.getArtists();
//        Set<Song> songs = album.getSongs();
//
//        AlbumDto albumDto = new AlbumDto(album.getId(), album.getTitle());
//        Set<ArtistDto> artistsDto = artists.stream()
//                .map(artist -> new ArtistDto(
//                        artist.getId(),
//                        artist.getName()
//                ))
//                .collect(Collectors.toSet());
//        Set<SongDto> songsDto = songs.stream()
//                .map(song -> new SongDto(
//                        song.getId(),
//                        song.getName()
//                ))
//                .collect(Collectors.toSet());
//        return new AlbumDtoWithArtistsAndSongs(
//                albumDto,
//                artistsDto,
//                songsDto
//        );
    }
}
