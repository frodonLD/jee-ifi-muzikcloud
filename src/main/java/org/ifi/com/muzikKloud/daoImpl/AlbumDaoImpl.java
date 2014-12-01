package org.ifi.com.muzikKloud.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ifi.com.muzikKloud.dao.AlbumDao;
import org.ifi.com.muzikKloud.entity.Album;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AlbumDaoImpl implements AlbumDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addAlbum(Album a) throws DataAccessException {
		// TODO Auto-generated method stub
		this.entityManager.persist(a);

	}

	@Override
	public Album getAlbum(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		String req = "select a from album where a.id = ?";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, id);
		return (Album) query.getSingleResult();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateAlbum(int id, String titre) throws DataAccessException {
		// TODO Auto-generated method stub
		String req = "update table album set titre = ? where id = ? ";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, titre);
		query.setParameter(2, id);
		query.executeUpdate();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAlbum(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		String req = "delete from album where id = ? ";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Album> getAllAlbums() {
		// TODO Auto-generated method stub
		Query query = this.entityManager.createNamedQuery("Album.findAll");
		List<Album> result = (List<Album>) query.getResultList();
		return result;
	}

	@Override
	public Album getAlbum(String titre) throws DataAccessException {
		// TODO Auto-generated method stub
		String req = "select al from Album al where al.titre = ?";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, titre);
		try{
			return (Album) query.getSingleResult();
		}catch(Exception e){
			return null;
		}
	}

}
