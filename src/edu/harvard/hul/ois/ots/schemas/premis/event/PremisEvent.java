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
import static javax.xml.stream.XMLStreamConstants.START_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMOutputDocument;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

import edu.harvard.hul.ois.ots.schemas.XmlComponent;
import edu.harvard.hul.ois.ots.schemas.StaxUtils.RootXmlHelper;
import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;
import edu.harvard.hul.ois.ots.schemas.XmlContent.SimpleLink;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContent;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.premis.LinkingAgentIdentifier;
import edu.harvard.hul.ois.ots.schemas.premis.LinkingObjectIdentifier;
import edu.harvard.hul.ois.ots.schemas.premis.Premis;
import edu.harvard.hul.ois.ots.schemas.premis.PremisDate;
import edu.harvard.hul.ois.ots.schemas.premis.PremisDateException;


public class PremisEvent implements XmlComponent {
	
	private String eventType;
	private PremisDate dateTime;
	private String eventDetail;
	private String xmlID;
	private String version;
	private List<EventOutcomeInformation> eventOutcomeInformation;
	private List<LinkingObjectIdentifier> linkingObjectIdentifiers;
	private List<LinkingAgentIdentifier> linkingAgentIdentifiers;
	
	//eventidentifier variables 
	private String eventIdentifierType;
	private String eventIdentifierValue;
	private SimpleLink eventIdentifierXlink;
	
	private boolean isRoot;

	public PremisEvent() {
		this(false);
		dateTime = new PremisDate();
	}
	
	public PremisEvent(boolean isRoot) {
		this.isRoot = isRoot;
		linkingAgentIdentifiers = new ArrayList<LinkingAgentIdentifier>();
		linkingObjectIdentifiers = new ArrayList<LinkingObjectIdentifier>();
		eventOutcomeInformation = new ArrayList<EventOutcomeInformation>();
		dateTime = new PremisDate();
	}
	
	public PremisEvent(XMLStreamReader reader, boolean isRoot) throws XMLStreamException, XmlContentException {
		this(isRoot);
		parse(reader);
	}
	
	public PremisEvent(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this(false);
		parse(reader);
	}
	
	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
		
	public String getType() {
		return eventType;
	}

	public void setType(String type) {
		this.eventType = type;
	}

	public PremisDate getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) throws PremisDateException {
		this.dateTime.setValue(dateTime);
	}

	public String getDetail() {
		return eventDetail;
	}

	public void setDetail(String detail) {
		this.eventDetail = detail;
	}

	public String getXmlID() {
		return xmlID;
	}

	public void setXmlID(String xmlID) {
		this.xmlID = xmlID;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion() {
		this.version = Premis.VERSION;
	}

	public List<EventOutcomeInformation> getEventOutcomeInformation() {
		return eventOutcomeInformation;
	}

	public void setEventOutcomeInformation(
			List<EventOutcomeInformation> eventOutcomeInformation) {
		this.eventOutcomeInformation = eventOutcomeInformation;
	}
	
	public void addEventOutcomeInformation(EventOutcomeInformation eventInfo) {
		eventOutcomeInformation.add(eventInfo);
	}

	public String getEventIdentifierType() {
		return eventIdentifierType;
	}

	public void setEventIdentifierType(String eventIdentifierType) {
		this.eventIdentifierType = eventIdentifierType;
	}

	public String getEventIdentifierValue() {
		return eventIdentifierValue;
	}

	public void setEventIdentifierValue(String eventIdentifierValue) {
		this.eventIdentifierValue = eventIdentifierValue;
	}

	public SimpleLink getXlink() {
		return eventIdentifierXlink;
	}

	public void setXlink(SimpleLink xlink) {
		this.eventIdentifierXlink = xlink;
	}

	public List<LinkingObjectIdentifier> getLinkingObjectIdentifiers() {
		return linkingObjectIdentifiers;
	}

	public void setLinkingObjectIdentifiers(
			List<LinkingObjectIdentifier> linkingObjectIdentifiers) {
		this.linkingObjectIdentifiers = linkingObjectIdentifiers;
	}

	public void addLinkingObjectIdentifier(LinkingObjectIdentifier identifier) {
		linkingObjectIdentifiers.add(identifier);
	}
	
	public List<LinkingAgentIdentifier> getLinkingAgentIdentifiers() {
		return linkingAgentIdentifiers;
	}

	public void setLinkingAgentIdentifiers(
			List<LinkingAgentIdentifier> linkingAgentIdentifiers) {
		this.linkingAgentIdentifiers = linkingAgentIdentifiers;
	}
	
	public void addLinkingAgentIdentifier(LinkingAgentIdentifier identifier) {
		linkingAgentIdentifiers.add(identifier);
	}

	public void output(XMLStreamWriter writer) throws XMLStreamException {
		if(isRoot) {
			SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer,"1.0","UTF-8",false);	
			Premis.ns = doc.getNamespace(Premis.XMLNS,Premis.nsSchemaContext.getPrefix());
			SMOutputElement root = doc.addElement(Premis.ns,"event");			
			RootXmlHelper.output(writer,getAllNamespaceSchemaContexts(),root);
			outputContents(writer,root);
			doc.closeRoot();
		}
		else {
			SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
			Premis.ns = frag.getNamespace(Premis.XMLNS,Premis.nsSchemaContext.getPrefix());
			SMOutputElement fragRoot = frag.addElement(Premis.ns,"event");
			outputContents(writer,fragRoot);
			frag.closeRoot();
		}	
	}
	
	private void outputContents(XMLStreamWriter writer,SMOutputElement obj) throws XMLStreamException {
		if(xmlID != null) {
			obj.addAttribute("xmlID",xmlID);
		}
		if(version != null) {
			obj.addAttribute("version",version);
		}
		if(eventIdentifierType != null || eventIdentifierValue != null || eventIdentifierXlink != null) {
			SMOutputElement eventIdent = obj.addElement(Premis.ns,"eventIdentifier");
			if(eventIdentifierXlink != null) {
				eventIdentifierXlink.output(writer);
			}
			if(eventIdentifierType != null) {
				eventIdent.addElementWithCharacters(Premis.ns,"eventIdentifierType",eventIdentifierType);
			}
			if(eventIdentifierValue != null) {
				eventIdent.addElementWithCharacters(Premis.ns,"eventIdentifierValue",eventIdentifierValue);
			}
		}
		if(eventType != null) {
			obj.addElementWithCharacters(Premis.ns,"eventType",eventType);
		}
		if(dateTime.toString() != null) {
			obj.addElementWithCharacters(Premis.ns,"eventDateTime",dateTime.toString());
		}
		if(eventDetail != null) {
			obj.addElementWithCharacters(Premis.ns,"eventDetail",eventDetail);
		}
		for(EventOutcomeInformation eventInfo : eventOutcomeInformation) {
			eventInfo.output(writer,obj);
		}
		for(LinkingAgentIdentifier linkingAgentIdent: linkingAgentIdentifiers) {
			linkingAgentIdent.output(writer,obj);
		}
		for(LinkingObjectIdentifier linkingObjIdent : linkingObjectIdentifiers) {
			linkingObjIdent.output(writer,obj);
		}
	}

	public PremisEvent parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		int event = reader.getEventType();
		if(event == START_DOCUMENT) {
			reader.nextTag();
		}
		reader.require(START_ELEMENT, Premis.XMLNS, "event");	
		xmlID = reader.getAttributeValue(null,"xmlID");
		version = reader.getAttributeValue(null,"version");
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("event"))) {	
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();	
				if(localName.equals("eventIdentifier")) {
					if(reader.getAttributeCount() > 0) {
						eventIdentifierXlink = new SimpleLink(reader);
					}
				}
				else if(localName.equals("eventIdentifierType")) {
					eventIdentifierType = reader.getElementText();
				}
				else if(localName.equals("eventIdentifierValue")) {
					eventIdentifierValue = reader.getElementText();
				}
				else if(localName.equals("eventType")) {
					eventType = reader.getElementText();
				}
				else if(localName.equals("eventDateTime")) {
					setDateTime(reader.getElementText());
				}
				else if(localName.equals("eventDetail")) {
					eventDetail = reader.getElementText();
				}
				else if(localName.equals("eventOutcomeInformation")) {
					eventOutcomeInformation.add(new EventOutcomeInformation(reader));
				}
				else if(localName.equals("linkingAgentIdentifier")) {
					linkingAgentIdentifiers.add(new LinkingAgentIdentifier(reader));
				}
				else if(localName.equals("linkingObjectIdentifier")) {
					linkingObjectIdentifiers.add(new LinkingObjectIdentifier(reader));
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

	@Override
	public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
		List<NamespaceSchemaContext> contexts = new ArrayList<NamespaceSchemaContext>();
		contexts.add(Premis.nsSchemaContext);
		for(EventOutcomeInformation info : eventOutcomeInformation) {
			for(EventOutcomeDetail detail : info.getEventOutcomeDetail()) {
				XmlContent xml = detail.getExtension();
				if(xml != null) {
					contexts.addAll(xml.getAllNamespaceSchemaContexts());
				}
			}
		}
		//check eventIdentifiers for xlink
		if(eventIdentifierXlink != null) {
			contexts.add(SimpleLink.nsSchemaContext);
		}
		//else look in LinkingAgentIdentifier and LinkingObjectIdentifier
		else {
			boolean foundXlink = false;
			for(LinkingAgentIdentifier ident : linkingAgentIdentifiers) {
				if(ident.getXlink() != null) {
					contexts.add(SimpleLink.nsSchemaContext);
					foundXlink = true;
					break;
				}
			}			
			if(!foundXlink) {
				for(LinkingObjectIdentifier ident : linkingObjectIdentifiers) {
					if(ident.getXlink() != null) {
						contexts.add(SimpleLink.nsSchemaContext);
						break;
					}
				}
			}
		}
		
		return contexts;
	}

	@Override
	public NamespaceSchemaContext getNamespaceSchemaContext() {
		return Premis.nsSchemaContext;
	}

}
