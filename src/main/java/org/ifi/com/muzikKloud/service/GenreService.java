package org.ifi.com.muzikKloud.service;

import java.util.List;

import org.ifi.com.muzikKloud.entity.Genre;

public interface GenreService {
	public void addGenre(Genre g);
	public Genre getGenre(int id);
	public Genre getGenre(String name);
	public void updateGenre(int id, String name);
	public void deleteGenre(int id);
	public boolean doesGenreExists(Genre g);
	public List<Genre> getAllGenres();
}
