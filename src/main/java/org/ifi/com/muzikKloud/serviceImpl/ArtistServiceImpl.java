package org.ifi.com.muzikKloud.serviceImpl;

import java.util.List;

import org.ifi.com.muzikKloud.dao.ArtistDao;
import org.ifi.com.muzikKloud.entity.Artist;
import org.ifi.com.muzikKloud.entity.Song;
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
	public List<Artist> getAllArtists() {
		// TODO Auto-generated method stub
		return artistDao.getAllArtist();
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

	@Override
	public List<Song> getAllSongOfArtist(Artist a) {
		// TODO Auto-generated method stub
		return this.artistDao.getAllSongOfArtist(a);
	}

	@Override
	public boolean updateArtist(int id, String name) {
		// TODO Auto-generated method stub
		Artist a = new Artist();
		a.setName(name);
		a = this.artistDao.getArtist(a.getName());
		if(a != null)
			return false;
		a = new Artist();
		a.setName(name);
		this.artistDao.updateArtist(id, name);
		return true;
	}
	
}
