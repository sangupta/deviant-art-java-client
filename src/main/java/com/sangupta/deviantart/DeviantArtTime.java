package com.sangupta.deviantart;

public enum DeviantArtTime {
	
	Hours24("24h"),
	
	Hours8("8h"),
	
	Days3("27h"),
	
	Week("168h"),
	
	Month("744h"),
	
	AllTime("");
	
	private final String key;
	
	private DeviantArtTime(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return this.key;
	}

}
