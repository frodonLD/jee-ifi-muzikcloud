package org.ifi.com.muzikKloud.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ifi.com.muzikKloud.dao.AlbumDao;
import org.ifi.com.muzikKloud.entity.Album;
import org.ifi.com.muzikKloud.entity.Artist;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumDaoImpl implements AlbumDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
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
	public void updateAlbum(int id, String titre) throws DataAccessException {
		// TODO Auto-generated method stub
		String req = "update table album set titre = ? where id = ? ";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, titre);
		query.setParameter(2, id);
		query.executeUpdate();
	}

	@Override
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

}
