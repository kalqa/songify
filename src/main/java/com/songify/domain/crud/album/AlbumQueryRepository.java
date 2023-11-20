package com.songify.domain.crud.album;

import com.songify.domain.crud.album.query.SimpleAlbumQueryDto;
import org.springframework.data.repository.Repository;

import java.util.Optional;

interface AlbumQueryRepository extends Repository<SimpleAlbumQueryDto, Long> {

    Optional<SimpleAlbumQueryDto> findQueryDtoById(Long id);
}
