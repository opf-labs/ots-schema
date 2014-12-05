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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD.HulDrsAdminMD.Status;

public class GenericVersionedElement {

	private BasicVersionedElement current = null;
	private List<BasicVersionedElement> previousValues;
		
	public class BasicVersionedElement extends VersionedElementAttributes {
		private String value;
		
		public void output(SMOutputElement parent, SMNamespace ns, String elementName) throws XMLStreamException {
			SMOutputElement element = parent.addElement(ns, elementName);
			outputVersionedAttributes(element);
			element.addCharacters(value);
		}
		
		public String getValue() {
			return value;
		}
	}
	
	public GenericVersionedElement() {
		previousValues = new ArrayList<BasicVersionedElement>();
	}
	
	public String getValue() {
		return current.value;
	}
	
	public Date getCreateDate() {
		return current.createDate;
	}
	
	public String getCreatingAgent() {
		return current.creatingAgent;
	}
	
	public String getStatus() {
		return current.status.toString();
	}
	
	public BasicVersionedElement[] getPreviousValues() {
		int size = previousValues.size();
		BasicVersionedElement[] previousValueArr = new BasicVersionedElement[size];
		for(int i=0;i<size;i++) {
			previousValueArr[i] = previousValues.get(i);
		}
		return previousValueArr;
	}

	public void setValue(String value, String agent) {
		//if current has already been set
		if(current != null) {
			//create a copy of the current element
			BasicVersionedElement previousElement = new BasicVersionedElement();
			previousElement.value = current.value;
			previousElement.createDate = current.createDate;
			previousElement.creatingAgent = current.creatingAgent;
			previousElement.status = Status.superseded;
			previousElement.modAgent = agent;
			previousElement.modDate = new Date();
			
			//set the current values
			current.value = value;
			current.creatingAgent = agent;
			current.createDate = new Date();
			//current.status is already "current"
			
			//add previous element to list
			previousValues.add(previousElement);
			
		}
		else {
			current = new BasicVersionedElement();
			current.status = Status.current;
		}
		current.value = value;
		current.creatingAgent = agent;
		current.createDate = new Date();
	}
	

	
	public void parse(XMLStreamReader reader) throws XMLStreamException {
		BasicVersionedElement element = new BasicVersionedElement();
		element.status = Status.valueOf(reader.getAttributeValue(null,"status"));
		try {
			String d = reader.getAttributeValue(null,"createDate");
			if(d != null)
				element.createDate = element.sdf.parse(d);
		}
		catch (ParseException e) {
			throw new XMLStreamException("Invalid Date: "+reader.getAttributeValue(null,"createDate"));
		}
		try {
			String d = reader.getAttributeValue(null,"modDate");
			if(d != null)
				element.modDate = element.sdf.parse(d);
		}
		catch (ParseException e) {
			throw new XMLStreamException("Invalid Date: "+reader.getAttributeValue(null,"modDate"));
		}		
		element.creatingAgent = reader.getAttributeValue(null,"creatingAgent");
		element.modAgent = reader.getAttributeValue(null,"modAgent");
		element.value = reader.getElementText();
		
		if(element.status == Status.current) {
			current = element;
		}
		else {
			previousValues.add(element);
		}		
	}
	
	public void output(SMOutputElement parent, SMNamespace ns, String elementName) throws XMLStreamException {
		//output current values
		if(current != null) {
			current.output(parent,ns,elementName);
		}
		//output previous values
		for(BasicVersionedElement bve : getPreviousValues()) {
			bve.output(parent,ns,elementName);
		}

	}
	
}
