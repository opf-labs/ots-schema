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

public class RelatedObjectIdentification {
	
	private String type;
	private String value;
	private Integer relatedObjectSequence;
	private String relObjectXmlID;
	private SimpleLink xlink;
	
	public RelatedObjectIdentification() {	}
	
	public RelatedObjectIdentification(XMLStreamReader reader) throws NumberFormatException, XMLStreamException {
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

	public int getRelatedObjectSequence() {
		return relatedObjectSequence;
	}

	public void setRelatedObjectSequence(int relatedObjectSequence) {
		this.relatedObjectSequence = relatedObjectSequence;
	}

	public String getRelObjectXmlID() {
		return relObjectXmlID;
	}

	public void setRelObjectXmlID(String relObjectXmlID) {
		this.relObjectXmlID = relObjectXmlID;
	}

	public SimpleLink getXlink() {
		return xlink;
	}

	public void setXlink(SimpleLink xlink) {
		this.xlink = xlink;
	}

	public void output(XMLStreamWriter writer, SMOutputElement parent) throws XMLStreamException {
		SMOutputElement relObjectIdent = parent.addElement(Premis.ns,"relatedObjectIdentification");
		if(relObjectXmlID != null) {
			relObjectIdent.addAttribute("RelObjectXmlID",relObjectXmlID);
		}
		if(xlink != null) {
			xlink.output(writer);
		}
		if(type != null) {
			relObjectIdent.addElementWithCharacters(Premis.ns,"relatedObjectIdentifierType",type);
		}
		if(value != null) {
			relObjectIdent.addElementWithCharacters(Premis.ns,"relatedObjectIdentifierValue",value);
		}
		if(relatedObjectSequence != null) {
			relObjectIdent.addElementWithCharacters(Premis.ns,"relatedObjectSequence",relatedObjectSequence.toString());
		}
	}

	public RelatedObjectIdentification parse(XMLStreamReader reader) throws NumberFormatException, XMLStreamException {
		reader.require(START_ELEMENT, Premis.XMLNS, "relatedObjectIdentification");	
		if(reader.getAttributeCount() > 0) {
			xlink = new SimpleLink(reader);
			relObjectXmlID = reader.getAttributeValue(Premis.XMLNS,"RelObjectXmlID");
		}
		for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("relatedObjectIdentifierType")) {
						type = reader.getElementText();
					}
					else if(localName.equals("relatedObjectIdentifierValue")) {
						value = reader.getElementText();
					}
					else if(localName.equals("relatedObjectSequence")) {
						relatedObjectSequence = Integer.parseInt(reader.getElementText());
					}
				break;		
				case END_ELEMENT:
				//return when we're at the end of the element
				if(reader.getLocalName().equals("relatedObjectIdentification")) {
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
