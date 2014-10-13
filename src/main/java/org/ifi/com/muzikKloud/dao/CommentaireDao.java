package org.ifi.com.muzikKloud.dao;

import org.ifi.com.muzikKloud.entity.Commentaire;

public interface CommentaireDao {
	public void addCommentaire(Commentaire c);
	public Commentaire getCommentaire(int id);
	public Commentaire getCommentaire(String author);
}
