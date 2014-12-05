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
package edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD.FITS;

import java.util.ArrayList;
import java.util.List;

public class Identity {
	private String format;
	private String mimetype;
	private List<Tool> tools = new ArrayList<Tool>();
	private List<Version> versions = new ArrayList<Version>();
	private List<ExternalIdentifier> externalIdentifiers = new ArrayList<ExternalIdentifier>();
	
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getMimetype() {
		return mimetype;
	}
	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}
	public List<Tool> getTools() {
		return tools;
	}
	public void setTools(List<Tool> tools) {
		this.tools = tools;
	}
	public void addTool(Tool tool) {
		tools.add(tool);
	}
	public List<Version> getVersions() {
		return versions;
	}
	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}
	public void addVersion(Version version) {
		versions.add(version);
	}
	public List<ExternalIdentifier> getExternalIdentifiers() {
		return externalIdentifiers;
	}
	public void setExternalIdentifiers(List<ExternalIdentifier> externalIdentifiers) {
		this.externalIdentifiers = externalIdentifiers;
	}
	public void addExternalIdentifier(ExternalIdentifier xID) {
		externalIdentifiers.add(xID);
	}
}