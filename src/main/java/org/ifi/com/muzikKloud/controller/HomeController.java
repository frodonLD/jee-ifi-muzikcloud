package org.ifi.com.muzikKloud.controller;

import java.util.List;

import org.ifi.com.muzikKloud.entity.Artist;
import org.ifi.com.muzikKloud.entity.Song;
import org.ifi.com.muzikKloud.service.ArtistService;
import org.ifi.com.muzikKloud.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	@Autowired
	private SongService songService;
	@Autowired
	private ArtistService artistService;
	private int limit = 2;

	/**
	 * Display welcome page and bind the 5 last songs added
	 * @return
	 */
	@RequestMapping("/")
	public String welcome(Model model){
		List<Song> allSongs = songService.getLastSongsAdded(limit);
		//System.out.println(allSongs);
		model.addAttribute("lastSongs", allSongs );
		return "home";
	}    
	
	@RequestMapping("/home")
	public String welcomeBis(Model model){
		List<Song> allSongs = songService.getLastSongsAdded(limit);
		//System.out.println(allSongs);
		model.addAttribute("lastSongs", allSongs );
		return "home";
	}  
	
	@RequestMapping("/artists")
	public String showAllArtists(Model model){
		List<Artist> allArtists = artistService.getAllArtists();
		System.out.println(allArtists);
		model.addAttribute("allArtists", allArtists );
		return "artists";
	} 
	
}
