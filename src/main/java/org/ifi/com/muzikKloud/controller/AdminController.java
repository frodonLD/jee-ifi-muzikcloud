package org.ifi.com.muzikKloud.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.ifi.com.muzikKloud.entity.Album;
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
	private static final Logger logger = Logger.getLogger(AdminController.class);
	
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
	

	@RequestMapping("/admin")
	public String admin() {
		return "admin/home";
	}

	@RequestMapping("/admin/addsongs")
	public String addSongs() {
		return "admin/addsongs";
	}
	
	
	@RequestMapping(value = "/admin/addsongs", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody JsonResponse addSongsss(@RequestBody SongTemp s) {
		boolean status = songService.addSong(s.titre, s.date, s.fichier, s.artistes, s.albums, s.genres);
		if(!status)
			return new JsonResponse("KO", "Erreur lors de l'ajout du son "+s.titre+" lié au fichier "+s.fichier+" : Ce son existe déjà!");
		else 
			return new JsonResponse("OK", "");
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
		ArrayList<Album> allAlbums = (ArrayList<Album>) albumService.getAllAlbums();
		ArrayList<Album> cloneAllAlbums = (ArrayList<Album>) allAlbums.clone();
		for (Album album : allAlbums) {
			if(album.getTitre().trim().isEmpty())
				cloneAllAlbums.remove(album);
		}
		System.err.println(cloneAllAlbums);
		model.addAttribute("songTmp", songTmp);
		model.addAttribute("error", error);
		model.addAttribute("allArtists", artistService.getAllArtists());
		model.addAttribute("allGenres", genreService.getAllGenres());
		model.addAttribute("allAlbums", cloneAllAlbums);
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
							if(logger.isDebugEnabled()){
								logger.debug("Fichier ajouté "+tempPath);
							}
						} else {
							if(logger.isDebugEnabled()){
								logger.debug("Extension non valide pour le fichier "+fileName);
							}
						}
					}
					
				} catch (Exception e) {
					System.out.println(e);
					logger.error("Erreur lors de la copie d'un ou plusieurs fichiers ==>"+e.getMessage(), e);
				}
			}
		} else {
			if(logger.isDebugEnabled()){
				logger.debug("Aucun fichier détecté lors de l'upload");
			}
		}
		return result;
	}
	
	public boolean isValidExtension(String filename){
		String ext = FilenameUtils.getExtension(filename);
		return listOfExtensions.contains(ext);
	}
	
}
