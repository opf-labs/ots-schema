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

import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.SimpleLink;
import edu.harvard.hul.ois.ots.schemas.premis.Premis;

public class RelatedEventIdentification {
	
	private String type;
	private String value;
	private Integer relatedEventSequence;
	private String relEventXmlID;
	private SimpleLink xlink;
	
	public RelatedEventIdentification() {	}
	
	public RelatedEventIdentification(XMLStreamReader reader) throws NumberFormatException, XMLStreamException {
		parse(reader);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getRelatedEventSequence() {
		return relatedEventSequence;
	}

	public void setRelatedEventSequence(int relatedEventSequence) {
		this.relatedEventSequence = relatedEventSequence;
	}

	public String Event() {
		return relEventXmlID;
	}

	public void setRelEventXmlID(String relEventXmlID) {
		this.relEventXmlID = relEventXmlID;
	}

	public SimpleLink getXlink() {
		return xlink;
	}

	public void setXlink(SimpleLink xlink) {
		this.xlink = xlink;
	}

	public void output(XMLStreamWriter writer, SMOutputElement parent) throws XMLStreamException {
		SMOutputElement relEventIdent = parent.addElement(Premis.ns,"relatedEventIdentification");
		if(relEventXmlID != null) {
			relEventIdent.addAttribute("RelEventXmlID",relEventXmlID);
		}
		if(xlink != null) {
			xlink.output(writer);
		}
		if(type != null) {
			relEventIdent.addElementWithCharacters(Premis.ns,"relatedEventIdentifierType",type);
		}
		if(value != null) {
			relEventIdent.addElementWithCharacters(Premis.ns,"relatedEventIdentifierValue",value);
		}
		if(relatedEventSequence != null) {
			relEventIdent.addElementWithCharacters(Premis.ns,"relatedEventSequence",relatedEventSequence.toString());
		}
	}

	public RelatedEventIdentification parse(XMLStreamReader reader) throws NumberFormatException, XMLStreamException {
		reader.require(START_ELEMENT, Premis.XMLNS, "relatedEventIdentification");	
		if(reader.getAttributeCount() > 0) {
			xlink = new SimpleLink(reader);
			relEventXmlID = reader.getAttributeValue(Premis.XMLNS,"RelEventXmlID");
		}
		for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("relatedEventIdentifierType")) {
						type = reader.getElementText();
					}
					else if(localName.equals("relatedEventIdentifierValue")) {
						value = reader.getElementText();
					}
					else if(localName.equals("relatedEventSequence")) {
						relatedEventSequence = new Integer(reader.getElementText());			
					}
				break;		
				case END_ELEMENT:
				//return when we're at the end of the element
				if(reader.getLocalName().equals("relatedEventIdentification")) {
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
