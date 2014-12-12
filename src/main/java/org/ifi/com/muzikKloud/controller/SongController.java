package org.ifi.com.muzikKloud.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import org.ifi.com.muzikKloud.entity.Album;
import org.ifi.com.muzikKloud.entity.Artist;
import org.ifi.com.muzikKloud.entity.CommentTemp;
import org.ifi.com.muzikKloud.entity.Commentaire;
import org.ifi.com.muzikKloud.entity.JsonResponse;
import org.ifi.com.muzikKloud.entity.Song;
import org.ifi.com.muzikKloud.entity.SongTempUpdate;
import org.ifi.com.muzikKloud.service.AlbumService;
import org.ifi.com.muzikKloud.service.ArtistService;
import org.ifi.com.muzikKloud.service.GenreService;
import org.ifi.com.muzikKloud.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;


@Controller
public class SongController {
	@Autowired
	private SongService songService;
	@Autowired
	private ArtistService artistService;
	@Autowired
	private GenreService genreService;
	@Autowired
	private AlbumService albumService;
	
	private static final Logger logger = Logger.getLogger(AdminController.class);
	
	@RequestMapping("/songs")
	public String showAllsong(Model model){
		List<Song> allSongs = songService.getLastSongsAdded(0);
		model.addAttribute("songs", allSongs );
		return "songs";
	}
	
	
	@RequestMapping("/song")
    public String showSong(@RequestParam(value="id", required=true)int idSong, Model model) {
		Song s = songService.getSong(idSong);
		int note = 0, i = 0, temp = 0;
		model.addAttribute("song", s );
		Set<Artist> st = new HashSet<>();
		st.addAll(s.getArtists());
		model.addAttribute("Artists", st.toArray());
		List<Commentaire> commentaires = s.getCommentaires();
		for (Commentaire c : commentaires) {
			i++;
			temp = Integer.parseInt(c.getContent());
			note += temp;
		}
		if(i > 0){
			note = note/i;
		}
		model.addAttribute("nbAvis", i);
		model.addAttribute("note", note);
		model.addAttribute("gris", 5 - note);
		return "song";
	}
	
	@RequestMapping("/admin/addsongs")
	public String addSongs() {
		return "admin/addsongs";
	}
	
	@RequestMapping("/admin/songs")
	public String showAllsongInAdmin(Model model){
		List<Song> songs = songService.getLastSongsAdded(0);
		Artist[] art = {};
		for (Song s : songs) {
			Set<Artist> st = new HashSet<>();
			st.addAll(s.getArtists());
			ArrayList<Artist> artists = new ArrayList<Artist>(Arrays.asList((Artist[])st.toArray(art)));
			s.setArtists(artists);
		}
		model.addAttribute("songs", songs );
		return "admin/songs";
	}
	
	
	@RequestMapping("/admin/song")
    public String showSongInAdmin(@RequestParam(value="id", required=true)int idSong, Model model) {
		Song s = songService.getSong(idSong);
		model.addAttribute("song", s );
		Set<Artist> st = new HashSet<>();
		st.addAll(s.getArtists());
		model.addAttribute("Artists", st.toArray());
		return "admin/song";
	}
	
	@RequestMapping("/admin/updatesong")
	public String updateSong(@RequestParam(value="id", required=true)int idSong, Model model){
		boolean error = true;
		Song s = songService.getSong(idSong);
		if(s != null){
			error = false;
			model.addAttribute("s", s);
			model.addAttribute("error", error);
			model.addAttribute("allArtists", artistService.getAllArtists());
			model.addAttribute("allAlbums", albumService.getAllAlbums());
			return "admin/updatesong";
		}
		return "404";
	}
	
	
	@RequestMapping("/admin/removesong")
	public String removeSong(@RequestParam(value="id", required=true)int idSong, Model model){
		boolean error = !this.songService.removeSong(idSong);
		model.addAttribute("error", error);
		return "redirect:/admin/songs";
	}
	
	@RequestMapping("/artists")
	public String showAllArtists(Model model){
		model.addAttribute("allArtists", artistService.getAllArtists());
		return "artists";
	} 
	
	@RequestMapping("/artist")
    public String showArtistSongs(@RequestParam(value="id", required=true)int idArtist, Model model) {
		Artist a = artistService.getArtist(idArtist);
		if(a != null){
			model.addAttribute("a", a);
			model.addAttribute("songs", this.artistService.getAllSongOfArtist(a));
			return "artistSong";
		}
		return "404";
		
	}
	
	@RequestMapping("/admin/artists")
	public String showAllArtistsInAdmin(Model model){
		model.addAttribute("allArtists", artistService.getAllArtists());
		return "admin/artists";
	} 
	
	
	@RequestMapping("/albums")
	public String showAllAlbums(Model model){
		List<Album> allAlbums = albumService.getAllAlbums();
		model.addAttribute("allAlbums", allAlbums );
		return "albums";
	} 
	
	@RequestMapping("/admin/albums")
	public String showAllAlbumsInAdmin(Model model){
		List<Album> allAlbums = albumService.getAllAlbums();
		model.addAttribute("allAlbums", allAlbums );
		return "admin/albums";
	} 
	
	@RequestMapping("/album")
    public String showAlbumSongs(@RequestParam(value="id", required=true)int idAlbum, Model model) {
		Album al = albumService.getAlbum(idAlbum);
		if(al != null){
			model.addAttribute("al", al);
			return "albumSong";
		}
		return "404";
	}
	
	@RequestMapping(value = "/commentsong", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody
	JsonResponse insertComment(@RequestBody CommentTemp c) {
		boolean status = songService.commentSong(c.idSong, c.author, c.comment, Calendar.getInstance().getTime());
		if (!status)
			return new JsonResponse("KO", "Erreur lors de la création du commentaire");
		else
			return new JsonResponse("OK", "");
	}

}
