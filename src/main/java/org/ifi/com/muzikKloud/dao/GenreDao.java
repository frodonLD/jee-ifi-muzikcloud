package org.ifi.com.muzikKloud.dao;

import org.ifi.com.muzikKloud.entity.Genre;

public interface GenreDao {
	public void addGenre(Genre g);
	public Genre getGenre(int id);
	public Genre getGenre(String name);
	public void updateGenre(int id, String name);
	public void deleteGenre(int id);
}
