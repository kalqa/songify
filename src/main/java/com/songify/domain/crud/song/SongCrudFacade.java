package com.songify.domain.crud.song;

import com.songify.domain.crud.song.dto.SongDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
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
                        .name(song.getName())
                        .build())
                .toList();
    }

    public void updateById(Long id, SongDto newSongDto) {
        songRetriever.existsById(id);
        // some domain validator
        Song songValidatedAndReadyToUpdate = new Song(newSongDto.name());
        // some domain validator ended checking
        songUpdater.updateById(id, songValidatedAndReadyToUpdate);
    }

    public SongDto updatePartiallyById(Long id, SongDto songFromRequest) {
        songRetriever.existsById(id);
        Song songFromDatabase = songRetriever.findSongDtoById(id);
        Song toSave = new Song();
        if (songFromRequest.name() != null) {
            toSave.setName(songFromRequest.name());
        } else {
            toSave.setName(songFromDatabase.getName());
        }
//        todo
//        if (songFromRequest.getArtist() != null) {
//            builder.artist(songFromRequest.getArtist());
//        } else {
//            builder.artist(songFromDatabase.getArtist());
//        }
        songUpdater.updateById(id, toSave);
        return SongDto.builder()
                .id(toSave.getId())
                .name(toSave.getName())
                .build();

    }

    public SongDto addSong(final SongDto songDto) {
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
