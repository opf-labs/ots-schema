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

import java.text.ParseException;
import java.util.Date;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD.HulDrsAdminMD.Status;

public class AdminFlag extends VersionedElementAttributes {
	
	public static enum FlagType {CORRUPTED,FORMAT_ID_CONFLICT,OBJECT_INVALID,VIRUS_INFECTED,FAILED_OBJECT_CREATION,INCORRECT_METADATA,FAILED_METADATA_EXTRACTION}
	
	private FlagType flagType;
	private String flagNote;
	
	public AdminFlag(FlagType flagType, String flagNote, String agent) {		
		status = Status.current;
		this.flagType = flagType;
		this.flagNote = flagNote;
		creatingAgent = agent;
		createDate = new Date();
	}
	
	protected AdminFlag() { 	}
	
	public AdminFlag(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}
	
	public FlagType getFlagType() {
		return flagType;
	}
	
	public String getFlagNote() {
		return flagNote;
	}
	
	/**
	 *sets the status attribute to deleted and adds modagent and moddate attributes
	 */
	public void delete(String agent) {
		modAgent = agent;
		modDate = new Date();
		status = Status.deleted;
	}
	
	/**
	 * updates the admin flag note
	 * @param note
	 */
	public void setFlagNote(String flagNote) {
		this.flagNote = flagNote;
	}
	
	public void parse(XMLStreamReader reader) throws XMLStreamException {
		status = Status.valueOf(reader.getAttributeValue(null,"status"));
		try {
			String d = reader.getAttributeValue(null,"createDate");
			if(d != null)
				createDate = sdf.parse(d);
		}
		catch (ParseException e) {
			throw new XMLStreamException("Invalid Date: "+reader.getAttributeValue(null,"createDate"));
		}
		try {
			String d = reader.getAttributeValue(null,"modDate");
			if(d != null) 
				modDate = sdf.parse(d);
		}
		catch (ParseException e) {
			throw new XMLStreamException("Invalid Date: "+reader.getAttributeValue(null,"modDate"));
		}
		
		creatingAgent = reader.getAttributeValue(null,"creatingAgent");
		modAgent = reader.getAttributeValue(null,"modAgent");

		int event = reader.getEventType();
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("adminFlag"))) {
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();
				if(localName.equals("flagType")) {
					flagType = FlagType.valueOf(reader.getElementText());
				}
				else if(localName.equals("flagNote")) {
					flagNote = reader.getElementText();
				}
				break;
			case END_ELEMENT:
				localName = reader.getLocalName();
				break;
			}
		}
	}

	public void output(SMOutputElement parent, SMNamespace ns) throws XMLStreamException {
		SMOutputElement adminFlag = parent.addElement(ns, "adminFlag");
		outputVersionedAttributes(adminFlag);
		if(flagType != null) {
			adminFlag.addElementWithCharacters(ns, "flagType",flagType.toString());
		}
		if(flagNote != null) {
			adminFlag.addElementWithCharacters(ns, "flagNote",flagNote);
		}
	}
}
