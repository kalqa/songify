package com.songify.domain.crud;

import com.songify.domain.crud.dto.ArtistDto;
import com.songify.domain.crud.exception.ArtistNotFoundException;
import lombok.AllArgsConstructor;


@AllArgsConstructor
class ArtistRetriever {

    private final ArtistRepository artistRepository;

    public ArtistDto findDtoById(final long artistId) {
        Artist artist = findById(artistId);
        return new ArtistDto(artist.getId());
    }

    Artist findById(final long artistId) {
        return artistRepository.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException(artistId));
    }

}
