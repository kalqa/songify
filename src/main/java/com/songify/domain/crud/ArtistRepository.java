package com.songify.domain.crud;

import com.songify.domain.crud.dto.ArtistDto;
import org.springframework.data.repository.Repository;

interface ArtistRepository extends Repository<Artist, Long> {
    Artist save(Artist artist);
}
