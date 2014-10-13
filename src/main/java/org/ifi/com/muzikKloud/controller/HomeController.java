package org.ifi.com.muzikKloud.controller;

import java.util.List;

import org.ifi.com.muzikKloud.entity.Song;
import org.ifi.com.muzikKloud.service.AlbumService;
import org.ifi.com.muzikKloud.service.SongService;
import org.ifi.com.muzikKloud.serviceImpl.SongServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String welcome(){
		return"home";
	}
	
	@RequestMapping("/songs")
	public String showAllsong( Model model){
		//SongService songService = new SongServiceImpl();
		List<Song> allSongs = songServiceImpl.getLastSongsAdded(0);
		System.out.println(allSongs);
		model.addAttribute("songs", allSongs );
		return"songs";
	}
	
}
