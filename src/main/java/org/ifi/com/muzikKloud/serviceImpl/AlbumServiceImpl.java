package org.ifi.com.muzikKloud.serviceImpl;


import org.ifi.com.muzikKloud.dao.AlbumDao;
import org.ifi.com.muzikKloud.entity.Album;
import org.ifi.com.muzikKloud.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {
    
	@Autowired
	private AlbumDao albumDao;
	
	@Override
	public void addAlbum(Album al) {
		this.albumDao.addAlbum(al);
	}

	@Override
	public Album getAlbum(int id) {
		// TODO Auto-generated method stub
		return this.albumDao.getAlbum(id);
	}

	@Override
	public void updateAlbum(int id, String titre) {
		// TODO Auto-generated method stub
		this.albumDao.updateAlbum(id, titre);
	}

	@Override
	public void deleteAlbum(int id) {
		// TODO Auto-generated method stub
		this.albumDao.deleteAlbum(id);
	}
}
