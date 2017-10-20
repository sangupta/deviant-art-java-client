package com.sangupta.deviantart;

public enum DeviantArtType {
	
	Deviation ("deviation");
	
	private final String key;
	
	private DeviantArtType(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return this.key;
	}

}
