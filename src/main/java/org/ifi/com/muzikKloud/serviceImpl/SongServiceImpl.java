package org.ifi.com.muzikKloud.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ifi.com.muzikKloud.dao.SongDao;
import org.ifi.com.muzikKloud.entity.Album;
import org.ifi.com.muzikKloud.entity.Artist;
import org.ifi.com.muzikKloud.entity.Genre;
import org.ifi.com.muzikKloud.entity.Song;
import org.ifi.com.muzikKloud.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

	@Autowired
	private SongDao songDao;
	
	@Override
	public void addSong(String titre, Date dateParution, String link,
			List<String> artist_names, String album_name, String genre_name) {
		// TODO Auto-generated method stub
		List<Artist> artists = new ArrayList<Artist>();
		List<Genre> genres = new ArrayList<Genre>();
		
		Song s = new Song();
		
		Album al = new Album();
		al.setTitre(album_name);
		
		Genre g = new Genre();
		g.setName(genre_name);
		
		genres.add(g);
		
		for (String name : artist_names) {
			Artist temp = new Artist();
			temp.setName(name);
			artists.add(temp);
		}
		
		s.setArtists(artists);
		s.setDateParution(dateParution);
		s.setLink(link);
		s.setTitre(titre);
		s.setGenres(genres);
		
		this.songDao.addSong(s);
		
	}

	@Override
	public List<Song> getLastSongsAdded(int limit) {
		// TODO Auto-generated method stub
		System.out.println("SERVICE");
		this.songDao.getLastSongsAdded(limit);
		return null;
	}

}
