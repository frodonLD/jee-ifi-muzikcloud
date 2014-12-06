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
		return this.genreService.getGenre(g.getName());
	}

	private Album produceAlbumEntity(Album al) {
		return this.albumService.getAlbum(al.getTitre());
	}

	private Artist produceArtistEntity(Artist a) {
		return this.artistService.getArtist(a.getName());
	}
	
	private Song produceSongEntity(Song s) {
		Song tmp = this.songDao.getSong(s.getTitre(), s.getLink());
		if(tmp == null){
			this.songDao.addSong(s);
			return this.getSong(s.getTitre(), s.getLink());
		}
		return tmp;
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
			System.err.println("Artiste ::: "+a);
			this.artistService.addArtist(tmp);
			tmp = this.produceArtistEntity(tmp);
			System.err.println("Id ::: "+tmp.getId());
			artists.add(tmp);
		}
		
		/*for (String g : genre_name) {
			Genre tmp = new Genre();
			tmp.setName(g.trim());
			this.genreService.addGenre(tmp);
			tmp = this.produceGenreEntity(tmp);
			genres.add(tmp);
		}*/
		
		if (!album_name.trim().isEmpty() && !album_name.trim().equals(new String(""))) {
			Album al = new Album();
			al.setTitre(album_name.trim());
			this.albumService.addAlbum(al);
			al = this.produceAlbumEntity(al);
			s.setAlbum(al);
		}
		s.setTitre(titre);
		s.setLink(link);
		s.setDateParution(dateParution);
		if(this.songDao.doesSongExist(s))
			return false;
		for (Artist a : artists) {
			System.err.println("Saving Song ---------------------");
			System.err.println("Artist ["+a.getName()+"]");
			System.err.println("Id : "+a.getId());
			s = this.produceSongEntity(s);
			System.err.println("Song Id ::: "+s.getId());
			this.artistService.updateArtistSongs(a, s);
		}
		
		/*for (Genre g : genres) {
			System.err.println("Genre ["+g.getName()+"]");
			System.err.println("["+g.getId()+"]");
			s = this.produceSongEntity(s);
			System.err.println("Song Id ::: "+s.getId());
			this.genreService.updateGenreSongs(g, s);
		}*/
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
	


	@Override
	public boolean updateSong(String titre, String ex_titre, int dateParution, String fichier,
			String[] artist_names, String album_name, String[] genres) {
		Song s = new Song();
		s.setLink(fichier);
		s.setTitre(ex_titre);
		s = this.produceSongEntity(s);
		
		if(!titre.equals(ex_titre)){
			s.setTitre(titre);
		}
		
		if(s.getAlbum()!= null && !s.getAlbum().getTitre().equals(album_name)){
			if (!album_name.trim().isEmpty() && !album_name.trim().equals(new String(""))) {
				Album al = new Album();
				al.setTitre(album_name.trim());
				this.albumService.addAlbum(al);
				al = this.produceAlbumEntity(al);
				s.setAlbum(al);
			} else {
				s.setAlbum(null);
			}
		}
		List<Artist> artists =  s.getArtists();
		for (Artist art : artists){
			boolean found = false;
			for (String a : artist_names) {
				if(art.getName().trim().equals(a.trim()))
					found = true;
			}
			if(!found){
				this.artistService.removeArtistSongs(art, s);
			}
		}
			
		
		for (String a : artist_names) {
			Artist tmp = new Artist();
			tmp.setName(a.trim());
			this.artistService.addArtist(tmp);
			tmp = this.produceArtistEntity(tmp);
			System.err.println("Id ::: "+tmp.getId());
			this.artistService.updateArtistSongs(tmp, s);
		}
		
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeSong(int songId) {
		// TODO Auto-generated method stub
		Song temp = this.songDao.getSong(songId);
		if(temp == null)
			return false;
		this.songDao.deleteSong(songId);
		return true;
	}

	@Override
	public Song getSong(String name, String link) {
		// TODO Auto-generated method stub
		return this.songDao.getSong(name, link);
	}
	
}
