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
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

import edu.harvard.hul.ois.ots.schemas.premis.Premis;

public class Relationship {
	
	private String type;
	private String subType;
	private List<RelatedObjectIdentification> relatedObjectIdentifiers;
	private List<RelatedEventIdentification> relatedEventIdentifiers;
	
	public Relationship() {
		relatedObjectIdentifiers = new ArrayList<RelatedObjectIdentification>();
		relatedEventIdentifiers = new ArrayList<RelatedEventIdentification>();
	}

	public Relationship(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public List<RelatedObjectIdentification> getRelatedObjectIdentifiers() {
		return relatedObjectIdentifiers;
	}

	public void setRelatedObjectIdentifiers(List<RelatedObjectIdentification> relatedObjectIdentifiers) {
		this.relatedObjectIdentifiers = relatedObjectIdentifiers;
	}
	
	public void addRelatedObjectIdentifier(RelatedObjectIdentification ident) {
		relatedObjectIdentifiers.add(ident);
	}

	public List<RelatedEventIdentification> getRelatedEventIdentifiers() {
		return relatedEventIdentifiers;
	}

	public void setRelatedEventIdentifiers(List<RelatedEventIdentification> relatedEventIdentifiers) {
		this.relatedEventIdentifiers = relatedEventIdentifiers;
	}
	
	public void addRelatedEventIdentifier(RelatedEventIdentification ident) {
		relatedEventIdentifiers.add(ident);
	}

	public void output(XMLStreamWriter writer) throws XMLStreamException {
		SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
		SMOutputElement relationship = frag.addElement(Premis.ns,"relationship");
		if(type != null) {
			relationship.addElementWithCharacters(Premis.ns,"relationshipType",type);
		}
		if(subType != null) {
			relationship.addElementWithCharacters(Premis.ns,"relationshipSubType",subType);
		}
		for(RelatedObjectIdentification relObjectIdent : relatedObjectIdentifiers) {
			relObjectIdent.output(writer,relationship);
		}
		for(RelatedEventIdentification relEventIdent : relatedEventIdentifiers) {
			relEventIdent.output(writer,relationship);
		}
		frag.closeRoot();
	}

	public Relationship parse(XMLStreamReader reader) throws XMLStreamException {
		reader.require(START_ELEMENT, Premis.XMLNS, "relationship");	
		for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("relationshipType")) {
						type = reader.getElementText();
					}
					else if(localName.equals("relationshipSubType")) {
						subType = reader.getElementText();
					}
					else if(localName.equals("relatedObjectIdentification")) {
						RelatedObjectIdentification rObjectIdentfier = new RelatedObjectIdentification(reader);
						relatedObjectIdentifiers.add(rObjectIdentfier);
					}
					else if(localName.equals("relatedEventIdentification")) {
						RelatedEventIdentification rEventIdentifer = new RelatedEventIdentification(reader);
						relatedEventIdentifiers.add(rEventIdentifer);
					}
				break;		
				case END_ELEMENT:
				//return when we're at the end of the element
				if(reader.getLocalName().equals("relationship")) {
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
