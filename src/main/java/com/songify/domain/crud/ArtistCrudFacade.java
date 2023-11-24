package com.songify.domain.crud;

import com.songify.domain.crud.dto.ArtistDto;
import com.songify.domain.crud.exception.ArtistNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
@Transactional
class ArtistCrudFacade {

    private final ArtistRepository artistRepository;


    public ArtistDto findDtoById(final long artistId) {
        return artistRepository.findById(artistId)
                .map(artist -> new ArtistDto(artist.getId()))
                .orElseThrow(() -> new ArtistNotFoundException(artistId));
    }

    Artist findById(final long artistId) {
        return artistRepository.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException(artistId));
    }

}
