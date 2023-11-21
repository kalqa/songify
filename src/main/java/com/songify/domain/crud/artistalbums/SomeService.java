package com.songify.domain.crud.artistalbums;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SomeService {

    private final ArtistAlbumRepository albumRepository;

    public void f() {
        Optional<ArtistAlbums> byId = albumRepository.findById(new AlbumArtistId(1L, 1L));
        Set<ArtistAlbums> allArtist = albumRepository.findByIdAlbumId(1L);
        System.out.println(allArtist);
        System.out.println(byId);
        albumRepository.save(new ArtistAlbums(new AlbumArtistId(50L, 100L)));
        System.out.println("saved");
    }
}
