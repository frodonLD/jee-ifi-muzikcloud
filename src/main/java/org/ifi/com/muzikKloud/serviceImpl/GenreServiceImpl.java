package org.ifi.com.muzikKloud.serviceImpl;

import java.util.List;

import org.ifi.com.muzikKloud.dao.GenreDao;
import org.ifi.com.muzikKloud.entity.Artist;
import org.ifi.com.muzikKloud.entity.Genre;
import org.ifi.com.muzikKloud.entity.Song;
import org.ifi.com.muzikKloud.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

	@Autowired
	private GenreDao genreDao;
	
	@Override
	public void addGenre(Genre g) {
		// TODO Auto-generated method stub
		if (!this.doesGenreExists(g))
			this.genreDao.addGenre(g);
	}

	@Override
	public Genre getGenre(int id) {
		// TODO Auto-generated method stub
		return this.genreDao.getGenre(id);
	}
	
	@Override
	public Genre getGenre(String name) {
		// TODO Auto-generated method stub
		return this.genreDao.getGenre(name);
	}

	@Override
	public void updateGenre(int id, String name) {
		// TODO Auto-generated method stub
		Genre g = new Genre();
		g.setName(name);
		if (!this.doesGenreExists(g))
			this.genreDao.updateGenre(id, name);
	}

	@Override
	public void deleteGenre(int id) {
		// TODO Auto-generated method stub
		this.genreDao.deleteGenre(id);
	}

	@Override
	public boolean doesGenreExists(Genre g) {
		// TODO Auto-generated method stub
		return this.genreDao.getGenre(g.getName()) != null;
	}

	@Override
	public List<Genre> getAllGenres() {
		// TODO Auto-generated method stub
		return this.genreDao.getAllGenres();
	}
	
	@Override
	public void updateGenreSongs(Genre g, Song s) {
		// TODO Auto-generated method stub
		this.genreDao.updateGenreSongs(g, s);
	}

}
