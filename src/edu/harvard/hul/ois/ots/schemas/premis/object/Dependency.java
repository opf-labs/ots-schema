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

public class Dependency {

	private List<String> names;
	private List<DependencyIdentifier> dependencyIdentifiers;

	public Dependency() {
		names = new ArrayList<String>();
		dependencyIdentifiers = new ArrayList<DependencyIdentifier>();
	}
	
	public Dependency(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}
	
	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public void addName(String name) {
		this.names.add(name);
	}

	public List<DependencyIdentifier> getDependencyIdentifiers() {
		return dependencyIdentifiers;
	}

	public void setDependencyIdentifiers(List<DependencyIdentifier> dependencyIdentifiers) {
		this.dependencyIdentifiers = dependencyIdentifiers;
	}
	
	public void addDependencyIdentifier(DependencyIdentifier dependencyIdentifier) {
		this.dependencyIdentifiers.add(dependencyIdentifier);
	}

	public void output(SMOutputElement parent) throws XMLStreamException {
		SMOutputElement dependency = parent.addElement(Premis.ns,"dependency");	
		for(String name : names) {
			dependency.addElementWithCharacters(Premis.ns,"dependencyName",name);
		}
		for(DependencyIdentifier dependencyIdentifier : dependencyIdentifiers) {
			dependencyIdentifier.output(dependency);
		}
	}

	public Dependency parse(XMLStreamReader reader) throws XMLStreamException {
		reader.require(START_ELEMENT, Premis.XMLNS, "dependency");	
		for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("dependencyName")) {
						names.add(reader.getElementText());
					}
					else if(localName.equals("dependencyIdentifier")) {
						DependencyIdentifier dependencyIdentifier = new DependencyIdentifier(reader);
						dependencyIdentifiers.add(dependencyIdentifier);
					}
				break;		
				case END_ELEMENT:
				//return when we're at the end of the element
				if(reader.getLocalName().equals("dependency")) {
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
