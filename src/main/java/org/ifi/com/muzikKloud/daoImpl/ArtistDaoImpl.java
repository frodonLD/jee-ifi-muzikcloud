package org.ifi.com.muzikKloud.daoImpl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ifi.com.muzikKloud.dao.ArtistDao;
import org.ifi.com.muzikKloud.entity.Artist;
import org.ifi.com.muzikKloud.entity.Song;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class ArtistDaoImpl implements ArtistDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(propagation = Propagation.REQUIRED) 
	public void addArtist(Artist a) throws DataAccessException{
		// TODO Auto-generated method stub
		this.entityManager.persist(a);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED) 
	public void updateArtist(int id, String name) throws DataAccessException{
		// TODO Auto-generated method stub
		Artist atmp = this.entityManager.find(Artist.class, id);
		atmp.setName(name);
	}

	
	public Artist getArtist(int id) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "select a from Artist a where a.id = ? ";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, id);
		try{
			return (Artist) query.getSingleResult();
		}catch(Exception e){
			return null;
		}
	}
	
	
	public Artist getArtist(String name) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "select a from Artist a  where a.name = ?";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, name);
		try{
			return (Artist) query.getSingleResult();
		}catch(Exception e){
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Artist> getAllArtist() throws DataAccessException{
		// TODO Auto-generated method stub
		Query query = this.entityManager.createNamedQuery("Artist.findAll");
		List<Artist> result = (List<Artist>) query.getResultList();
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED) 
	public void deleteArtist(int id) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "delete from artist where id = ? ";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, id);
		query.executeUpdate();
	}

	@Override
	public List<Song> getAllSongOfArtist(Artist a) throws DataAccessException {
		// TODO Auto-generated method stub
		Artist atmp = this.entityManager.find(a.getClass(), a.getId());
		return atmp.getSongs();
	}

}
