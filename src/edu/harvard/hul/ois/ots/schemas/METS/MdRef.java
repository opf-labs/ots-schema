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

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

import edu.harvard.hul.ois.ots.schemas.XmlContent.SimpleLink;

public class MdRef {
	
	private String id;
	private String label;
	private String loctype;
	private String otherloctype;
	private String mdtype;
	private String othermdtype;
	private String mimetype;
	private SimpleLink xlink;

	public MdRef() {
		
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public String getMdtype() {
		return mdtype;
	}

	public void setMdtype(String mdtype) {
		this.mdtype = mdtype;
	}

	public String getOthermdtype() {
		return othermdtype;
	}

	public void setOthermdtype(String othermdtype) {
		this.othermdtype = othermdtype;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public SimpleLink getXlink() {
		return xlink;
	}

	public void setXlink(SimpleLink xlink) {
		this.xlink = xlink;
	}
	
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
		SMOutputElement mdref = frag.addElement(METS.ns,"mdRef");
		if(id != null) {
			mdref.addAttribute("ID",id);
		}
		if(label != null) {
			mdref.addAttribute("LABEL",label);
		}
		if(loctype != null) {
			mdref.addAttribute("LOCTYPE",loctype);
		}
		if(otherloctype != null) {
			mdref.addAttribute("OTHERLOCTYPE",otherloctype);
		}
		if(mdtype != null) {
			mdref.addAttribute("MDTYPE",mdtype);
		}
		if(othermdtype != null) {
			mdref.addAttribute("OTHERMDTYPE",othermdtype);
		}
		if(mimetype != null) {
			mdref.addAttribute("MIMETYPE",mimetype);
		}	
		if(xlink != null) {
			xlink.output(writer);
		}
		frag.closeRoot();
	}
	
}
