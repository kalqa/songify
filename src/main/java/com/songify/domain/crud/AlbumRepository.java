package com.songify.domain.crud;

import org.springframework.data.repository.Repository;

interface AlbumRepository extends Repository<Album, Long> {

    Album save(Album album);
}
