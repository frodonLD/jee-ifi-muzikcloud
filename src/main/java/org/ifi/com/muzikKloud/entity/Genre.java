package org.ifi.com.muzikKloud.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the genre database table.
 * 
 */
@Entity
@Table(name="genre")
@NamedQuery(name="Genre.findAll", query="SELECT g FROM Genre g")
public class Genre implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private List<Song> songs = new ArrayList<Song>();

	public Genre() {
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


	@Column(nullable=false, length=30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-many association to Song
//	@ManyToMany(fetch=FetchType.EAGER, cascade = {javax.persistence.CascadeType.PERSIST})
//	@Cascade(org.hibernate.annotations.CascadeType.PERSIST)
	@ManyToMany(fetch=FetchType.EAGER, cascade = {javax.persistence.CascadeType.ALL})
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@JoinTable(
		name="rel_song_genre"
		, joinColumns={
			@JoinColumn(name="id_genre", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_song", nullable=false)
			}
		)
	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
	public void addSong(Song s) {
		getSongs().add(s);
        s.getGenres().add(this);
    }
 
    public void removeSong(Song s) {
        getSongs().remove(s);
        s.getGenres().remove(this);
    } 

}