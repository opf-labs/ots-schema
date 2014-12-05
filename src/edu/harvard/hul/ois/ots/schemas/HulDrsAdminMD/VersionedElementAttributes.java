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
package edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.stream.XMLStreamException;

import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD.HulDrsAdminMD.Status;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlDateFormat;

public abstract class VersionedElementAttributes {
		
	protected Status status;
	protected Date createDate;
	protected String creatingAgent;
	protected Date modDate;
	protected String modAgent;
	//2006-11-14T18:13:51.0Z
	//yyyy.MM.dd'T'HH:mm:ss.Z
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
	
	public Status getStatus() {
		return status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getCreatingAgent() {
		return creatingAgent;
	}

	public Date getModDate() {
		return modDate;
	}

	public String getModAgent() {
		return modAgent;
	}
	
	public void outputVersionedAttributes(SMOutputElement element) throws XMLStreamException {
		if(status != null) {
			element.addAttribute("status",status.toString());
		}
		if(creatingAgent != null) {
			element.addAttribute("creatingAgent",creatingAgent);
		}
		if(createDate != null) {
			element.addAttribute("createDate",XmlDateFormat.formatDateTime(createDate));
		}
		if(modAgent != null) {
			element.addAttribute("modAgent",modAgent);
		}
		if(modDate != null) {
			element.addAttribute("modDate",XmlDateFormat.formatDateTime(modDate));
		}
	}
	
}
