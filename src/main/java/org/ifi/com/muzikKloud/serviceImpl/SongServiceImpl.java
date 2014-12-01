package org.ifi.com.muzikKloud.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.ifi.com.muzikKloud.dao.SongDao;
import org.ifi.com.muzikKloud.entity.Album;
import org.ifi.com.muzikKloud.entity.Artist;
import org.ifi.com.muzikKloud.entity.Genre;
import org.ifi.com.muzikKloud.entity.Song;
import org.ifi.com.muzikKloud.service.AlbumService;
import org.ifi.com.muzikKloud.service.ArtistService;
import org.ifi.com.muzikKloud.service.GenreService;
import org.ifi.com.muzikKloud.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

	@Autowired
	private SongDao songDao;
	@Autowired
	private GenreService genreService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private ArtistService artistService;

	private Genre produceGenreEntity(Genre g) {
		if (!this.genreService.doesGenreExists(g))
			this.genreService.addGenre(g);
		return this.genreService.getGenre(g.getName());
	}

	private Album produceAlbumEntity(Album al) {
		if (!this.albumService.doesAllbumExist(al))
			this.albumService.addAlbum(al);
		return this.albumService.getAlbum(al.getTitre());
	}

	private Artist produceArtistEntity(Artist a) {
		if (!this.artistService.doesArtistExists(a))
			this.artistService.addArtist(a);
		return this.artistService.getArtist(a.getName());
	}

	@Override
	public boolean addSong(String titre, int dateParution, String link,
			String[] artist_names, String album_name, String[] genre_name) {
		// TODO Auto-generated method stub
		Song s = new Song();
		ArrayList<Genre> genres = new ArrayList<Genre>();
		ArrayList<Artist> artists = new ArrayList<Artist>();

		for (String a : artist_names) {
			Artist tmp = new Artist();
			tmp.setName(a.trim());
			tmp = this.produceArtistEntity(tmp);
			artists.add(tmp);
		}
		
		if(genre_name.length > 0){
			for (String g : genre_name) {
				Genre tmp = new Genre();
				tmp.setName(g.trim());
				tmp = this.produceGenreEntity(tmp);
				genres.add(tmp);
			}
			s.setGenres(genres);
		}
		
		
		if (!album_name.isEmpty()) {
			Album al = new Album();
			al.setTitre(album_name.trim());
			al = this.produceAlbumEntity(al);
			s.setAlbum(al);
		}
		
		s.setTitre(titre);
		s.setLink(link);
		s.setArtists(artists);
		s.setDateParution(dateParution);
		if(this.doesSongExist(s))
			return false;
		this.songDao.addSong(s);
		return true;

	}

	@Override
	public Song getSong(int id) {
		// TODO Auto-generated method stub
		return this.songDao.getSong(id);
	}

	@Override
	public List<Song> getLastSongsAdded(int limit) {
		// TODO Auto-generated method stub
		return this.songDao.getLastSongsAdded(limit);
	}
	
	private boolean doesSongExist(Song s) {
		// TODO Auto-generated method stub
		return this.songDao.getSong(s.getTitre(), s.getLink()) != null;
	}
}
