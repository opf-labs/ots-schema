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

import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.premis.Premis;

public class DependencyIdentifier {

	private String type;
	private String value;
	
	public DependencyIdentifier() {	}
	
	public DependencyIdentifier(XMLStreamReader reader) throws XMLStreamException {
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

	public void output(SMOutputElement parent) throws XMLStreamException {
		SMOutputElement dependencyIdentifier = parent.addElement(Premis.ns,"dependencyIdentifier");	
		if(type != null) {
			dependencyIdentifier.addElementWithCharacters(Premis.ns,"dependencyIdentifierType",type);
		}
		if(value != null) {
			dependencyIdentifier.addElementWithCharacters(Premis.ns,"dependencyIdentifierValue",value);
		}
	}

	public DependencyIdentifier parse(XMLStreamReader reader) throws XMLStreamException {
		reader.require(START_ELEMENT, Premis.XMLNS, "dependencyIdentifier");	
		for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("dependencyIdentifierType")) {
						type = reader.getElementText();
					}
					else if(localName.equals("dependencyIdentifierValue")) {
						value = reader.getElementText();
					}
				break;		
				case END_ELEMENT:
				//return when we're at the end of the element
				if(reader.getLocalName().equals("dependencyIdentifier")) {
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
