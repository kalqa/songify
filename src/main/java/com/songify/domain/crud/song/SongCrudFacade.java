package com.songify.domain.crud.song;

import com.songify.domain.crud.album.AlbumCrudFacade;
import com.songify.domain.crud.genre.GenreCrudFacade;
import com.songify.domain.crud.song.dto.SongDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@Service
@Transactional
public class SongCrudFacade {

    private final SongRetriever songRetriever;
    private final SongUpdater songUpdater;
    private final SongDeleter songDeleter;
    private final SongAdder songAdder;
    private final AlbumCrudFacade albumCrudFacade;
    private final GenreCrudFacade genreCrudFacade;

    public List<SongDto> findAll(Pageable pageable) {
        return songRetriever.findAll(pageable)
                .stream()
                .map(song -> SongDto.builder()
                        .id(song.getId())
                        .name(song.getName())
                        .albumId(song.getAlbum().getId())
                        .genreId(song.getGenre().getId())
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
        Long albumId = albumCrudFacade.findAlbumById(songDto.albumId()).getId();
        Long genreId = genreCrudFacade.findGenreById(songDto.genreId()).getId();
        // some domain validator
        String name = songDto.name();
        Song vaidatedAndReadytoSaveSong = new Song(name);
        // some domain validator ended checking
        Song addedSong = songAdder.addSong(vaidatedAndReadytoSaveSong, albumId, genreId);
        return SongDto.builder()
                .id(addedSong.getId())
                .name(addedSong.getName())
//                .genreId(addedSong.getGenre().getId())
                .albumId(addedSong.getAlbum().getId())
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
