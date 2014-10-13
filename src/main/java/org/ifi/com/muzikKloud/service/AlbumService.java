package org.ifi.com.muzikKloud.service;

import org.ifi.com.muzikKloud.entity.Album;

public interface AlbumService {
	public void addAlbum(Album al);
	public Album getAlbum(int id);
	public void updateAlbum(int id, String titre);
	public void deleteAlbum(int id);
}
