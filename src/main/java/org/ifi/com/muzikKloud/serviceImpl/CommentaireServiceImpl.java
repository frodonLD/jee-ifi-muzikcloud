package org.ifi.com.muzikKloud.serviceImpl;

import java.util.Date;

import org.ifi.com.muzikKloud.dao.CommentaireDao;
import org.ifi.com.muzikKloud.entity.Commentaire;
import org.ifi.com.muzikKloud.entity.Song;
import org.ifi.com.muzikKloud.service.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentaireServiceImpl implements CommentaireService {

	@Autowired
	private CommentaireDao commentaireDao;
	

	@Override
	public Commentaire getCommentaire(int id) {
		// TODO Auto-generated method stub
		return this.commentaireDao.getCommentaire(id);
	}

	@Override
	public boolean addCommentaire(String authorname, String content, Song s,
			Date date) {
		// TODO Auto-generated method stub
		Commentaire c = new Commentaire();
		c.setAuthor(authorname);
		c.setContent(content);
		c.setDateCommentaire(date);
		c.setSong(s);
		if(!this.commentaireDao.doesSongBeCommented(c)){
			this.commentaireDao.addCommentaire(c);
			return true;
		}
		return false;
	}

}
