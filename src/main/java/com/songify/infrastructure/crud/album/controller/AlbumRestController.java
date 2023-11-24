package com.songify.infrastructure.crud.album.controller;

import com.songify.domain.crud.CrudFacade;
import com.songify.domain.crud.dto.AlbumDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@Log4j2
@RequestMapping("/albums")
@AllArgsConstructor
public
class AlbumRestController {

    private final CrudFacade crudFacade;

    @GetMapping
    ResponseEntity<Set<AlbumDto>> getAllAlbums(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Set<AlbumDto> allAlbums = crudFacade.findAllAlbums(pageable);
        return ResponseEntity.ok(allAlbums);
    }

    @PutMapping("/{albumId}/{artistId}")
    ResponseEntity<AlbumDto> addArtistToAlbum(@PathVariable Long albumId,
                                              @PathVariable Long artistId) {
        AlbumDto albumDto = crudFacade.addArtistToAlbum(artistId, albumId);
        return ResponseEntity.ok(albumDto);
    }
}
