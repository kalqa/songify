package com.songify.domain.crud;

import com.songify.domain.crud.dto.SongDto;
import com.songify.domain.crud.exception.SongNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Log4j2
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongRetriever {

    private final SongRepository songRepository;

    public List<SongDto> findAll(Pageable pageable) {
        log.info("retrieving all songs: ");
        return songRepository.findAll(pageable)
                .stream()
                .map(song -> SongDto.builder()
                        .id(song.getId())
                        .name(song.getName())
                        .genreId(song.getGenre().getId())
                        .name(song.getName())
                        .build())
                .toList();
    }

    public SongDto findSongDtoById(Long id) {
        Song song = findSongById(id);
        return SongDto.builder()
                .id(song.getId())
                .name(song.getName())
                .genreId(song.getGenre().getId())
                .build();
    }

    Song findSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new SongNotFoundException("Song with id " + id + " not found"));
    }

    void existsById(Long id) {
        if (!songRepository.existsById(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
    }

}
