package org.ifi.com.muzikKloud.service;

import java.util.Date;

import org.ifi.com.muzikKloud.entity.Commentaire;
import org.ifi.com.muzikKloud.entity.Song;

public interface CommentaireService {
	public boolean addCommentaire(String authorname, String content, Song s, Date date);
	public Commentaire getCommentaire(int id);
}
