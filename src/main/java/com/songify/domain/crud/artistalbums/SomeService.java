package com.songify.domain.crud.artistalbums;

import com.songify.domain.crud.album.Album;
import com.songify.domain.crud.album.AlbumRepository;
import com.songify.domain.crud.artist.Artist;
import com.songify.domain.crud.artist.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class SomeService {

    private final ArtistAlbumRepository artistAlbumsRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public void first() {
        Optional<ArtistAlbums> byId = artistAlbumsRepository.findById(new AlbumArtistId(1L, 1L));
        Set<ArtistAlbums> allArtist = artistAlbumsRepository.findByIdAlbumId(1L);
        System.out.println(allArtist);
        System.out.println(byId);
        artistAlbumsRepository.save(new ArtistAlbums(new AlbumArtistId(50L, 100L)));
        System.out.println("saved");
    }

    public void second() {
        Optional<Album> byId = albumRepository.findById(1L);
        Album album = byId.get();
        System.out.println(album);
    }

//    public void third() {
//        Optional<Artist> byId = artistRepository.findById(9L);
//        Artist artistDto = byId.get();
//        System.out.println(artistDto);
//        artistDto.addArtistToAlbum(1L);
//        System.out.println(artistDto);
//    }

    public void addArtistToAlbum(Long artistId, Long albumId) {
        artistAlbumsRepository.save(new ArtistAlbums(new AlbumArtistId(albumId, artistId)));
    }

    public void deleteArtistFromAlbum(Long artistId, Long albumId) {
        artistAlbumsRepository.deleteById_AlbumIdAndId_ArtistId(albumId,artistId );
    }

    public void deleteArtist(Long artistId) {
        artistRepository.deleteById(artistId);
    }
}
