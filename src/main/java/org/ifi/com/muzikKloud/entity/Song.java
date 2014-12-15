package org.ifi.com.muzikKloud.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the song database table.
 * 
 */
@Entity
@Table(name="song")
@NamedQuery(name="Song.findAll", query="SELECT s FROM Song s")
public class Song implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int dateParution;
	private String link;
	private String titre;
	private List<Artist> artists = new ArrayList<Artist>();
	private List<Commentaire> commentaires = new ArrayList<Commentaire>();
	private List<Genre> genres = new ArrayList<Genre>();
	private Album album;

	public Song() {
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


	@Column(name="date_parution")
	public int getDateParution() {
		return this.dateParution;
	}

	public void setDateParution(int dateParution) {
		this.dateParution = dateParution;
	}


	@Column(nullable=false, length=255)
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}


	@Column(nullable=false, length=200)
	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

/*
	//bi-directional many-to-many association to Artist
	//@ManyToMany(mappedBy="songs", fetch=FetchType.EAGER)
//	@ManyToMany(mappedBy="songs", fetch=FetchType.EAGER, cascade = {javax.persistence.CascadeType.ALL})
//	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@ManyToMany(mappedBy="songs", fetch=FetchType.EAGER, cascade = {javax.persistence.CascadeType.PERSIST})
	@Cascade(org.hibernate.annotations.CascadeType.PERSIST)
//	@ManyToMany(mappedBy="songs", fetch=FetchType.EAGER, cascade = {javax.persistence.CascadeType.PERSIST})
//	@Cascade(org.hibernate.annotations.CascadeType.PERSIST)*/
	//bi-directional many-to-many association to Song
//	@ManyToMany(fetch=FetchType.EAGER, cascade = {javax.persistence.CascadeType.PERSIST})
//	@Cascade(org.hibernate.annotations.CascadeType.PERSIST)
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = {javax.persistence.CascadeType.MERGE})
	@Cascade(org.hibernate.annotations.CascadeType.MERGE)
	@JoinTable(
		name="rel_artist_song"
		, joinColumns={
			@JoinColumn(name="id_song", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_artist", nullable=false)
			}
		)
	public List<Artist> getArtists() {
		return this.artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}


	//bi-directional many-to-one association to Commentaire
	@OneToMany(mappedBy="song", fetch=FetchType.EAGER)
	public List<Commentaire> getCommentaires() {
		return this.commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public Commentaire addCommentaire(Commentaire commentaire) {
		getCommentaires().add(commentaire);
		commentaire.setSong(this);

		return commentaire;
	}

	public Commentaire removeCommentaire(Commentaire commentaire) {
		getCommentaires().remove(commentaire);
		commentaire.setSong(null);

		return commentaire;
	}


	//bi-directional many-to-many association to Genre
	@ManyToMany(mappedBy="songs", fetch=FetchType.EAGER)
	public List<Genre> getGenres() {
		return this.genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}


	//bi-directional many-to-one association to Album
	@ManyToOne
	@JoinColumn(name="id_album", nullable=false)
	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	public void addArtist(Artist a) {
		getArtists().add(a);
        a.getSongs().add(this);
    }
 
    public void removeArtist(Artist a) {
    	getArtists().remove(a);
        a.getSongs().remove(this);
    }
    
    public void addGenre(Genre g) {
		getGenres().add(g);
        g.getSongs().add(this);
    }
 
    public void removeGenre(Genre g) {
    	getGenres().remove(g);
        g.getSongs().remove(this);
    }

}