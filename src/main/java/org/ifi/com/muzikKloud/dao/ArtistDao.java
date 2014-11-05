package org.ifi.com.muzikKloud.dao;

import java.util.List;

import org.ifi.com.muzikKloud.entity.Artist;
import org.springframework.dao.DataAccessException;

public interface ArtistDao {
	public void addArtist(Artist a) throws DataAccessException;
	public Artist getArtist(int id) throws DataAccessException;
	public Artist getArtist(String name) throws DataAccessException;
	public List<Artist> getAllArtist() throws DataAccessException;
	public void updateArtist(int id, String name) throws DataAccessException;
	public void deleteArtist(int id) throws DataAccessException;
}
