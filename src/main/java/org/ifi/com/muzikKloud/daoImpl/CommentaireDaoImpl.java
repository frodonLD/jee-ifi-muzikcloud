package org.ifi.com.muzikKloud.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ifi.com.muzikKloud.dao.CommentaireDao;
import org.ifi.com.muzikKloud.entity.Commentaire;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CommentaireDaoImpl implements CommentaireDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED) 
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
		try{
			return (Commentaire) query.getSingleResult();
		}catch(Exception e){
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean doesSongBeCommented(Commentaire c)
			throws DataAccessException {
		// TODO Auto-generated method stub
		String req = "from Commentaire c where c.author = ?";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, c.getAuthor());
		@SuppressWarnings("unchecked")
		List<Commentaire> result = (List<Commentaire>) query.getResultList();
		int i = 0;
		boolean vire = false;
		for (Commentaire commentaire : result) {
			if(c.getSong().getId() == commentaire.getSong().getId()){
				vire = true;
			}
//			if(c.getDateCommentaire().getDate() == commentaire.getDateCommentaire().getDate() &&
//					c.getDateCommentaire().getMonth() == commentaire.getDateCommentaire().getMonth() &&
//					c.getDateCommentaire().getYear() == commentaire.getDateCommentaire().getYear())
//				i++;
		}
		if(vire)
			return true;
		
//		if(result.isEmpty() || i < 3)
//			return true;
		return false;
	}
	

}
