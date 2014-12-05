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

public class MdWrap {
	
	private String id;
	private String mdType;
	private String othermdType;
	private String mimetype;
	private XmlData xmlData;
	
	public MdWrap() {
		
	}
	
	public MdWrap(String id, String mdType) {
		this.id = id;
		this.mdType = mdType;
	}
	
	public String getID() {
		return id;
	}
	public void setID(String id) {
		this.id = id;
	}
	public String getMdType() {
		return mdType;
	}
	public void setMdType(String mdType) {
		this.mdType = mdType;
	}
	public String getOthermdType() {
		return othermdType;
	}
	public void setOthermdType(String othermdType) {
		this.othermdType = othermdType;
	}
	public String getMimetype() {
		return mimetype;
	}
	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public XmlData getXmlData() {
		return xmlData;
	}

	public void setXmlData(XmlData xmlData) {
		this.xmlData = xmlData;
	}
	
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
		SMOutputElement mdwrap = frag.addElement(METS.ns,"mdWrap");
		if(id != null)
			mdwrap.addAttribute("ID",id);
		if(mdType != null)
			mdwrap.addAttribute("MDTYPE",mdType);
		if(othermdType != null)
			mdwrap.addAttribute("OTHERMDTYPE",othermdType);
		if(mimetype != null)
			mdwrap.addAttribute("MIMETYPE",mimetype);
		if(xmlData.getContent() != null) {
			mdwrap.addElement(METS.ns,"xmlData");
			xmlData.getContent().output(writer);
		}
		frag.closeRoot();
	}
}
