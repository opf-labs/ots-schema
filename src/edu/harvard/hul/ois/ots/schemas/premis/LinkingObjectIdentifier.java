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
package edu.harvard.hul.ois.ots.schemas.premis;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.SimpleLink;

public class LinkingObjectIdentifier {
	
	private String type;
	private String value;
	private List<String> roles;
	private String linkObjectXmlID;
	private SimpleLink xlink;
	
	public LinkingObjectIdentifier() {
		roles = new ArrayList<String>();
	}
	
	public LinkingObjectIdentifier(XMLStreamReader reader) throws XMLStreamException {
		this();
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRole(List<String> roles) {
		this.roles = roles;
	}
	
	public void addRole(String role) {
		roles.add(role);
	}

	public String getLinkObjectXmlID() {
		return linkObjectXmlID;
	}

	public void setLinkObjectXmlID(String linkObjectXmlID) {
		this.linkObjectXmlID = linkObjectXmlID;
	}

	public SimpleLink getXlink() {
		return xlink;
	}

	public void setXlink(SimpleLink xlink) {
		this.xlink = xlink;
	}

	public void output(XMLStreamWriter writer, SMOutputElement parent) throws XMLStreamException {
		SMOutputElement linkingAgentIdentifier = parent.addElement(Premis.ns,"linkingObjectIdentifier");
		if(linkObjectXmlID != null) {
			linkingAgentIdentifier.addAttribute("LinkObjectXmlID",linkObjectXmlID);
		}
		if(xlink != null) {
			xlink.output(writer);
		}
		if(type != null) {
			linkingAgentIdentifier.addElementWithCharacters(Premis.ns,"linkingObjectIdentifierType",type);
		}
		if(value != null) {
			linkingAgentIdentifier.addElementWithCharacters(Premis.ns,"linkingObjectIdentifierValue",value);
		}	
		for(String role : roles) {
			linkingAgentIdentifier.addElementWithCharacters(Premis.ns,"linkingObjectRole",role);
		}
	}

	public LinkingObjectIdentifier parse(XMLStreamReader reader) throws XMLStreamException {
		int event = reader.getEventType();
		String localName = "";
		if(reader.getAttributeCount() > 0) {
			linkObjectXmlID = reader.getAttributeValue(Premis.XMLNS,"LinkingObjectXmlID");
			xlink = new SimpleLink(reader);
		}
		while(!(event == END_ELEMENT && localName.equals("linkingObjectIdentifier"))) {	
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();	
				if(localName.equals("linkingObjectIdentifierType")) {
					type = reader.getElementText();
				}
				else if(localName.equals("linkingObjectIdentifierValue")) {
					value = reader.getElementText();
				}
				else if(localName.equals("linkingObjectRole")) {
					roles.add(reader.getElementText());
				}
				break;
			case END_ELEMENT:
				localName = reader.getLocalName();
				break;	
			}
		}
		return this;
	}

	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

}
