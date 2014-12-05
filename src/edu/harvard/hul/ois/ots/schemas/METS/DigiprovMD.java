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

import java.util.Date;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlDateFormat;

public class DigiprovMD {
	
	private String id;
	private MdWrap mdwrap;
	private MdRef mdref;
	
	private Date created;
	private String status;
	
	public DigiprovMD() {
	
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public MdWrap getMdwrap() {
		return mdwrap;
	}

	public void setMdwrap(MdWrap mdwrap) {
		this.mdwrap = mdwrap;
	}

	public MdRef getMdref() {
		return mdref;
	}

	public void setMdref(MdRef mdref) {
		this.mdref = mdref;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
	
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
		SMOutputElement digiprov = frag.addElement(METS.ns,"digiprovMD");
		if(id != null) {
			digiprov.addAttribute("ID",id);
		}
		if(status != null) {
			digiprov.addAttribute("STATUS",status);
		}
		if(created != null) {
			digiprov.addAttribute("CREATED",XmlDateFormat.formatDateTime(created));
		}
		if(mdref != null) {
			mdref.output(writer);
		}
		if(mdwrap != null) {
			mdwrap.output(writer);
		}
		frag.closeRoot();
	}

}
