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
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

import edu.harvard.hul.ois.ots.schemas.XmlContent.SimpleLink;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.premis.LinkingAgentIdentifier;
import edu.harvard.hul.ois.ots.schemas.premis.LinkingObjectIdentifier;
import edu.harvard.hul.ois.ots.schemas.premis.Premis;
import edu.harvard.hul.ois.ots.schemas.premis.PremisDate;
import edu.harvard.hul.ois.ots.schemas.premis.PremisDateException;

public class RightsStatement {

	//statement identifier
	private String statementIdentifierType;
	private String statementIdentifierValue;
	private SimpleLink statementIdentifierXlink;
	
	private String basis;
	
	//copyright
	private String copyrightStatus;
	private String copyrightJurisdiction;
	private PremisDate copyrightStatusDeterminationDate;
	private List<String> copyrightNotes;
	
	//license
	private String licenseIdentifierType;
	private String licenseIdentifierValue;
	private String licenseTerms;
	private List<String> licenseNotes;
	
	private List<StatuteInformation> statuteInformations;
	private List<RightsGranted> rightsGranted;
	private List<LinkingObjectIdentifier> linkingObjectIdentifiers;
	private List<LinkingAgentIdentifier> linkingAgentIdentifiers;
	
	public RightsStatement() { 
		licenseNotes = new ArrayList<String>();
		copyrightNotes = new ArrayList<String>();
		statuteInformations = new ArrayList<StatuteInformation>();
		rightsGranted = new ArrayList<RightsGranted>();
		linkingAgentIdentifiers = new ArrayList<LinkingAgentIdentifier>();
		linkingObjectIdentifiers = new ArrayList<LinkingObjectIdentifier>();
		copyrightStatusDeterminationDate = new PremisDate();
	}
	
	public RightsStatement(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this();
		parse(reader);
	}
	
	public String getStatementIdentifierType() {
		return statementIdentifierType;
	}
	public void setStatementIdentifierType(String statementIdentifierType) {
		this.statementIdentifierType = statementIdentifierType;
	}
	public String getStatementIdentifierValue() {
		return statementIdentifierValue;
	}
	public void setStatementIdentifierValue(String statementIdentifierValue) {
		this.statementIdentifierValue = statementIdentifierValue;
	}
	public SimpleLink getStatementIdentifierXlink() {
		return statementIdentifierXlink;
	}
	public void setStatementIdentifierXlink(SimpleLink statementIdentifierXlink) {
		this.statementIdentifierXlink = statementIdentifierXlink;
	}
	public String getBasis() {
		return basis;
	}
	public void setBasis(String basis) {
		this.basis = basis;
	}
	public String getCopyrightStatus() {
		return copyrightStatus;
	}
	public void setCopyrightStatus(String copyrightInformationStatus) {
		this.copyrightStatus = copyrightInformationStatus;
	}
	public String getCopyrightJurisdiction() {
		return copyrightJurisdiction;
	}
	public void setCopyrightJurisdiction(String copyrightJurisdiction) {
		this.copyrightJurisdiction = copyrightJurisdiction;
	}
	public PremisDate getCopyrightStatusDeterminationDate() {
		return copyrightStatusDeterminationDate;
	}
	public void setCopyrightStatusDeterminationDate(PremisDate copyrightStatusDeterminationDate) {
		this.copyrightStatusDeterminationDate = copyrightStatusDeterminationDate;
	}
	public void setCopyrightStatusDeterminationDate(String date) throws PremisDateException {
		copyrightStatusDeterminationDate.setValue(date);
	}
	public List<String> getCopyrightInformationNote() {
		return copyrightNotes;
	}
	public void setCopyrightNotes(List<String> copyrightNotes) {
		this.copyrightNotes = copyrightNotes;
	}
	public void addCopyrightNote(String note) {
		copyrightNotes.add(note);
	}
	public String getLicenseIdentifierType() {
		return licenseIdentifierType;
	}
	public void setLicenseIdentifierType(String licenseIdentifierType) {
		this.licenseIdentifierType = licenseIdentifierType;
	}
	public String getLicenseIdentifierValue() {
		return licenseIdentifierValue;
	}
	public void setLicenseIdentifierValue(String licenseIdentifierValue) {
		this.licenseIdentifierValue = licenseIdentifierValue;
	}
	public String getLicenseTerms() {
		return licenseTerms;
	}
	public void setLicenseTerms(String licenseTerms) {
		this.licenseTerms = licenseTerms;
	}
	public List<String> getLicenseNotes() {
		return licenseNotes;
	}
	public void setLicenseNotes(List<String> licenseNotes) {
		this.licenseNotes = licenseNotes;
	}
	public void addLicenseNote(String note) {
		licenseNotes.add(note);
	}
	public List<StatuteInformation> getStatuteInformations() {
		return statuteInformations;
	}
	public void setStatuteInformations(List<StatuteInformation> statuteInformations) {
		this.statuteInformations = statuteInformations;
	}
	public void addStatuteInformation(StatuteInformation info) {
		statuteInformations.add(info);
	}
	public List<RightsGranted> getRightsGranted() {
		return rightsGranted;
	}
	public void setRightsGranted(List<RightsGranted> rightsGranted) {
		this.rightsGranted = rightsGranted;
	}
	public void addRightsGranted(RightsGranted rights) {
		rightsGranted.add(rights);
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

	
	public void output(XMLStreamWriter writer, SMOutputElement parent) throws XMLStreamException {
		SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
		SMOutputElement rightsStatement = frag.addElement(Premis.ns,"rightsStatement");
		//rights statement
		if(statementIdentifierType != null || statementIdentifierValue != null ||
				statementIdentifierXlink != null) {
			SMOutputElement rightsStmtIdent = rightsStatement.addElement(Premis.ns,"rightsStatementIdentifier");
			if(statementIdentifierXlink != null) {
				statementIdentifierXlink.output(writer);
			}
			if(statementIdentifierType != null) {
				rightsStmtIdent.addElementWithCharacters(Premis.ns,"rightsStatementIdentifierType", statementIdentifierType);
			}
			if(statementIdentifierValue != null) {
				rightsStmtIdent.addElementWithCharacters(Premis.ns,"rightsStatementIdentifierValue", statementIdentifierValue);
			}
		}
		
		if(basis != null) {
			rightsStatement.addElementWithCharacters(Premis.ns,"rightsBasis",basis);
		}
		//copyright
		if(copyrightStatus != null || copyrightJurisdiction != null || 
				copyrightStatusDeterminationDate.toString() != null || copyrightNotes.size() > 0) {
			SMOutputElement copyrightInfo = rightsStatement.addElement(Premis.ns,"copyrightInformation");
			if(copyrightStatus != null) {
				copyrightInfo.addElementWithCharacters(Premis.ns,"copyrightStatus",copyrightStatus);
			}
			if(copyrightJurisdiction != null) {
				copyrightInfo.addElementWithCharacters(Premis.ns,"copyrightJurisdiction",copyrightJurisdiction);
			}
			if(copyrightStatusDeterminationDate.toString() != null) {
				copyrightInfo.addElementWithCharacters(Premis.ns,"copyrightStatusDeterminationDate",copyrightStatusDeterminationDate.toString());
			}
			for(String note : copyrightNotes) {
				copyrightInfo.addElementWithCharacters(Premis.ns,"copyrightNote",note);
			}
		}
		
		//license
		if(licenseIdentifierType != null || licenseIdentifierValue != null || licenseTerms != null ||
				licenseNotes.size() > 0) {
			SMOutputElement licenseInfo = rightsStatement.addElement(Premis.ns,"licenseInformation");
			if(licenseIdentifierType != null || licenseIdentifierValue != null) {
				SMOutputElement licenseIdent = licenseInfo.addElement(Premis.ns,"licenseIdentifier");
				if(licenseIdentifierType != null) {
					licenseIdent.addElementWithCharacters(Premis.ns,"licenseIdentifierType", licenseIdentifierType);
				}
				if(licenseIdentifierValue != null) {
					licenseIdent.addElementWithCharacters(Premis.ns,"licenseIdentifierValue", licenseIdentifierValue);
				}
			}
			if(licenseTerms != null) {
				licenseInfo.addElementWithCharacters(Premis.ns, "licenseTerms",licenseTerms);
			}
			for(String note : licenseNotes) {
				licenseInfo.addElementWithCharacters(Premis.ns, "licenseNote",note);
			}
		}
		for(StatuteInformation statuteInfo : statuteInformations) {
			statuteInfo.output(rightsStatement);
		}
		for(RightsGranted grantedRight : rightsGranted) {
			grantedRight.output(rightsStatement);
		}
		for(LinkingObjectIdentifier linkingObjIdent : linkingObjectIdentifiers) {
			linkingObjIdent.output(writer,rightsStatement);
		}
		for(LinkingAgentIdentifier linkingAgentIdent: linkingAgentIdentifiers) {
			linkingAgentIdent.output(writer,rightsStatement);
		}
		frag.closeRoot();
	}
	
	public RightsStatement parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		int event = reader.getEventType();
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("rightsStatement"))) {	
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();
				if(localName.equals("rightsStatementIdentifier")) {
					if(reader.getAttributeCount() > 0) {
						statementIdentifierXlink = new SimpleLink(reader);
					}
				}
				else if(localName.equals("rightsStatementIdentifierType")) {
					statementIdentifierType = reader.getElementText();
				}
				else if(localName.equals("rightsStatementIdentifierValue")) {
					statementIdentifierValue = reader.getElementText();
				}
				else if(localName.equals("rightsBasis")) {
					basis = reader.getElementText();
				}
				//copyright
				else if(localName.equals("copyrightStatus")) {
					copyrightStatus = reader.getElementText();
				}
				else if(localName.equals("copyrightJurisdiction")) {
					copyrightJurisdiction = reader.getElementText();
				}
				else if(localName.equals("copyrightStatusDeterminationDate")) {
					setCopyrightStatusDeterminationDate(reader.getElementText());
				}
				else if(localName.equals("copyrightNote")) {
					copyrightNotes.add(reader.getElementText());
				}
				//license
				else if(localName.equals("licenseIdentifierType")) {
					licenseIdentifierType = reader.getElementText();
				}
				else if(localName.equals("licenseIdentifierValue")) {
					licenseIdentifierValue = reader.getElementText();
				}
				else if(localName.equals("licenseTerms")) {
					licenseTerms = reader.getElementText();
				}
				else if(localName.equals("licenseNote")) {
					licenseNotes.add(reader.getElementText());
				}
				else if(localName.equals("statuteInformation")) {
					statuteInformations.add(new StatuteInformation(reader));
				}
				else if(localName.equals("rightsGranted")) {
					rightsGranted.add(new RightsGranted(reader));
				}
				else if(localName.equals("linkingObjectIdentifier")) {
					linkingObjectIdentifiers.add(new LinkingObjectIdentifier(reader));
				}
				else if(localName.equals("linkingAgentIdentifier")) {
					linkingAgentIdentifiers.add(new LinkingAgentIdentifier(reader));
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
