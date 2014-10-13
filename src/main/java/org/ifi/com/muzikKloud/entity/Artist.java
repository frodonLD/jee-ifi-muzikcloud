package org.ifi.com.muzikKloud.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the artist database table.
 * 
 */
@Entity
@NamedQuery(name="Artist.findAll", query="SELECT a FROM Artist a")
public class Artist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	//bi-directional many-to-many association to Album
	@ManyToMany
	@JoinTable(
		name="rel_artist_album"
		, joinColumns={
			@JoinColumn(name="id_artist")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_album")
			}
		)
	private List<Album> albums;

	//bi-directional many-to-many association to Song
	@ManyToMany
	@JoinTable(
		name="rel_artist_song"
		, joinColumns={
			@JoinColumn(name="id_artist")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_song")
			}
		)
	private List<Song> songs;

	public Artist() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

}