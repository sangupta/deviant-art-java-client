package com.sangupta.deviantart;

public enum DeviantArtCategory {
	
	DigitalArt ("digitalart"),
	
	TraditionalArt (""),
	
	Photography (""),
	
	ArtisanCrafts (""),
	
	Literature (""),
	
	FilmAndAnimation (""),
	
	MotionBooks (""),
	
	Flash (""),
	
	DesignsAndInterfaces (""),
	
	Customization (""),
	
	CartoonsAndComics (""),
	
	MangaAndAnime (""),
	
	Anthro (""),
	
	FanArt ("");
	
	private final String key;
	
	private DeviantArtCategory(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return this.key;
	}

}
