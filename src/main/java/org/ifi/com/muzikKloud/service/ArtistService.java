package org.ifi.com.muzikKloud.service;

import java.util.List;

import org.ifi.com.muzikKloud.entity.Artist;
import org.ifi.com.muzikKloud.entity.Song;
import org.springframework.dao.DataAccessException;

public interface ArtistService {
	public void addArtist(Artist a);
	public Artist getArtist(int id);
	public Artist getArtist(String name);
	public boolean updateArtist(int id, String name);
	public List<Artist> getAllArtists();
	public List<Song> getAllSongOfArtist(Artist a);
	public void deleteArtist(int id);
	public boolean doesArtistExists(Artist a);
}
