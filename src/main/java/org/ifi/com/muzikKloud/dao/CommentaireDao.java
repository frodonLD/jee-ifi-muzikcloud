package org.ifi.com.muzikKloud.dao;

import org.ifi.com.muzikKloud.entity.Commentaire;
import org.springframework.dao.DataAccessException;

public interface CommentaireDao {
	public void addCommentaire(Commentaire c) throws DataAccessException;
	public Commentaire getCommentaire(int id) throws DataAccessException;
	public Commentaire getCommentaire(String author) throws DataAccessException;
}
