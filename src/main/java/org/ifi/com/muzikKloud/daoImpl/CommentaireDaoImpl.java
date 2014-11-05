package org.ifi.com.muzikKloud.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ifi.com.muzikKloud.dao.CommentaireDao;
import org.ifi.com.muzikKloud.entity.Commentaire;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class CommentaireDaoImpl implements CommentaireDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void addCommentaire(Commentaire c) throws DataAccessException{
		// TODO Auto-generated method stub
		this.entityManager.persist(c);
	}

	@Override
	public Commentaire getCommentaire(int id) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "select c from commentaire where c.id = ?";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, id);
		return (Commentaire) query.getSingleResult();
	}

	@Override
	public Commentaire getCommentaire(String author) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "select c from commentaire where c.authorname = ?";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, author);
		return (Commentaire) query.getSingleResult();
	}

}
