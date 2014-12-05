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
package edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

public class AdminCategory {
	
	private String  id;
	private String name;
	private String description;

	public AdminCategory() { }
	
	public AdminCategory(String id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public AdminCategory(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public AdminCategory parse(XMLStreamReader reader) throws XMLStreamException {
		int event = reader.getEventType();
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("adminCategory"))) {	
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();	
				if(localName.equals("adminCategoryID")) {
					id = reader.getElementText();
				}
				else if(localName.equals("adminCategoryName")) {
					name = reader.getElementText();
				}
				else if(localName.equals("adminCategoryDescription")) {
					description = reader.getElementText();
				}
				break;
			case END_ELEMENT:
				localName = reader.getLocalName();
				break;	
			}
		}
		return this;
	}

	public void output(SMOutputElement parent, SMNamespace ns) throws XMLStreamException {
		SMOutputElement adminCategory = parent.addElement(ns,"adminCategory");
		if(id != null) {
			adminCategory.addElementWithCharacters(ns,"adminCategoryID",id);
		}
		if(name != null) {
			adminCategory.addElementWithCharacters(ns,"adminCategoryName",name);
		}	
		if(description != null) {
			adminCategory.addElementWithCharacters(ns,"adminCategoryDescription",description);
		}
	}
	
}
