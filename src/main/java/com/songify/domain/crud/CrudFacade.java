package com.songify.domain.crud;

import com.songify.domain.crud.dto.AddGenreToSongDto;
import com.songify.domain.crud.dto.AlbumDto;
import com.songify.domain.crud.dto.GenreDto;
import com.songify.domain.crud.dto.SongDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Transactional
public class CrudFacade {

    private final SongAdder songAdder;
    private final AlbumRetriever albumRetriever;
    private final SongRetriever songRetriever;
    private final GenreRetriever genreRetriever;
    private final AlbumUpdater albumUpdater;
    private final SongUpdater songUpdater;
    private final SongDeleter songDeleter;

    public AlbumDto findAlbumById(final long albumId) {
        return albumRetriever.findDtoById(albumId);
    }

    public GenreDto findGenreById(final long genreId) {
        return genreRetriever.findGenreDtoById(genreId);
    }

    public List<SongDto> findAllSongs(Pageable pageable) {
        return songRetriever.findAll(pageable);
    }

    public void updateSongById(Long id, SongDto newSongDto) {
        songUpdater.updateById(id, newSongDto);
    }

    public AddGenreToSongDto addGenreToSong(Long songId, Long genreId) {
        songUpdater.addGenreToSong(songId, genreId);
        return new AddGenreToSongDto(songId, genreId);
    }

    public SongDto updateSongPartiallyById(Long id, SongDto songFromRequest) {
        return songUpdater.updatePartiallyById(id, songFromRequest);
    }

    public SongDto addSong(final SongDto songDto) {
        return songAdder.addSong(songDto);
    }

    public void deleteSongById(Long id) {
        songDeleter.deleteById(id);
    }

    public SongDto findSongDtoById(Long id) {
        return songRetriever.findSongDtoById(id);
    }

    public AlbumDto addArtistToAlbum(Long artistId, Long albumId) {
        return albumUpdater.addArtistToAlbum(artistId, albumId);
    }

    public Set<AlbumDto> findAllAlbums(final Pageable pageable) {
        return albumRetriever.findAll(pageable);
    }
}
