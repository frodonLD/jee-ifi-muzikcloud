package org.ifi.com.muzikKloud.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.log4j.Logger;
import org.ifi.com.muzikKloud.entity.Album;
import org.ifi.com.muzikKloud.entity.Song;
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
		model.addAttribute("song", s );
		return "song";
	}
	
	@RequestMapping("/admin/addsongs")
	public String addSongs() {
		return "admin/addsongs";
	}
	
	@RequestMapping("/admin/songs")
	public String showAllsongInAdmin(Model model){
		List<Song> songs = songService.getLastSongsAdded(0);
		model.addAttribute("songs", songs );
		return "admin/songs";
	}
	
	
	@RequestMapping("/admin/song")
    public String showSongInAdmin(@RequestParam(value="id", required=true)int idSong, Model model) {
		Song s = songService.getSong(idSong);
		model.addAttribute("song", s );
		return "admin/song";
	}
	
	@RequestMapping("/admin/updatesong")
	public String updateSong(@RequestParam(value="id", required=true)int idSong, Model model){
		boolean error = true;
		Song s = songService.getSong(idSong);
		if(s != null){
			error = false;
			model.addAttribute("s", s);
			ArrayList<Album> allAlbums = (ArrayList<Album>) albumService.getAllAlbums();
			model.addAttribute("error", error);
			model.addAttribute("allArtists", artistService.getAllArtists());
			model.addAttribute("allAlbums", allAlbums);
			return "admin/updatesong";
		}
		return "404";
	}
	
	
	@RequestMapping("/admin/removesong")
	public String removeSong(@RequestParam(value="id", required=true)int idSong, Model model){
		boolean error = true;
		error = !this.songService.removeSong(idSong);
		model.addAttribute("error", error);
		return "redirect:/admin/songs";
	}
}
