package com.songify.domain.crud.album;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends Repository<Album, Long> {

    @Query("SELECT s FROM Album s")
    List<Album> findAll(Pageable pageable);

    @Query("SELECT s FROM Album s WHERE s.id =:id")
    Optional<Album> findById(Long id);
}
