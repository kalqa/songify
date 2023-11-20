package com.songify.domain.crud.album;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Service
@Log4j2
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class AlbumRetriever {

    private final AlbumRepository albumRepository;
}
