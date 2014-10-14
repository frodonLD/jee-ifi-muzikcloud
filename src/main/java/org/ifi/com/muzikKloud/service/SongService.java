package org.ifi.com.muzikKloud.service;

import java.util.Date;
import java.util.List;

import org.ifi.com.muzikKloud.entity.Song;

public interface SongService {
	public void addSong(String titre, Date dateParution, String link, List<String> artist_names, String album_name, String genre_name);
	/*public void addSong(String titre, Date dateParution, String link, List<String> artist_names, String album_name, int id_genre);
	public void addSong(String titre, Date dateParution, String link, String album_name, List<Integer> ids_artist, String genre_name);
	public void addSong(String titre, Date dateParution, String link, List<Integer>ids_artist, int id_album, int id_genre);
	public void updateSong(int id, String titre);
	public void updateSong(Date dateParution);
	public void updateSong(int id, String titre, Date dateParution);
	public Song getSong(int id);
	public List<Commentaire> getCommentairesOf(int id_song);
	public List<Song> getSongsWithTitleLike(String word);
	public List<Song> getSongsWithArtistLike(String name);
	public List<Song> getSongsOf(int id_artist);
	public List<Song> getSongsOfAlbum(int id_alb);
	public List<Song> getSongsOfGenre(int id_genre);*/
	public List<Song> getLastSongsAdded(int limit);
	//public void commentSong(int id_song, String authorname, String content);
}
