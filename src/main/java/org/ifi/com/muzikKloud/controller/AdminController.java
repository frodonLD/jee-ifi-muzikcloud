package org.ifi.com.muzikKloud.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.ifi.com.muzikKloud.entity.Artist;
import org.ifi.com.muzikKloud.entity.Song;
import org.ifi.com.muzikKloud.service.ArtistService;
import org.ifi.com.muzikKloud.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminController {
	@Autowired
	private SongService songService;

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

	@RequestMapping(value = "/admin/addsongs", method = RequestMethod.POST)
	public String importParse(@RequestParam("file") MultipartFile[] files, Model model) {
		// ... do whatever you want with 'myFile'
		// Redirect to a successful upload page
		String msg = multipleSave(files);
		model.addAttribute("msg", msg);
		System.out.println("You call me WITH a POST!!!");
		return "admin/addsongs";
	}

	public String multipleSave(MultipartFile[] files) {
		String fileName = null;
		String msg = "";
		 
		
		String rep = "workspace/MuzikKloud/webapp/audios";
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				String basePath = new File("").getAbsolutePath();
				System.out.println("BASE ====> "+basePath);
				try {
					fileName = files[i].getOriginalFilename();
					String tempPath = ".."+File.separator+rep+File.separator+fileName;
					System.out.println("CHEMIn ====> "+tempPath);
					byte[] bytes = files[i].getBytes();
					if (isValidExtension(fileName)){
						BufferedOutputStream buffStream = new BufferedOutputStream(
								new FileOutputStream(new File(tempPath)));
						buffStream.write(bytes);
						buffStream.close();
						msg += "You have successfully uploaded " + fileName
								+ "\n";
					} else{
						msg =  "You failed to upload " + fileName + ": Aucun fichier audio détecté. Vérifiez le format\n";
					}
				} catch (Exception e) {
					msg =  "You failed to upload " + fileName + ": "
							+ e.getMessage() + "\n";
				}
			}
			
		} else {
			msg = "Unable to upload. File is empty.";
		}
		return msg;
	}
	
	public boolean isValidExtension(String filename){
		ArrayList<String> listOfExtensions = new ArrayList<String>();
		listOfExtensions.add("mp3");
		listOfExtensions.add("aac");
		listOfExtensions.add("wma");
		String ext = FilenameUtils.getExtension(filename);
		return listOfExtensions.contains(ext);
	}

}
