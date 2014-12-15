package org.ifi.com.muzikKloud.controller;

import org.ifi.com.muzikKloud.service.ArtistService;
import org.ifi.com.muzikKloud.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {
	@Autowired
	private SongService songService;
	@Autowired
	private ArtistService artistService;

	/**
	 * Display welcome page
	 * @return
	 */
	@RequestMapping("/")
	public String welcome(){
		return "home";
	}    
	
	/**
	 * Display welcome page
	 * @return
	 */
	@RequestMapping("/home")
	public String welcomeBis(){
		return this.welcome();
	}  
	
	/**
	 * Display login page
	 * @return
	 */
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	/**
	 * Display techno page
	 * @return
	 */
	@RequestMapping(value="/technologies", method = RequestMethod.POST)
	public String techno(){
		return "techno";
	}
	
	/**
	 * Display 403 page
	 * @return
	 */
	@RequestMapping("/403")
	public String notAllowed(){
		return "403";
	}
	
	/**
	 * Display 404 page
	 * @return
	 */
	@RequestMapping("/404")
	public String notFound(){
		return "404";
	}
}
