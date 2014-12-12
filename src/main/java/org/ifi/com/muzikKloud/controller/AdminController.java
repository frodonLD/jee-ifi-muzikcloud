package org.ifi.com.muzikKloud.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.ifi.com.muzikKloud.entity.Album;
import org.ifi.com.muzikKloud.entity.Artist;
import org.ifi.com.muzikKloud.entity.JsonResponse;
import org.ifi.com.muzikKloud.entity.Song;
import org.ifi.com.muzikKloud.entity.SongTempInsert;
import org.ifi.com.muzikKloud.entity.SongTempUpdate;
import org.ifi.com.muzikKloud.service.AlbumService;
import org.ifi.com.muzikKloud.service.ArtistService;
import org.ifi.com.muzikKloud.service.GenreService;
import org.ifi.com.muzikKloud.service.SongService;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.TagField;
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
	private static final Logger logger = Logger
			.getLogger(AdminController.class);

	@Autowired
	private SongService songService;
	@Autowired
	private ArtistService artistService;
	@Autowired
	private GenreService genreService;
	@Autowired
	private AlbumService albumService;

	private String repUpload = "workspace/MuzikKloud/webapp/audios";
	private String filePrefix = ".." + File.separator + repUpload
			+ File.separator;
	private ArrayList<String> listOfExtensions;

	@RequestMapping("/admin")
	public String admin() {
		return "admin/home";
	}

	@RequestMapping("/admin/home")
	public String adminBis() {
		return "admin/home";
	}

	@RequestMapping(value = "/admin/addsongs", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody
	JsonResponse saveSongs(@RequestBody SongTempInsert s) {
		boolean status = songService.addSong(s.titre, s.date, s.fichier,
				s.artistes, s.albums, s.genres);
		if (!status)
			return new JsonResponse("KO", "Erreur lors de l'ajout du son "
					+ s.titre + " lié au fichier " + s.fichier
					+ " : Ce son existe déjà!");
		else
			return new JsonResponse("OK", "");
	}

	@RequestMapping(value = "/admin/applyupdate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody
	JsonResponse applyUpdate(@RequestBody SongTempUpdate s) {
		boolean status = songService.updateSong(s.titre, s.ex_titre, s.date,
				s.fichier, s.artistes, s.albums, s.genres);
		if (!status)
			return new JsonResponse("KO",
					"Erreur lors de la modification du son " + s.titre
							+ " : Ce son existe déjà!");
		else
			return new JsonResponse("OK", "");
	}
	
	@RequestMapping("/admin/updateartist")
    public String showArtistSongs(@RequestParam(value="id", required=true)int idArtist, @RequestParam(value="name", required=true) String name) {
		Artist a = artistService.getArtist(idArtist);
		if(a == null)
			return "KO";
		boolean res = this.artistService.updateArtist(idArtist, name);
		if(res)
			return "OK";
		return "KO";
	}
	
	@RequestMapping("/admin/updatealbum")
    public String showArtistAlbum(@RequestParam(value="id", required=true)int idAlbum, @RequestParam(value="name", required=true) String titre) {
		Album al = this.albumService.getAlbum(idAlbum);
		if(al == null)
			return "KO";
		boolean res = this.albumService.updateAlbum(idAlbum, titre);
		if(res)
			return "OK";
		return "KO";
	}
	
	

	@RequestMapping("/admin/uploadsongs")
	public String uploadSongs() {
		return "admin/uploadsongs";
	}

	@RequestMapping(value = "/admin/uploadsongs", method = RequestMethod.POST)
	public String uploadSongs(@RequestParam("file") MultipartFile[] files,
			Model model) {
		listOfExtensions = new ArrayList<String>();
		listOfExtensions.add("mp3");
		listOfExtensions.add("aac");
		listOfExtensions.add("wma");
		listOfExtensions.add("m4a");
		String msg = "";
		boolean error = false;
		ArrayList<SongTempInsert> songTmp = multipleSave(files);
		if (!songTmp.isEmpty()) {
			msg = "Upload effectué avec succès";
		} else {
			msg = "Fail lors de l'upload des fichiers";
			error = true;
		}
		ArrayList<Album> allAlbums = (ArrayList<Album>) albumService
				.getAllAlbums();
		ArrayList<Album> cloneAllAlbums = (ArrayList<Album>) allAlbums.clone();
		for (Album album : allAlbums) {
			if (album.getTitre().trim().isEmpty())
				cloneAllAlbums.remove(album);
		}
		model.addAttribute("songTmp", songTmp);  
		model.addAttribute("error", error);
		model.addAttribute("allArtists", artistService.getAllArtists());
		model.addAttribute("allGenres", genreService.getAllGenres());
		model.addAttribute("allAlbums", cloneAllAlbums);
		model.addAttribute("msg", msg);
		return "admin/addsongs";
	}

	public ArrayList<SongTempInsert> multipleSave(MultipartFile[] files) {
		ArrayList<SongTempInsert> result = new ArrayList<SongTempInsert>();
		String fileName = null;
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				try {
					fileName = files[i].getOriginalFilename();
					if (!fileName.equals("")) {
						String tempPath = filePrefix + fileName;
						byte[] bytes = files[i].getBytes();
						if (isValidExtension(fileName)) {
							BufferedOutputStream buffStream = new BufferedOutputStream(
									new FileOutputStream(new File(tempPath)));
							buffStream.write(bytes);
							buffStream.close();
							SongTempInsert s = new SongTempInsert(fileName,
									2014, fileName, fileName);
							result.add(s);
							if (logger.isDebugEnabled()) {
								logger.debug("Fichier ajouté " + tempPath);
							}
							File temp = new File(new File(tempPath).getAbsolutePath());
							//System.err.println();
							//tagParser(temp);
						} else {
							if (logger.isDebugEnabled()) {
								logger.debug("Extension non valide pour le fichier "
										+ fileName);
							}
						}
					}

				} catch (Exception e) {
					System.out.println(e);
					logger.error(
							"Erreur lors de la copie d'un ou plusieurs fichiers ==>"
									+ e.getMessage(), e);
				}
			}
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Aucun fichier détecté lors de l'upload");
			}
		}
		return result;
	}

	public boolean isValidExtension(String filename) {
		String ext = FilenameUtils.getExtension(filename);
		return listOfExtensions.contains(ext);
	}

	public static Map<String, String> tagParser(File file) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            Iterator<TagField> it = AudioFileIO.read(file).getTag().getFields();
            while (it.hasNext())
            {
                TagField tagField = it.next();
                System.err.println("TAGGG ===> "+tagField.getId()+" ||| Content ===> "+tagField.getRawContent().toString());
                switch (tagField.getId()) {
                    case "ALBUMARTIST":
                        map.put("artist", tagField.toString());
                        break;
                    case "ALBUM":
                        map.put("release", tagField.toString());
                        break;
                    case "TITLE":
                        map.put("track", tagField.toString());
                        break;
                    default:
                        break;
                }
            }
            map.put("path", file.getAbsolutePath());
            System.out.println("Artist: " + map.get("artist") + ",Release: " + map.get("release") + ", Track: " + map.get("track") + ",Path: " + map.get("path"));
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
