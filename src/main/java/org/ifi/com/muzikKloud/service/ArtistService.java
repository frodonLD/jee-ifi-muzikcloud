package org.ifi.com.muzikKloud.service;

import java.util.List;

import org.ifi.com.muzikKloud.entity.Artist;

public interface ArtistService {
	public void addArtist(Artist a);
	public Artist getArtist(int id);
	public Artist getArtist(String name);
	public List<Artist> getAllArtists();
	public void updateArtist(int id, String name);
	public void deleteArtist(int id);
	public boolean doesArtistExists(Artist a);
}
