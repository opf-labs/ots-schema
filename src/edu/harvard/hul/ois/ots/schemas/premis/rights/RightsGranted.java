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
package edu.harvard.hul.ois.ots.schemas.premis.rights;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.premis.Premis;
import edu.harvard.hul.ois.ots.schemas.premis.PremisDate;
import edu.harvard.hul.ois.ots.schemas.premis.PremisDateException;

public class RightsGranted {
	
	private String act;
	private List<String> restrictions;
	private PremisDate termGrantStartDate;
	private PremisDate termGrantEndDate;
	private List<String> notes;
	
	public RightsGranted() { 
		restrictions = new ArrayList<String>();
		notes = new ArrayList<String>();
		termGrantStartDate = new PremisDate();
		termGrantEndDate = new PremisDate();
	}
	
	public RightsGranted(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this();
		parse(reader);
	}
	
	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public List<String> getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(List<String> restrictions) {
		this.restrictions = restrictions;
	}
	
	public void addRestriction(String restriction) {
		restrictions.add(restriction);
	}

	public PremisDate getTermGrantStartDate() {
		return termGrantStartDate;
	}

	public void setTermGrantStartDate(PremisDate termGrantStartDate) {
		this.termGrantStartDate = termGrantStartDate;
	}
	
	public void setTermGrantStartDate(String date) throws PremisDateException {
		termGrantStartDate.setValue(date);
	}

	public PremisDate getTermGrantEndDate() {
		return termGrantEndDate;
	}

	public void setTermGrantEndDate(PremisDate termGrantEndDate) {
		this.termGrantEndDate = termGrantEndDate;
	}
	
	public void setTermGrantEndDate(String date) throws PremisDateException {
		termGrantEndDate.setValue(date);
	}

	public List<String> getNotes() {
		return notes;
	}

	public void setNotes(List<String> notes) {
		this.notes = notes;
	}
	
	public void addNote(String note) {
		notes.add(note);
	}
	
	public void output(SMOutputElement parent) throws XMLStreamException {
		SMOutputElement grantedRight = parent.addElement(Premis.ns,"rightsGranted");
		if(act != null) {
			grantedRight.addElementWithCharacters(Premis.ns,"act",act);
		}
		for(String restriction : restrictions) {
			grantedRight.addElementWithCharacters(Premis.ns,"restriction",restriction);
		}
		if(termGrantStartDate.toString() != null || termGrantEndDate.toString() != null) {
			SMOutputElement termOfGrant = grantedRight.addElement(Premis.ns,"termOfGrant");
			if(termGrantStartDate.toString() != null) {
				termOfGrant.addElementWithCharacters(Premis.ns,"startDate", termGrantStartDate.toString());
			}
			if(termGrantEndDate.toString() != null) {
				termOfGrant.addElementWithCharacters(Premis.ns,"endDate", termGrantEndDate.toString());
			}
		}
		for(String note : notes) {
			grantedRight.addElementWithCharacters(Premis.ns,"rightsGrantedNote",note);
		}
	}
	
	public RightsGranted parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		int event = reader.getEventType();
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("rightsGranted"))) {	
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();
				if(localName.equals("act")) {
					act = reader.getElementText();
				}
				else if(localName.equals("restriction")) {
					restrictions.add(reader.getElementText());
				}
				else if(localName.equals("startDate")) {
					setTermGrantStartDate(reader.getElementText());
				}
				else if(localName.equals("endDate")) {
					setTermGrantEndDate(reader.getElementText());
				}
				else if(localName.equals("rightsGrantedNote")) {
					notes.add(reader.getElementText());
				}
				break;
			case END_ELEMENT:
				localName = reader.getLocalName();
				break;	
			}
		}
		return this;
	}
}
