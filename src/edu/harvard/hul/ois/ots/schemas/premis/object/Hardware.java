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

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.premis.Premis;

public class Hardware {

	private String name;
	private String type;
	private List<String> otherInformation;
	
	public Hardware() {
		otherInformation = new ArrayList<String>();
	}
	
	public Hardware(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getOtherInformation() {
		return otherInformation;
	}

	public void setOtherInformation(List<String> otherInformation) {
		this.otherInformation = otherInformation;
	}
	
	public void addOtherInformation(String info) {
		otherInformation.add(info);
	}

	public void output(SMOutputElement parent) throws XMLStreamException {
		SMOutputElement hw = parent.addElement(Premis.ns,"hardware");	
		if(name != null) {
			hw.addElementWithCharacters(Premis.ns,"hwName",name);
		}
		if(type != null) {
			hw.addElementWithCharacters(Premis.ns,"hwType",type);
		}
		for(String info : otherInformation) {
			hw.addElementWithCharacters(Premis.ns,"hwOtherInformation",info);
		}
	}

	public Hardware parse(XMLStreamReader reader) throws XMLStreamException {
		reader.require(START_ELEMENT, Premis.XMLNS, "hardware");	
		for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("hwName")) {
						name = reader.getElementText();
					}
					else if(localName.equals("hwType")) {
						type = reader.getElementText();
					}
					else if(localName.equals("hwOtherInformation")) {
						otherInformation.add(reader.getElementText());
					}
				break;		
				case END_ELEMENT:
				//return when we're at the end of the element
				if(reader.getLocalName().equals("hardware")) {
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
