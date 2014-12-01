package org.ifi.com.muzikKloud.service;

import java.util.List;

import org.ifi.com.muzikKloud.entity.Album;

public interface AlbumService {
	public void addAlbum(Album al);
	public Album getAlbum(int id);
	public Album getAlbum(String titre);
	public void updateAlbum(int id, String titre);
	public void deleteAlbum(int id);
	public List<Album> getAllAlbums();
	public boolean doesAllbumExist(Album a);
}
