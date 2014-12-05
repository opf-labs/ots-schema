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
package edu.harvard.hul.ois.ots.schemas.premis.event;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.premis.Premis;

public class EventOutcomeInformation {
	
	private String eventOutcome;
	private List<EventOutcomeDetail> eventOutcomeDetails;

	public EventOutcomeInformation() {
		eventOutcomeDetails = new ArrayList<EventOutcomeDetail>();
	}
	
	public EventOutcomeInformation(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this();
		parse(reader);
	}
		
	public String getEventOutcome() {
		return eventOutcome;
	}

	public void setEventOutcome(String eventOutcome) {
		this.eventOutcome = eventOutcome;
	}

	public List<EventOutcomeDetail> getEventOutcomeDetail() {
		return eventOutcomeDetails;
	}

	public void setEventOutcomeDetail(List<EventOutcomeDetail> eventOutcomeDetail) {
		this.eventOutcomeDetails = eventOutcomeDetail;
	}

	public void addEventOutcomeDetail(EventOutcomeDetail eventDetail) {
		eventOutcomeDetails.add(eventDetail);
	}

	public void output(XMLStreamWriter writer, SMOutputElement parent) throws XMLStreamException {
		SMOutputElement outcomeInfo = parent.addElement(Premis.ns,"eventOutcomeInformation");
		if(eventOutcome != null) {
			outcomeInfo.addElementWithCharacters(Premis.ns,"eventOutcome",eventOutcome);
		}
		for(EventOutcomeDetail detail : eventOutcomeDetails) {
			detail.output(writer,outcomeInfo);
		}
	}

	public EventOutcomeInformation parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		int event = reader.getEventType();
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("eventOutcomeInformation"))) {	
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();	
				if(localName.equals("eventOutcome")) {
					eventOutcome = reader.getElementText();
				}
				else if(localName.equals("eventOutcomeDetail")) {
					eventOutcomeDetails.add(new EventOutcomeDetail(reader));
				}
				break;
			case END_ELEMENT:
				localName = reader.getLocalName();
				break;	
			}
		}
		return this;
	}

	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

}
