package org.ifi.com.muzikKloud.dao;


import java.util.Date;
import java.util.List;

import org.ifi.com.muzikKloud.entity.Song;
import org.springframework.dao.DataAccessException;

public interface SongDao {
	public void addSong(Song s) throws DataAccessException;
	public Song getSong(int id) throws DataAccessException;
	public Song getSong(String titre, String link) throws DataAccessException;
	public void updateSong(int id, String titre) throws DataAccessException;
	public void updateSong(int id, Date date_parution) throws DataAccessException;
	public void updateSong(int id, String titre, Date date_parution) throws DataAccessException;
	public List<Song> getLastSongsAdded(int limit) throws DataAccessException;
	public void deleteSong(int id) throws DataAccessException;
	public boolean doesSongExist(Song s);
}
