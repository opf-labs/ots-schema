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

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import edu.harvard.hul.ois.ots.schemas.XmlComponent;
import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.premis.Premis;


public abstract class PremisObject implements XmlComponent {
	
	protected String xmlID;
	protected String version;
	protected String type;
	protected List<SignificantProperties> significantProperties;
	protected List<ObjectIdentifier> objectIdentifiers;
	protected List<Environment> environments;
	protected List<Relationship> relationships;
	protected List<LinkingEventIdentifier> linkingEventIdentifiers;
	protected List<LinkingRightsStatementIdentifier> linkingRightsStatementIdentifiers;
	protected List<LinkingIntellectualEntityIdentifier> linkingIntellectualEntityIdentifiers;
	protected boolean isRoot;
		
	public PremisObject(boolean isRoot) {
		significantProperties = new ArrayList<SignificantProperties>();
		objectIdentifiers = new ArrayList<ObjectIdentifier>();
		environments = new ArrayList<Environment>();
		relationships = new ArrayList<Relationship>();
		linkingEventIdentifiers = new ArrayList<LinkingEventIdentifier>();
		linkingRightsStatementIdentifiers = new ArrayList<LinkingRightsStatementIdentifier>();
		linkingIntellectualEntityIdentifiers = new ArrayList<LinkingIntellectualEntityIdentifier>();
		this.isRoot = isRoot;
	}
	
	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
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
		//version must be 2.0
		this.version = "2.0";
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
		
	public List<SignificantProperties> getSignificantProperties() {
		return significantProperties;
	}

	public void setSignificantProperties(
			List<SignificantProperties> significantProperties) {
		this.significantProperties = significantProperties;
	}
	
	public void addSignificantProperty(SignificantProperties property) {
		significantProperties.add(property);
	}

	public List<ObjectIdentifier> getObjectIdentifiers() {
		return objectIdentifiers;
	}

	public void setObjectIdentifiers(List<ObjectIdentifier> objectIdentifiers) {
		this.objectIdentifiers = objectIdentifiers;
	}

	public void addObjectIdentifier(ObjectIdentifier ident) {
		objectIdentifiers.add(ident);
	}
	
	public List<Environment> getEnvironments() {
		return environments;
	}

	public void setEnvironments(List<Environment> environments) {
		this.environments = environments;
	}
	
	public void addEnvironment(Environment environment) {
		environments.add(environment);
	}

	public List<Relationship> getRelationships() {
		return relationships;
	}

	public void setRelationships(List<Relationship> relationships) {
		this.relationships = relationships;
	}
	
	public void addRelationship(Relationship relationship) {
		relationships.add(relationship);
	}

	public List<LinkingEventIdentifier> getLinkingEventIdentifiers() {
		return linkingEventIdentifiers;
	}

	public void setLinkingEventIdentifiers(
			List<LinkingEventIdentifier> linkingEventIdentifiers) {
		this.linkingEventIdentifiers = linkingEventIdentifiers;
	}
	
	public void addLinkingEventIdentifier(LinkingEventIdentifier ident) {
		linkingEventIdentifiers.add(ident);
	}

	public List<LinkingRightsStatementIdentifier> getLinkingRightsStatementIdentifiers() {
		return linkingRightsStatementIdentifiers;
	}

	public void setLinkingRightsStatementIdentifiers(List<LinkingRightsStatementIdentifier> idents) {
		this.linkingRightsStatementIdentifiers = idents;
	}
	
	public void addLinkingRightsStatementIdentifier(LinkingRightsStatementIdentifier ident) {
		linkingRightsStatementIdentifiers.add(ident);
	}

	public List<LinkingIntellectualEntityIdentifier> getLinkingIntellectualEntityIdentifiers() {
		return linkingIntellectualEntityIdentifiers;
	}

	public void setLinkingIntellectualEntityIdentifiers(List<LinkingIntellectualEntityIdentifier> idents) {
		linkingIntellectualEntityIdentifiers = idents;
	}
	
	public void addLinkingIntellectualEntityIdentifier(LinkingIntellectualEntityIdentifier ident) {
		this.linkingIntellectualEntityIdentifiers.add(ident);
	}
	
	public void readObjectElements(XMLStreamReader reader, String localName) throws XMLStreamException, XmlContentException {
		if(localName.equals("significantProperties")) {
			SignificantProperties sigProp = new SignificantProperties(reader);
			significantProperties.add(sigProp);
		}
		else if(localName.equals("objectIdentifier")) {
			ObjectIdentifier objIdentifier = new ObjectIdentifier(reader);
			objectIdentifiers.add(objIdentifier);
		}
		else if(localName.equals("environment")) {
			Environment environment = new Environment(reader);
			environments.add(environment);
		}
		else if(localName.equals("relationship")) {
			Relationship relationship = new Relationship(reader);
			relationships.add(relationship);
		}
		else if(localName.equals("linkingEventIdentifier")) {
			LinkingEventIdentifier linkingEventIdentifier = new LinkingEventIdentifier(reader);
			linkingEventIdentifiers.add(linkingEventIdentifier);
		}
		else if(localName.equals("linkingRightsStatementIdentifier")) {
			LinkingRightsStatementIdentifier linkingRightsStatementIdentifier = new LinkingRightsStatementIdentifier(reader);
			linkingRightsStatementIdentifiers.add(linkingRightsStatementIdentifier);
		}
		else if(localName.equals("linkingIntellectualEntityIdentifier")) {
			LinkingIntellectualEntityIdentifier linkingIntellectualEntityIdentifier = new LinkingIntellectualEntityIdentifier(reader);
			linkingIntellectualEntityIdentifiers.add(linkingIntellectualEntityIdentifier);
		}
	}
	
	@Override
	public NamespaceSchemaContext getNamespaceSchemaContext() {
		return Premis.nsSchemaContext;
	}
	
	public boolean findXlinkNS() {
		boolean foundXlink = false;
		for(ObjectIdentifier ident : objectIdentifiers) {
			if(ident.getXlink() != null) {
				foundXlink = true;
				break;
			}
		}
		if(!foundXlink)
		for(LinkingEventIdentifier ident : linkingEventIdentifiers) {
			if(ident.getXlink() != null) {
				foundXlink = true;
				break;
			}
		}
		if(!foundXlink)
		for(LinkingRightsStatementIdentifier ident : linkingRightsStatementIdentifiers) {
			if(ident.getXlink() != null) {
				foundXlink = true;
				break;
			}
		}
		if(!foundXlink)
		for(LinkingIntellectualEntityIdentifier ident : linkingIntellectualEntityIdentifiers) {
			if(ident.getXlink() != null) {
				foundXlink = true;
				break;
			}
		}
		if(!foundXlink)
			for(Relationship r : relationships) {
				for(RelatedEventIdentification rr : r.getRelatedEventIdentifiers()) {
					if(rr.getXlink() != null) {
						foundXlink = true;
					}
				}
				if(!foundXlink)
				for(RelatedObjectIdentification rr : r.getRelatedObjectIdentifiers()) {
					if(rr.getXlink() != null) {
						foundXlink = true;
					}
				}
				if(foundXlink) {
					break;
				}
			}
		return foundXlink;
	}
}
