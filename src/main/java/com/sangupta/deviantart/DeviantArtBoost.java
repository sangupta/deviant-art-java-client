package com.sangupta.deviantart;

public enum DeviantArtBoost {
	
	Popular ("popular"),

	Hot ("hot"),
	
	Unsung ("unsung");
	
	private final String key;
	
	private DeviantArtBoost(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return this.key;
	}

}
