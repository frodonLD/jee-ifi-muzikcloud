package org.ifi.com.muzikKloud.dao;

import org.ifi.com.muzikKloud.entity.Artist;

public interface ArtistDao {
	public void addArtist(Artist a);
	public Artist getArtist(int id);
	public Artist getArtist(String name);
	public void updateArtist(int id, String name);
	public void deleteArtist(int id);
}
