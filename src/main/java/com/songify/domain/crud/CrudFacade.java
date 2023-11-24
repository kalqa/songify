package com.songify.domain.crud;

import com.songify.domain.crud.dto.AlbumDto;
import com.songify.domain.crud.dto.GenreDto;
import com.songify.domain.crud.dto.SongDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class CrudFacade {

    private final AlbumCrudFacade albumCrudFacade;
    private final GenreCrudFacade genreCrudFacade;
    private final SongCrudFacade songCrudFacade;

    public AlbumDto findAlbumById(final long albumId) {
        return albumCrudFacade.findDtoById(albumId);
    }

    public GenreDto findGenreById(final long genreId) {
        return genreCrudFacade.findGenreById(genreId);
    }

    public List<SongDto> findAllSongs(Pageable pageable) {
        return songCrudFacade.findAll(pageable);
    }

    public void updateSongById(Long id, SongDto newSongDto) {
        songCrudFacade.updateById(id, newSongDto);
    }

    public SongDto updateSongPartiallyById(Long id, SongDto songFromRequest) {
        return songCrudFacade.updatePartiallyById(id, songFromRequest);
    }

    public SongDto addSong(final SongDto songDto) {
        return songCrudFacade.addSong(songDto);
    }

    public void deleteSongById(Long id) {
        songCrudFacade.deleteById(id);
    }

    public SongDto findSongDtoById(Long id) {
        return songCrudFacade.findSongDtoById(id);
    }

    public AlbumDto addArtistToAlbum(Long artistId, Long albumId){
        return albumCrudFacade.addArtistToAlbum(artistId, albumId);
    }

    public Set<AlbumDto> findAllAlbums(final Pageable pageable) {
        return albumCrudFacade.findAll(pageable);
    }
}
