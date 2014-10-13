package org.ifi.com.muzikKloud.serviceImpl;

import org.ifi.com.muzikKloud.dao.ArtistDao;
import org.ifi.com.muzikKloud.entity.Artist;
import org.ifi.com.muzikKloud.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {
	
	@Autowired
	private ArtistDao artistDao;
	
	@Override
	public void addArtist(Artist a) {
		// TODO Auto-generated method stub
		if(!this.doesArtistExists(a))
			this.artistDao.addArtist(a);
	}

	@Override
	public Artist getArtist(int id) {
		// TODO Auto-generated method stub
		return this.artistDao.getArtist(id);
	}
	
	@Override
	public Artist getArtist(String name) {
		// TODO Auto-generated method stub
		return this.artistDao.getArtist(name);
	}
	
	@Override
	public void updateArtist(int id, String name) {
		// TODO Auto-generated method stub
		Artist a = new Artist();
		a.setName(name);
		if(!this.doesArtistExists(a))
			this.artistDao.updateArtist(id, name);
	}

	@Override
	public void deleteArtist(int id) {
		// TODO Auto-generated method stub
		this.artistDao.deleteArtist(id);
	}

	@Override
	public boolean doesArtistExists(Artist a) {
		// TODO Auto-generated method stub
		return this.artistDao.getArtist(a.getName()) != null;
	}
	
}
