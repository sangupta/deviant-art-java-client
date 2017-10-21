/**
 *
 * deviant-art-java-client: Java Client for DeviantArt.com API
 * Copyright (c) 2017, Sandeep Gupta
 * 
 * https://sangupta.com/projects/deviant-art-java-client
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.sangupta.deviantart;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.sangupta.deviantart.model.DeviantArtImage;
import com.sangupta.jerry.http.WebResponse;
import com.sangupta.jerry.http.service.HttpService;
import com.sangupta.jerry.util.DomUtils;
import com.sangupta.jerry.util.UriUtils;

public class DeviantArtClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DeviantArtClient.class);
	
	private HttpService httpService;
	
	public List<DeviantArtImage> getImages(DeviantArtCategory category, DeviantArtBoost boost, DeviantArtSort sort, DeviantArtType type, DeviantArtTime duration) {
		if(type == null) {
			type = DeviantArtType.Deviation;
		}
		
		// let's build the RSS url
		StringBuilder query = new StringBuilder();
		if(sort != null) {
			query.append("+sort:");
			query.append(sort.getKey());
		}
		
		if(category != null) {
			query.append("+in:");
			query.append(category.getKey());

			// TODO: support sub-categories
		}
		
		// the final URL
		String url = "https://backend.deviantart.com/rss.xml?q=" + UriUtils.encodeURIComponent(query.toString()) + "&type=" + type.getKey();
		
		// hit it
		WebResponse response = this.httpService.doGET(url);
		if(response == null) {
			LOGGER.warn("Webresponse is null for URL: {}", url);
			return null;
		}
		
		if(!response.isSuccess()) {
			LOGGER.warn("Non-success response for for URL: {} with trace: {}", url, response.trace());
			return null;
		}
		
		// parse this response - and send back a list of all images
		SyndFeed feed = null;
		try {
			feed = new SyndFeedInput().build(new StringReader(response.getContent()));
		} catch (IllegalArgumentException e) {
			LOGGER.error("Unable to read RSS feed from given url: " + url, e);
			return null;
		} catch (FeedException e) {
			LOGGER.error("Unable to read RSS feed from given url: " + url, e);
			return null;
		}
		
		// unable to parse feed?
		if(feed == null) {
			return null;
		}
		
		List<DeviantArtImage> images = new ArrayList<DeviantArtImage>();
		
		// feed parsed - extract information
		for(SyndEntry entry : feed.getEntries()) {
			final List<Element> elements = (List<Element>) entry.getForeignMarkup();
			
			final String absoluteURL = DomUtils.getTagAttribute("content", "url", elements);
			DeviantArtImage image = new DeviantArtImage();
			
			image.url = absoluteURL;
			image.homeUrl = entry.getLink();
			image.title = entry.getTitle();
			if(entry.getDescription() != null) {
				image.descriptionType = entry.getDescription().getType();
				image.description = entry.getDescription().getValue();
			}
			image.publishDate = entry.getPublishedDate() != null ? entry.getPublishedDate().getTime() : 0;
			
			List<String> authors = DomUtils.getTagValues("credit", "role", "author", elements);
			String authorName = null;
			String authorURL = null;
			for(String value : authors) {
				if(value.startsWith("http://")) {
					authorURL = value;
				} else {
					authorName = value;
				}
			}
			image.author = authorName;
			image.authorUrl = authorURL;
			
			image.copyright= DomUtils.getTagValues("copyright", elements).get(0);
			
			images.add(image);
		}
		
		return images;
	}
	
	// Usual accessors follow

	/**
	 * @return the httpService
	 */
	public HttpService getHttpService() {
		return httpService;
	}

	/**
	 * @param httpService the httpService to set
	 */
	public void setHttpService(HttpService httpService) {
		this.httpService = httpService;
	}

}
