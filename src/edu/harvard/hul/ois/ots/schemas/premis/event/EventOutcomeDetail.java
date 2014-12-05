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

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContent;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentParser;
import edu.harvard.hul.ois.ots.schemas.premis.Premis;
import edu.harvard.hul.ois.ots.schemas.premis.PremisParser;

public class EventOutcomeDetail {
	
	private String detailNote;
	private XmlContent extension;

	public EventOutcomeDetail() { }
	
	public EventOutcomeDetail(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this();
		parse(reader);
	}
	
	public String getDetailNote() {
		return detailNote;
	}

	public void setDetailNote(String detailNote) {
		this.detailNote = detailNote;
	}

	public XmlContent getExtension() {
		return extension;
	}

	public void setExtension(XmlContent extension) {
		this.extension = extension;
	}

	public void output(XMLStreamWriter writer, SMOutputElement parent) throws XMLStreamException {
		SMOutputElement eventOutcomeDetail = parent.addElement(Premis.ns,"eventOutcomeDetail");
		if(detailNote != null) {
			eventOutcomeDetail.addElementWithCharacters(Premis.ns,"eventOutcomeDetailNote", detailNote);
		}
		if(extension != null) {
			eventOutcomeDetail.addElement(Premis.ns,"eventOutcomeDetailExtension");
			extension.output(writer);
		}
	}

	public EventOutcomeDetail parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		int event = reader.getEventType();
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("eventOutcomeDetail"))) {	
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();	
				if(localName.equals("eventOutcomeDetailNote")) {
					detailNote = reader.getElementText();
				}
				else if(localName.equals("eventOutcomeDetailExtension")) {
					//advance to next element
					reader.nextTag();
					QName qname = reader.getName();
					//get a parser
					XmlContentParser parser = PremisParser.getParser(qname.getPrefix()+":"+qname.getLocalPart());
					extension = parser.parse(reader);
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
