package org.ifi.com.muzikKloud.entity;

public class SongTemp {
	
	public String titre;
	public int date;
	public String artiste;
	public String fichier;
	public String genre;
	public String album;
	
	//Introducing the dummy constructor
    public SongTemp() {
    }
    
	public SongTemp(String titre, int date, String artist, String file){
		this.titre = titre;
		this.artiste = artist;
		this.date = date;
		this.fichier = file;
	}
	
	public SongTemp(String titre, int date, String artist, String file, String genre, String album){
		this.titre = titre;
		this.artiste = artist;
		this.date = date;
		this.fichier = file;
		this.genre = genre;
		this.album = album;
	}
	
	
	
}
