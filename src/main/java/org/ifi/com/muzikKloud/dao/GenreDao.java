package org.ifi.com.muzikKloud.dao;

import java.util.List;

import org.ifi.com.muzikKloud.entity.Genre;
import org.ifi.com.muzikKloud.entity.Song;
import org.springframework.dao.DataAccessException;

public interface GenreDao {
	public void addGenre(Genre g) throws DataAccessException;
	public Genre getGenre(int id) throws DataAccessException;
	public Genre getGenre(String name) throws DataAccessException;
	public void updateGenre(int id, String name) throws DataAccessException;
	public void deleteGenre(int id) throws DataAccessException;
	public List<Genre> getAllGenres();
	public void updateGenreSongs(Genre g, Song s);
}
