package org.ifi.com.muzikKloud.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the album database table.
 * 
 */
@Entity
@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String titre;

	//bi-directional many-to-many association to Artist
	@ManyToMany(mappedBy="albums")
	private List<Artist> artists;

	//bi-directional many-to-one association to Song
	@OneToMany(mappedBy="album")
	private List<Song> songs;

	public Album() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public List<Artist> getArtists() {
		return this.artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public Song addSong(Song song) {
		getSongs().add(song);
		song.setAlbum(this);

		return song;
	}

	public Song removeSong(Song song) {
		getSongs().remove(song);
		song.setAlbum(null);

		return song;
	}

}