package com.sangupta.deviantart;

public enum DeviantArtSort {
	
	Time ("time");
	
	private final String key;
	
	private DeviantArtSort(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return this.key;
	}

}
