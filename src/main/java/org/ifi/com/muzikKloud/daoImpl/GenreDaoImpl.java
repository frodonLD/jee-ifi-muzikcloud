package org.ifi.com.muzikKloud.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ifi.com.muzikKloud.dao.GenreDao;
import org.ifi.com.muzikKloud.entity.Genre;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class GenreDaoImpl implements GenreDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void addGenre(Genre g) throws DataAccessException{
		// TODO Auto-generated method stub
		this.entityManager.persist(g);
	}

	@Override
	public Genre getGenre(int id) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "select g from genre where g.id = ?";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, id);
		return (Genre) query.getSingleResult();
	}
	
	@Override
	public Genre getGenre(String name) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "select g from genre where g.name = ?";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, name);
		return (Genre) query.getSingleResult();
	}

	@Override
	public void updateGenre(int id, String name) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "update table genre set name = ? where id = ? ";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, name);
		query.setParameter(2, id);
		query.executeUpdate();
	}

	@Override
	public void deleteGenre(int id) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "delete from genre where id = ? ";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, id);
		query.executeUpdate();
	}

	

}
