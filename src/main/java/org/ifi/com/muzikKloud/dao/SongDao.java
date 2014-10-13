package org.ifi.com.muzikKloud.dao;


import java.util.Date;
import java.util.List;

import org.ifi.com.muzikKloud.entity.Song;

public interface SongDao {
	public void addSong(Song s);
	public Song getSong(int id);
	public void updateSong(int id, String titre);
	public void updateSong(int id, Date date_parution);
	public void updateSong(int id, String titre, Date date_parution);
	public List<Song> getLastSongsAdded(int limit);
	public void deleteSong(int id);
}
