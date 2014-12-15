package org.ifi.com.muzikKloud.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the commentaire database table.
 * 
 */
@Entity
@Table(name="commentaire")
@NamedQuery(name="Commentaire.findAll", query="SELECT c FROM Commentaire c")
public class Commentaire implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String author;
	private String content;
	private Date dateCommentaire;
	private Song song;

	public Commentaire() {
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
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	@Lob
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="date_commentaire", nullable=false)
	public Date getDateCommentaire() {
		return this.dateCommentaire;
	}

	public void setDateCommentaire(Date dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}


	//bi-directional many-to-one association to Song
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_song", nullable=false)
	public Song getSong() {
		return this.song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

}