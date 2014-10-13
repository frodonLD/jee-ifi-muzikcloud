package org.ifi.com.muzikKloud.daoImpl;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ifi.com.muzikKloud.dao.ArtistDao;
import org.ifi.com.muzikKloud.entity.Artist;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistDaoImpl implements ArtistDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addArtist(Artist a) {
		// TODO Auto-generated method stub
		this.entityManager.persist(a);
	}

	@Override
	public void updateArtist(int id, String name) {
		// TODO Auto-generated method stub
		String req = "update table artist set name = ? where id = ? ";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, name);
		query.setParameter(2, id);
		query.executeUpdate();
	}

	
	public Artist getArtist(int id) {
		// TODO Auto-generated method stub
		String req = "select a from artist  where a.id = ? ";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, id);
		return (Artist) query.getSingleResult();
	}
	
	@Override
	public Artist getArtist(String name) {
		// TODO Auto-generated method stub
		String req = "select a from artist  where a.name = ? ";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, name);
		return (Artist) query.getSingleResult();
	}

	@Override
	public void deleteArtist(int id) {
		// TODO Auto-generated method stub
		String req = "delete from artist where id = ? ";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, id);
		query.executeUpdate();
	}

}
