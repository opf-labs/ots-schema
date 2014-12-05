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
package edu.harvard.hul.ois.ots.schemas.METS;

import java.util.ArrayList;

import edu.harvard.hul.ois.ots.schemas.XmlContent.SimpleLink;

public class FileFLocat {

	protected String fileID;
	protected String flocatID;
	protected String loctype;
	protected String otherloctype;
	protected SimpleLink xlink;
	protected ArrayList<String> admID;
	protected String mime;
		
	public FileFLocat() {
		admID = new ArrayList<String>();
	}
	
	public String getFileID() {
		return fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}

	public String getLoctype() {
		return loctype;
	}

	public void setLoctype(String loctype) {
		this.loctype = loctype;
	}

	public String getOtherloctype() {
		return otherloctype;
	}

	public void setOtherloctype(String otherloctype) {
		this.otherloctype = otherloctype;
	}

	public SimpleLink getXlink() {
		return xlink;
	}

	public void setXlink(SimpleLink xlink) {
		this.xlink = xlink;
	}

	public ArrayList<String> getAdmID() {
		return admID;
	}

	public void setAdmID(ArrayList<String> admID) {
		this.admID = admID;
	}
	
	public void addAdmID(String id) {
		admID.add(id);
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getFlocatID() {
		return flocatID;
	}

	public void setFlocatID(String flocatID) {
		this.flocatID = flocatID;
	}
	
}
