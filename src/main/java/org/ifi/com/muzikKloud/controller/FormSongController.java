package org.ifi.com.muzikKloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.ifi.com.muzikKloud.entity.Artist;
import org.ifi.com.muzikKloud.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/addsongs")
public class FormSongController {
	@Autowired
	private ArtistService artistService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String addSong(Model model){
		model.addAttribute("artist", new Artist() );
		return "admin/addSongs";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String addSong(Artist a, BindingResult result){
		artistService.addArtist(a);
		return "redirect:/artists";
	}

	
	
}
