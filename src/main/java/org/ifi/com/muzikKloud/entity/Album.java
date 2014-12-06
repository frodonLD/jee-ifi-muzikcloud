package org.ifi.com.muzikKloud.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the album database table.
 * 
 */
@Entity
@Table(name="album")
@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String titre;
	private List<Song> songs;

	public Album() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(nullable=false, length=200)
	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}


	//bi-directional many-to-one association to Song
	@OneToMany(mappedBy="album", fetch=FetchType.EAGER)
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