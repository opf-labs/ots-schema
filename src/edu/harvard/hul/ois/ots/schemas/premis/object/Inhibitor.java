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
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.premis.Premis;


public class Inhibitor {

	private String type;
	private List<String> targets;
	private String key;
	
	public Inhibitor() { 
		targets = new ArrayList<String>();
	}
	
	public Inhibitor(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getTargets() {
		return targets;
	}

	public void setTargets(List<String> target) {
		this.targets = target;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void output(XMLStreamWriter writer, SMOutputElement parent) throws XMLStreamException {
		SMOutputElement inhibitor = parent.addElement(Premis.ns,"inhibitors");	
		if(type != null)  {
			inhibitor.addElementWithCharacters(Premis.ns,"inhibitorType",type);
		}
		for(String target : targets) {
			inhibitor.addElementWithCharacters(Premis.ns,"inhibitorTarget",target);
		}
		if(key != null)  {
			inhibitor.addElementWithCharacters(Premis.ns,"inhibitorKey",key);
		}		
	}

	public Inhibitor parse(XMLStreamReader reader) throws XMLStreamException {
		reader.require(START_ELEMENT, Premis.XMLNS, "inhibitors");	
		for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("inhibitorType")) {
						type = reader.getElementText();
					}
					else if(localName.equals("inhibitorTarget")) {
						targets.add(reader.getElementText());
					}
					else if(localName.equals("inhibitorKey")) {
						key = reader.getElementText();
					}
				break;		
				case END_ELEMENT:
				//return when we're at the end of the element
				if(reader.getLocalName().equals("inhibitors")) {
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
