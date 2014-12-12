package org.ifi.com.muzikKloud.daoImpl;


import java.util.ArrayList;
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
			this.entityManager.merge(s);
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
	public void updateSong(int id, Song s) throws DataAccessException{
		// TODO Auto-generated method stub
		Song tmp = this.getSong(id);
		tmp.setTitre(s.getTitre());
		tmp.setDateParution(s.getDateParution());
		tmp.setAlbum(s.getAlbum());
		List<Artist> nouveaux = s.getArtists();
		List<Artist> lisTmp = new ArrayList<Artist>();
		for(Artist a : nouveaux){
			Artist atmp = this.entityManager.find(a.getClass(), a.getId());
			lisTmp.add(atmp);
		}
		tmp.setArtists(lisTmp);
	}
	
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
