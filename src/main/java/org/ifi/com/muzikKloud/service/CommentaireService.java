package org.ifi.com.muzikKloud.service;

import org.ifi.com.muzikKloud.entity.Commentaire;
import org.ifi.com.muzikKloud.entity.Song;

public interface CommentaireService {
	public void addCommentaire(String authorname, String content, Song s);
	public Commentaire getCommentaire(int id);
}
