package org.ifi.com.muzikKloud.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ifi.com.muzikKloud.dao.SongDao;
import org.ifi.com.muzikKloud.entity.Album;
import org.ifi.com.muzikKloud.entity.Artist;
import org.ifi.com.muzikKloud.entity.Genre;
import org.ifi.com.muzikKloud.entity.Song;
import org.ifi.com.muzikKloud.service.AlbumService;
import org.ifi.com.muzikKloud.service.ArtistService;
import org.ifi.com.muzikKloud.service.CommentaireService;
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
	@Autowired
	private CommentaireService commentaireService;

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
		return this.getSong(s.getTitre(), s.getLink());
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
		System.err.println("Saving Song ---------------------");
		for (Artist a : artists) {
			System.err.println("Artist ["+a.getName()+"]");
			Artist tmp = this.produceArtistEntity(a);
			if(tmp != null){
				a = tmp;
				System.err.println("Id : "+a.getId());
			}
			s.addArtist(a);
		}
		this.songDao.addSong(s);
		s = this.produceSongEntity(s);
		System.err.println("Song Id ::: "+s.getId());
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
		Song tmp = new Song();
		Song s = new Song();
		String title = ex_titre;
		tmp.setLink(fichier);
		tmp.setTitre(ex_titre);
		if(!titre.equals(ex_titre)){
			tmp.setTitre(titre);
			title = titre;
			if(this.songDao.doesSongExist(tmp))
				return false;
		}
		tmp.setTitre(ex_titre);
		tmp = this.produceSongEntity(tmp);
		tmp.setLink(fichier);
		tmp.setTitre(title);
		int id = tmp.getId();
		s.setLink(fichier);
		s.setTitre(title);
		s.setDateParution(dateParution);
		if (!album_name.trim().isEmpty() && !album_name.trim().equals(new String(""))) {
			Album al = new Album();
			al.setTitre(album_name.trim());
			this.albumService.addAlbum(al);
			al = this.produceAlbumEntity(al);
			s.setAlbum(al);
		} else {
			s.setAlbum(null);
		}
		
		for (String a : artist_names) {
			if(!a.equals("")){
				Artist atmp = new Artist();
				atmp.setName(a.trim());
				this.artistService.addArtist(atmp);
				atmp = this.produceArtistEntity(atmp);
				s.addArtist(atmp);
			}
		}
		this.songDao.updateSong(id, s);
		return true;
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

	@Override
	public boolean commentSong(int id_song, String authorname, String content,
			Date date) {
		// TODO Auto-generated method stub
		Song s = this.songDao.getSong(id_song);
		if(s == null)
			return false;
		return this.commentaireService.addCommentaire(authorname, content, s, date);
	}
	
}
