package org.ifi.com.muzikKloud.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.ifi.com.muzikKloud.entity.Artist;
import org.ifi.com.muzikKloud.entity.JsonResponse;
import org.ifi.com.muzikKloud.entity.Song;
import org.ifi.com.muzikKloud.entity.SongTemp;
import org.ifi.com.muzikKloud.service.AlbumService;
import org.ifi.com.muzikKloud.service.ArtistService;
import org.ifi.com.muzikKloud.service.GenreService;
import org.ifi.com.muzikKloud.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminController {
	@Autowired
	private SongService songService;
	@Autowired
	private ArtistService artistService;
	@Autowired
	private GenreService genreService;
	@Autowired
	private AlbumService albumService;
	
	private String repUpload = "workspace/MuzikKloud/webapp/audios";
	private String filePrefix = ".."+File.separator+repUpload+File.separator;
	private ArrayList<String> listOfExtensions;
	

	/**
	 * Display welcome page and bind the 5 last songs added
	 * 
	 * @return
	 */
	@RequestMapping("/admin")
	public String admin() {
		return "admin/home";
	}

	@RequestMapping("/admin/addsongs")
	public String addSongs() {
		return "admin/addsongs";
	}
	
	/*@RequestMapping(value = "/admin/addsongs", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody JsonResponse addSongss(@RequestBody final SongTemp song) {
		for (int i = 0; i < 100; i++) {
			System.err.println("YEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEESSSSSSSSSSSSSSSSSSS");
		}
		return new JsonResponse("OK", "");
	}*/
	
	@RequestMapping(value = "/admin/addsongs", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonResponse addSongsss(@RequestBody SongTemp s) {
		for (int i = 0; i < 10; i++) {
			System.err.println("YEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEESSSSSSSSSSSSSSSSSSS");
		}
		System.err.println(s.artiste);
		return new JsonResponse("KO", "GROSSE ERREUR");
	}
	
	@RequestMapping("/admin/uploadsongs")
	public String uploadSongs() {
		return "admin/uploadsongs";
	}

	@RequestMapping(value = "/admin/uploadsongs", method = RequestMethod.POST)
	public String uploadSongs(@RequestParam("file") MultipartFile[] files, Model model) {
		listOfExtensions = new ArrayList<String>();
		listOfExtensions.add("mp3");
		listOfExtensions.add("aac");
		listOfExtensions.add("wma");
		listOfExtensions.add("m4a");
		String msg = "";
		boolean error = false;
		ArrayList<SongTemp> songTmp = multipleSave(files);
		if(!songTmp.isEmpty()){
			msg = "Upload effectué avec succès";
		} else {
			msg = "Fail lors de l'upload des fichiers";
			error = true;
		}
		model.addAttribute("songTmp", songTmp);
		model.addAttribute("error", error);
		model.addAttribute("allArtists", artistService.getAllArtists());
		model.addAttribute("allGenres", genreService.getAllGenres());
		model.addAttribute("allAlbums", albumService.getAllAlbums());
		model.addAttribute("msg", msg);
		return "admin/addsongs";
	}

	
	public ArrayList<SongTemp> multipleSave(MultipartFile[] files) {
		ArrayList<SongTemp> result = new ArrayList<SongTemp>();
		String fileName = null;
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				try {
					fileName = files[i].getOriginalFilename();
					if (!fileName.equals("")){
						String tempPath = filePrefix+fileName;
						byte[] bytes = files[i].getBytes();
						if (isValidExtension(fileName)){
							BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(tempPath)));
							buffStream.write(bytes);
							buffStream.close();
							SongTemp s = new SongTemp(fileName, 2014, fileName, fileName);
							result.add(s);
						} else {
							System.out.println("EXTENSION NON VALIDE");
						}
					} else {
						System.out.println("FICHIER VIDE!!!!!");
					}
					
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} else {
			
			System.out.println("PAS DE FICHIERS DU TOUUUT!!!!!!");
		}
		return result;
	}
	
	public boolean isValidExtension(String filename){
		String ext = FilenameUtils.getExtension(filename);
		return listOfExtensions.contains(ext);
	}
	
	/*
	public SongTemp getSongTemp(String filepath, String filename) throws SAXException, TikaException {
		try {
			File file = new File(filepath);
			InputStream input = new FileInputStream(file);
			Metadata metadata = new Metadata();
			BodyContentHandler handler = new BodyContentHandler(10 * 1024 * 1024);
			AutoDetectParser parser = new AutoDetectParser();
			parser.parse(input, handler, metadata);
			String[] metadataNames = metadata.names();
            for(String name : metadataNames){
                System.err.println(name + "==>> " + metadata.get(name));
            }
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	*/
	
}
