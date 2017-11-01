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

package com.sangupta.deviantart.model;

import java.util.List;

public class DeviantArtImage {
	
	public String url;
	
	public String homeUrl;
	
	public String title;
	
	public String author;
	
	public String authorUrl;
	
	public String description;
	
	public String descriptionType;
	
	public long publishDate;
	
	public String copyright;
	
	public boolean nsfw;
	
	public List<String> tags;
	
	@Override
	public int hashCode() {
		if(this.url == null) {
			return 0;
		}
		
		return this.url.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(this == obj) {
			return true;
		}
		
		if(this.url == null) {
			return false;
		}
		
		if(obj instanceof DeviantArtImage) {
			DeviantArtImage other = (DeviantArtImage) obj;
			
			return this.url.equals(other.url);
		}
		
		if(obj instanceof String) {
			return this.url.equals(obj); 
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "DeviantArtImage: " + this.url;
	}
}
