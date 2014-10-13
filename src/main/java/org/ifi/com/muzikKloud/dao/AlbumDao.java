package org.ifi.com.muzikKloud.dao;



import org.ifi.com.muzikKloud.entity.Album;

public interface AlbumDao {
	public void addAlbum(Album a);
	public Album getAlbum(int id);
	public void updateAlbum(int id, String titre);
	public void deleteAlbum(int id);
}
