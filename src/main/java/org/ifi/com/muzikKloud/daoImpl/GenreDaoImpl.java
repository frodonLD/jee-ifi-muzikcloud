package org.ifi.com.muzikKloud.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ifi.com.muzikKloud.dao.GenreDao;
import org.ifi.com.muzikKloud.entity.Genre;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GenreDaoImpl implements GenreDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED) 
	public void addGenre(Genre g) throws DataAccessException{
		// TODO Auto-generated method stub
		this.entityManager.persist(g);
	}

	@Override
	public Genre getGenre(int id) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "select g from Genre g where g.id = ?";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, id);
		return (Genre) query.getSingleResult();
	}
	
	@Override
	public Genre getGenre(String name) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "select g from Genre g where g.name = ?";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, name);
		try{
			return (Genre) query.getSingleResult();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED) 
	public void updateGenre(int id, String name) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "update table genre set name = ? where id = ? ";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, name);
		query.setParameter(2, id);
		query.executeUpdate();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED) 
	public void deleteGenre(int id) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "delete from genre where id = ? ";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Genre> getAllGenres() {
		// TODO Auto-generated method stub
		Query query = this.entityManager.createNamedQuery("Genre.findAll");
		List<Genre> result = (List<Genre>) query.getResultList();
		System.out.println(result);
		return result;
	}

	

}
