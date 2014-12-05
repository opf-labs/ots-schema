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
package edu.harvard.hul.ois.ots.schemas.premis.object;

import static javax.xml.stream.XMLStreamConstants.END_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;


import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

import edu.harvard.hul.ois.ots.schemas.XmlContent.SimpleLink;
import edu.harvard.hul.ois.ots.schemas.premis.Premis;

public class Storage {
	
	private String contentLocationType;
	private String contentLocationValue;
	private SimpleLink contentLocationXlink;
	private String storageMedium;
	
	public Storage() { }
	
	public Storage(XMLStreamReader reader) throws XMLStreamException {
		parse(reader);
	}
	
	public String getContentLocationType() {
		return contentLocationType;
	}

	public void setContentLocationType(String contentLocationType) {
		this.contentLocationType = contentLocationType;
	}

	public String getContentLocationValue() {
		return contentLocationValue;
	}

	public void setContentLocationValue(String contentLocationValue) {
		this.contentLocationValue = contentLocationValue;
	}

	public SimpleLink getContentLocationXlink() {
		return contentLocationXlink;
	}

	public void setContentLocationXlink(SimpleLink contentLocationXlink) {
		this.contentLocationXlink = contentLocationXlink;
	}

	public String getStorageMedium() {
		return storageMedium;
	}

	public void setStorageMedium(String storageMedium) {
		this.storageMedium = storageMedium;
	}
	
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
		SMOutputElement storage = frag.addElement(Premis.ns,"storage");	
		if(contentLocationXlink != null || contentLocationType != null || contentLocationValue != null) {
			SMOutputElement contentLocation = storage.addElement(Premis.ns,"contentLocation");	
			if(contentLocationXlink != null) {
				contentLocationXlink.output(writer);
			}
			if(contentLocationType != null) {
				contentLocation.addElementWithCharacters(Premis.ns, "contentLocationType",contentLocationType);
			}
			if(contentLocationValue != null) {
				contentLocation.addElementWithCharacters(Premis.ns, "contentLocationValue",contentLocationValue);
			}
		}
		if(storageMedium != null)  {
			storage.addElementWithCharacters(Premis.ns,"storageMedium",storageMedium);
		}
		frag.closeRoot();
	}

	public Storage parse(XMLStreamReader reader) throws XMLStreamException {
		reader.require(START_ELEMENT, Premis.XMLNS, "storage");	
		if(reader.getAttributeCount() > 0) {
			contentLocationXlink = new SimpleLink(reader);
		}
		for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("contentLocationType")) {
						contentLocationType = reader.getElementText();
					}
					else if(localName.equals("contentLocationValue")) {
						contentLocationValue = reader.getElementText();
					}
					else if(localName.equals("storageMedium")) {
						storageMedium = reader.getElementText();
					}
				break;		
				case END_ELEMENT:
				//return when we're at the end of the element
				if(reader.getLocalName().equals("storage")) {
					return this;
				}
			}
		}
		return this;
	}

	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

}
