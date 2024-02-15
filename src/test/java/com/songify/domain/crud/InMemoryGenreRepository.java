package com.songify.domain.crud;

class InMemoryGenreRepository implements GenreRepository {
    @Override
    public int deleteById(final Long id) {
        return 0;
    }

    @Override
    public Genre save(final Genre genre) {
        return null;
    }
}
