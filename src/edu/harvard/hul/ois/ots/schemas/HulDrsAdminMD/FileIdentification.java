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

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD.FITS.ExternalIdentifier;
import edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD.FITS.Identity;
import edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD.FITS.Tool;
import edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD.FITS.Version;
import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;

public class FileIdentification {
	
	private String status;
	private List<Identity> identities = new ArrayList<Identity>();
	
	public static final String XMLNS = "http://hul.harvard.edu/ois/xml/ns/fits/fits_output";
	public static final String XML_DEFAULT_PREFIX = "fits";
	public static final String SCHEMA_LOCATION = "http://hul.harvard.edu/ois/xml/xsd/fits/fits_output.xsd";	
			
	private NamespaceSchemaContext nsSchemaContext = new NamespaceSchemaContext(XMLNS, XML_DEFAULT_PREFIX, SCHEMA_LOCATION);

	
	public FileIdentification() { 	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Identity> getIdentities() {
		return identities;
	}

	public void setIdentities(List<Identity> identities) {
		this.identities = identities;
	}

	public void addIdentity(Identity identity) {
		identities.add(identity);
	}
	
	public void output(SMOutputElement parent, SMNamespace ns) throws XMLStreamException {
		SMOutputElement identification = parent.addElement(ns, "fileIdentification");
		if(status != null) {
			identification.addAttribute("status",status);
		}
		for(Identity ident : identities) {
			SMNamespace fitsNS = identification.getNamespace(XMLNS,nsSchemaContext.getPrefix());
			SMOutputElement identity = identification.addElement(fitsNS, "identity");
			if(ident.getFormat() != null) {
				identity.addAttribute("format",ident.getFormat());
			}
			if(ident.getMimetype() != null) {
				identity.addAttribute("mimetype",ident.getMimetype());
			}
			for(Tool tool : ident.getTools()) {
				SMOutputElement toolElement = identity.addElement(fitsNS, "tool");
				if(tool.getToolName() != null) {
					toolElement.addAttribute("toolname",tool.getToolName());
				}
				if(tool.getToolVersion() != null) {
					toolElement.addAttribute("toolversion",tool.getToolVersion());
				}
			}
			for(Version version : ident.getVersions()) {
				SMOutputElement versionElement = identity.addElement(fitsNS, "version");
				if(version.getStatus() != null) {
					versionElement.addAttribute("status",version.getStatus());
				}
				if(version.getToolName() != null) {
					versionElement.addAttribute("toolname",version.getToolName());
				}
				if(version.getToolVersion() != null) {
					versionElement.addAttribute("toolversion",version.getToolVersion());
				}
				versionElement.addCharacters(version.getValue());
			}
			for(ExternalIdentifier externalID : ident.getExternalIdentifiers()) {
				SMOutputElement xIDElement = identity.addElement(fitsNS, "externalIdentifier");
				if(externalID.getToolName() != null) {
					xIDElement.addAttribute("toolname",externalID.getToolName());
				}
				if(externalID.getToolVersion() != null) {
					xIDElement.addAttribute("toolversion",externalID.getToolVersion());
				}
				if(externalID.getType() != null) {
					xIDElement.addAttribute("type",externalID.getType());
				}	
				xIDElement.addCharacters(externalID.getValue());
			}
		}
	}

	public FileIdentification parse(XMLStreamReader reader) throws XMLStreamException {
		int event = reader.getEventType();
		String localName = "";
		status = reader.getAttributeValue(null,"status");
		while(!(event == END_ELEMENT && localName.equals("fileIdentification"))) {	
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();
				if(localName.equals("identity")) {
					Identity identity = new Identity();
					identity.setFormat(reader.getAttributeValue(null,"format"));
					identity.setMimetype(reader.getAttributeValue(null,"mimetype"));
					while(!(event == END_ELEMENT && localName.equals("identity"))) {	
						event = reader.next();
						switch(event) {
						case START_ELEMENT:
							localName = reader.getLocalName();
							if(localName.equals("tool")) {
								Tool tool = new Tool();
								tool.setToolName(reader.getAttributeValue(null,"toolname"));
								tool.setToolVersion(reader.getAttributeValue(null,"toolversion"));
								identity.addTool(tool);
							}
							else if(localName.equals("version")) {
								Version version = new Version();
								version.setStatus(reader.getAttributeValue(null,"status"));
								version.setToolName(reader.getAttributeValue(null,"toolname"));
								version.setToolVersion(reader.getAttributeValue(null,"toolversion"));
								version.setValue(reader.getElementText());
								identity.addVersion(version);
							}
							else if(localName.equals("externalIdentifier")) {
								ExternalIdentifier xID = new ExternalIdentifier();
								xID.setToolName(reader.getAttributeValue(null,"toolname"));
								xID.setToolVersion(reader.getAttributeValue(null,"toolversion"));
								xID.setType(reader.getAttributeValue(null,"type"));
								xID.setValue(reader.getElementText());
								identity.addExternalIdentifier(xID);
							}
							break;
						case END_ELEMENT:
							localName = reader.getLocalName();
							break;
						}
					}
					identities.add(identity);
				}
				break;
			case END_ELEMENT:
				localName = reader.getLocalName();
				break;
			}
		}
		return this;
	}
	
	public NamespaceSchemaContext getNamespaceSchemaContext() {
		return nsSchemaContext;
	}
}
