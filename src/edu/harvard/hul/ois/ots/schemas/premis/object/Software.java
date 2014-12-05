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

public class Software {

	private String name;
	private String version;
	private String type;
	private List<String> otherInformation;
	private List<String> dependencies;
	
	public Software() {
		otherInformation = new ArrayList<String>();
		dependencies = new ArrayList<String>();
	}
	
	public Software(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	public List<String> getDependency() {
		return dependencies;
	}

	public void setDependencies(List<String> dependencies) {
		this.dependencies = dependencies;
	}

	public void addDependencies(String dependency) {
		dependencies.add(dependency);
	}

	public void output(SMOutputElement parent) throws XMLStreamException {
		SMOutputElement sw = parent.addElement(Premis.ns,"software");	
		if(name != null) {
			sw.addElementWithCharacters(Premis.ns,"swName",name);
		}
		if(version != null) {
			sw.addElementWithCharacters(Premis.ns,"swVersion",version);
		}
		if(type != null) {
			sw.addElementWithCharacters(Premis.ns,"swType",type);
		}
		for(String info : otherInformation) {
			sw.addElementWithCharacters(Premis.ns,"swOtherInformation",info);
		}
		for(String dependency : dependencies) {
			sw.addElementWithCharacters(Premis.ns,"swDependency",dependency);
		}
	}

	public Software parse(XMLStreamReader reader) throws XMLStreamException {
		reader.require(START_ELEMENT, Premis.XMLNS, "software");	
		for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("swName")) {
						name = reader.getElementText();
					}
					else if(localName.equals("swVersion")) {
						version = reader.getElementText();
					}
					else if(localName.equals("swType")) {
						type = reader.getElementText();
					}
					else if(localName.equals("swOtherInformation")) {
						otherInformation.add(reader.getElementText());
					}
					else if(localName.equals("swDependency")) {
						dependencies.add(reader.getElementText());
					}
				break;		
				case END_ELEMENT:
				//return when we're at the end of the element
				if(reader.getLocalName().equals("software")) {
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
