package org.ifi.com.muzikKloud.daoImpl;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ifi.com.muzikKloud.dao.SongDao;
import org.ifi.com.muzikKloud.entity.Artist;
import org.ifi.com.muzikKloud.entity.Song;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SongDaoImpl implements SongDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED) 
	public void addSong(Song s) throws DataAccessException{
		// TODO Auto-generated method stub
		if(!this.doesSongExist(s))
			this.entityManager.persist(s);
	}

	@Override
	public Song getSong(int id) throws DataAccessException{
		String req = "select s from Song s where s.id = ?";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, id);
		try{
			return (Song) query.getSingleResult();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED) 
	public void updateSong(int id, String titre) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "update table song set titre = ? where id = ? ";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, titre);
		query.setParameter(2, id);
		query.executeUpdate();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED) 
	public void updateSong(int id, Date date_parution) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "update table song set date_parution = ? where id = ? ";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, date_parution);
		query.setParameter(2, id);
		query.executeUpdate();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED) 
	public void updateSong(int id, String titre, Date date_parution) throws DataAccessException{
		// TODO Auto-generated method stub
		this.updateSong(id, date_parution);
		this.updateSong(id, titre);
	}

	/*@Override
	@Transactional(propagation = Propagation.REQUIRED) 
	public void deleteSong(int id) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "delete from song where id = ? ";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, id);
		query.executeUpdate();
	}
	*/
	@Override
	@Transactional(propagation = Propagation.REQUIRED) 
	public void deleteSong(int id) throws DataAccessException{
		Song temp = this.getSong(id);
		this.entityManager.remove(temp);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Song> getLastSongsAdded(int limit) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "from Song";
		Query query = this.entityManager.createQuery(req);
		if (limit >= 0)
			query.setMaxResults(limit);
		List<Song> temp = query.getResultList();
		return temp;
	}

	@Override
	public Song getSong(String titre, String link) throws DataAccessException {
		String req = "select s from Song s where s.titre = ? and s.link = ?";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, titre);
		query.setParameter(2, link);
		try{
			return (Song) query.getSingleResult();
		}catch(Exception e){
			return null;
		}
	}
	
	public boolean doesSongExist(Song s) {
		// TODO Auto-generated method stub
		return this.getSong(s.getTitre(), s.getLink()) != null;
	}

}
