package org.ifi.com.muzikKloud.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.ifi.com.muzikKloud.entity.Song;
import org.ifi.com.muzikKloud.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;


@Controller
public class SongController {
	@Autowired
	private SongService songService;
	
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
}
