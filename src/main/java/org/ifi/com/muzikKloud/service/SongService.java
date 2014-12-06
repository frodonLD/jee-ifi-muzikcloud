package org.ifi.com.muzikKloud.service;

import java.util.List;

import org.ifi.com.muzikKloud.entity.Song;

public interface SongService {
	/*
	public void addSong(String titre, Date dateParution, String link, List<String> artist_names, String album_name, String genre_name);
	public void addSong(String titre, Date dateParution, String link, String album_name, List<Integer> ids_artist, String genre_name);
	public void addSong(String titre, Date dateParution, String link, List<Integer>ids_artist, int id_album, int id_genre);
	public void updateSong(int id, String titre);
	public void updateSong(Date dateParution);
	public void updateSong(int id, String titre, Date dateParution);
	public List<Commentaire> getCommentairesOf(int id_song);
	public List<Song> getSongsWithTitleLike(String word);
	public List<Song> getSongsWithArtistLike(String name);
	public List<Song> getSongsOf(int id_artist);
	public List<Song> getSongsOfAlbum(int id_alb);
	public List<Song> getSongsOfGenre(int id_genre);
	*/
	
	public boolean addSong(String titre, int dateParution, String link, String[] artist_names, String album_name, String[] genres);
	public Song getSong(int id);
	public Song getSong(String name, String link);
	public List<Song> getLastSongsAdded(int limit);
	public boolean removeSong(int songId);
	//public void commentSong(int id_song, String authorname, String content);
	boolean updateSong(String titre, String ex_titre, int dateParution,
			String fichier, String[] artist_names, String album_name,
			String[] genres);
}
