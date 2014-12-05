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

public class StatuteInformation {
	
	private String jurisdiction;
	private String citation;
	private PremisDate determinationDate;
	private List<String> notes;
	
	public StatuteInformation(){ 
		notes = new ArrayList<String>();
		determinationDate = new PremisDate();
	}
	
	public StatuteInformation(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this();
		parse(reader);
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getCitation() {
		return citation;
	}

	public void setCitation(String citation) {
		this.citation = citation;
	}

	public PremisDate getDeterminationDate() {
		return determinationDate;
	}

	public void setDeterminationDate(PremisDate determinationDate) {
		this.determinationDate = determinationDate;
	}
	
	public void setDeterminationDate(String date) throws PremisDateException {
		determinationDate.setValue(date);
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
		SMOutputElement statuteInfo = parent.addElement(Premis.ns,"statuteInformation");
		if(jurisdiction != null) {
			statuteInfo.addElementWithCharacters(Premis.ns,"statuteJurisdiction",jurisdiction);
		}
		if(citation != null) {
			statuteInfo.addElementWithCharacters(Premis.ns,"statuteCitation",citation);
		}
		if(determinationDate.toString() != null) {
			statuteInfo.addElementWithCharacters(Premis.ns,"statuteInformationDeterminationDate",determinationDate.toString());
		}
		for(String note : notes) {
			statuteInfo.addElementWithCharacters(Premis.ns,"statuteNote",note);
		}
	}
	
	public StatuteInformation parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		int event = reader.getEventType();
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("statuteInformation"))) {	
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();
				if(localName.equals("statuteJurisdiction")) {
					jurisdiction = reader.getElementText();
				}
				else if(localName.equals("statuteCitation")) {
					citation = reader.getElementText();
				}
				else if(localName.equals("statuteInformationDeterminationDate")) {
					setDeterminationDate(reader.getElementText());
				}	
				else if(localName.equals("statuteNote")) {
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
