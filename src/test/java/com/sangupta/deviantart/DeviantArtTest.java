package com.sangupta.deviantart;

import java.util.List;

import com.sangupta.jerry.http.service.impl.DefaultHttpServiceImpl;

public class DeviantArtTest {
	
	public static void main(String[] args) {
		DeviantArtClient client = new DeviantArtClient();
		client.setHttpService(new DefaultHttpServiceImpl());
		
		List<DeviantArtImage> images = client.getImages(DeviantArtCategory.DigitalArt, DeviantArtBoost.Popular, DeviantArtSort.Time, DeviantArtType.Deviation, null);
		System.out.println(images.size());
	}

}
