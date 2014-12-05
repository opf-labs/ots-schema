/* 
 * Copyright 2010 Harvard University Library
 * 
 * This file is part of OTS-Schemas.
 * 
 * OTS-Schemas is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * OTS-Schemas is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with OTS-Schemas.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.harvard.hul.ois.ots.schemas.TextMD;

import java.util.ArrayList;
import java.util.List;

public class Encoding {
	
	private String quality;
	private List<EncodingAgent> encodingAgents = new ArrayList<EncodingAgent>();;
	private List<EncodingSoftware> encodingSoftwares = new ArrayList<EncodingSoftware>();;
	private List<EncodingPlatform> encodingPlatforms = new ArrayList<EncodingPlatform>();;
	
	public Encoding() {	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public List<EncodingAgent> getEncodingAgents() {
		return encodingAgents;
	}
	
	public void addEncodingAgent(EncodingAgent agent) {
		encodingAgents.add(agent);
	}

	public void setEncodingAgents(List<EncodingAgent> encodingAgents) {
		this.encodingAgents = encodingAgents;
	}

	public List<EncodingSoftware> getEncodingSoftwares() {
		return encodingSoftwares;
	}
	
	public void addEncodingSoftwares(EncodingSoftware software) {
		encodingSoftwares.add(software);
	}

	public void setEncodingSoftwares(List<EncodingSoftware> encodingSoftwares) {
		this.encodingSoftwares = encodingSoftwares;
	}

	public List<EncodingPlatform> getEncodingPlatforms() {
		return encodingPlatforms;
	}
	
	public void addEncodingPlatforms(EncodingPlatform platform) {
		encodingPlatforms.add(platform);
	}

	public void setEncodingPlatforms(List<EncodingPlatform> encodingPlatforms) {
		this.encodingPlatforms = encodingPlatforms;
	}
}
