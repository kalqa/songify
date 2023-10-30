package com.songify.domain.crud.song.service;

import com.songify.domain.crud.dto.SongDto;
import com.songify.domain.crud.song.model.Song;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class SongCrudFacade {

    private final SongRetriever songRetriever;
    private final SongUpdater songUpdater;
    private final SongDeleter songDeleter;
    private final SongAdder songAdder;

    public List<SongDto> findAll(Pageable pageable) {
        return songRetriever.findAll(pageable)
                .stream()
                .map(song -> SongDto.builder()
                        .id(song.getId())
                        .name(song.getName())
                        .build())
                .toList();
    }

    public void updateById(Long id, SongDto newSongDto) {
        songRetriever.existsById(id);
        // some domain validator
        Song songValidatedAndReadyToUpdate = Song.builder()
                .name(newSongDto.name())
                .build();
        // some domain validator ended checking
        songUpdater.updateById(id, songValidatedAndReadyToUpdate);
    }

    public SongDto updatePartiallyById(Long id, SongDto songFromRequest) {
        songRetriever.existsById(id);
        Song songFromDatabase = songRetriever.findSongDtoById(id);
        Song.SongBuilder builder = Song.builder();
        if (songFromRequest.name() != null) {
            builder.name(songFromRequest.name());
        } else {
            builder.name(songFromDatabase.getName());
        }
//        todo
//        if (songFromRequest.getArtist() != null) {
//            builder.artist(songFromRequest.getArtist());
//        } else {
//            builder.artist(songFromDatabase.getArtist());
//        }
        Song toSave = builder.build();
        songUpdater.updateById(id, toSave);
        return SongDto.builder()
                .id(toSave.getId())
                .name(toSave.getName())
                .build();

    }

    public SongDto addSong(SongDto songDto) {
        // some domain validator
        String name = songDto.name();
        Song vaidatedAndReadytoSaveSong = new Song(name);
        // some domain validator ended checking
        Song addedSong = songAdder.addSong(vaidatedAndReadytoSaveSong);
        return SongDto.builder()
                .id(addedSong.getId())
                .name(addedSong.getName())
                .build();
    }

    public void deleteById(Long id) {
        songRetriever.existsById(id);
        songDeleter.deleteById(id);
    }

    public SongDto findSongDtoById(Long id) {
        Song song = songRetriever.findSongDtoById(id);
        return SongDto.builder()
                .id(song.getId())
                .name(song.getName())
                .build();
    }
}
