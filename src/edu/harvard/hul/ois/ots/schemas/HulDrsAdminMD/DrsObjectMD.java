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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputDocument;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

import edu.harvard.hul.ois.ots.schemas.StaxUtils.RootXmlHelper;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlDateFormat;


public class DrsObjectMD extends HulDrsAdminMD {
	
	public static enum Role {COLOR_PROFILE,DOCUMENTATION,DONOR_AGREEMENT,FINDING_AID,LICENSE,SCHEMA,CODE,WORLD_LOCATION}
	public static enum CaptionBehavior {on,off}
			
	public static enum ContentModel {
		OPAQUE("CMID-1.0","Opaque"),
		AUDIO("CMID-2.0","Audio"),
		AUDIOSIMPLE("CMID-2.1","Audio Simple"),
		WEB_HARVEST("CMID-3.0","Web Harvest"),
		PDS_DOCUMENT("CMID-4.0","PDS Document"),
		DOCUMENT("CMID-4.1","Document"),
		DARK_PDS_DOCUMENT("CMID-4.2","Dark PDS Document"),
		DARK_GOOGLE_DOCUMENT_1("CMID-4.3","Dark Google Document 1, Google 0.1 Container"),
		DARK_GOOGLE_DOCUMENT_2("CMID-4.4","Dark Google Document 2, Google 1.0 Container"),
		MOA2_DOCUMENT("CMID-4.5","MOA2 Document"),
		DARK_GOOGLE_DOCUMENT_3("CMID-4.6","Dark Google Document 3, Google 1.1 container"),
		STILL_IMAGE("CMID-5.0","Still Image"),
		BIOMEDICAL_IMAGE("CMID-5.1","Biomedial Image"),
		TARGET_IMAGE("CMID-5.2","Target Image"),
		TEXT("CMID-6.0","Text"),
		VECTOR_STILL_IMAGE("CMID-6.0","Vector Still Image, Drawing"),
		DRS_AGENT("CMID-8.0","DRS Agent");
		
		private final String id;
		private final String alias;
		ContentModel(String id, String alias) {
			this.id = id;
			this.alias = alias;
		}
		public String id() { return id; }
		public String alias() { return alias; }
	}
	
	private GenericVersionedElement ownerCode;
	private GenericVersionedElement billingCode;	
	private String captionBehavior;
	private String captionDefault;
	private String captionUnitName;
	private String captionDescription;
	private String contentModelID;
	private List<DrsObjectMD.Role> roles;
	
	private boolean isRoot;
	
	public DrsObjectMD() {
		this(false);
	}
	
	public DrsObjectMD(boolean isRoot) {
		super();
		roles = new ArrayList<DrsObjectMD.Role>();
		billingCode = new GenericVersionedElement();
		ownerCode = new GenericVersionedElement();
		this.isRoot = isRoot;
	}
		
	public DrsObjectMD(XMLStreamReader reader, boolean isRoot) throws XMLStreamException {
		this(isRoot);
		parse(reader);
	}
	
	public DrsObjectMD(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}
	
	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
		
	public GenericVersionedElement getOwnerCode() {
		return ownerCode;
	}

	public GenericVersionedElement getBillingCode() {
		return billingCode;
	}

	public String getCaptionBehavior() {
		return captionBehavior;
	}

	public void setCaptionBehavior(String captionBehavior) {
		this.captionBehavior = captionBehavior;
	}

	public void setOwnerCode(String value, String agent) {
		ownerCode.setValue(value, agent);
	}

	public void setBillingCode(String value, String agent) {
		billingCode.setValue(value, agent);
	}

	public String getCaptionUnitName() {
		return captionUnitName;
	}

	public void setCaptionUnitName(String captionUnitName) {
		this.captionUnitName = captionUnitName;
	}

	public String getCaptionDefault() {
		return captionDefault;
	}

	public void setCaptionDefault(String captionDefault) {
		this.captionDefault = captionDefault;
	}

	public String getCaptionDescription() {
		return captionDescription;
	}

	public void setCaptionDescription(String captionDescription) {
		this.captionDescription = captionDescription;
	}

	public String getContentModelID() {
		return contentModelID;
	}

	public void setContentModelID(String contentModelID) {
		this.contentModelID = contentModelID;
	}

	public Date getInsertionDate() {
		return insertionDate;
	}

	public void setInsertionDate(Date insertionDate) {
		this.insertionDate = insertionDate;
	}

	public Date getLatestModificationDate() {
		return latestModificationDate;
	}

	public void setLastModification(Date latestModificationDate) {
		this.latestModificationDate = latestModificationDate;
	}

	public String getOwnerSuppliedName() {
		return ownerSuppliedName;
	}

	public void setOwnerSuppliedName(String ownerSuppliedName) {
		this.ownerSuppliedName = ownerSuppliedName;
	}
	
	public List<DrsObjectMD.Role> getRoles() {
		return roles;
	}

	public void setRoles(List<DrsObjectMD.Role> role) {
		this.roles = role;
	}
	
	/**
	 * Adds a role if it doesn't already exist
	 * @param role
	 */
	public void addRole(DrsObjectMD.Role role) {
		if(!roles.contains(role)) {
			this.roles.add(role);
		}
	}
	
	@Override
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		if(isRoot) {
			SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer);	
			SMNamespace ns = doc.getNamespace(XMLNS,nsSchemaContext.getPrefix());
			SMOutputElement root = doc.addElement(ns,"hulDrsAdmin");			
			RootXmlHelper.output(writer,getAllNamespaceSchemaContexts(),root);
			outputContents(writer,ns,root);
			doc.closeRoot();
		}
		else {
			SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
			SMNamespace ns = frag.getNamespace(XMLNS,nsSchemaContext.getPrefix());
			SMOutputElement fragRoot = frag.addElement(ns,"hulDrsAdmin");
			outputContents(writer,ns,fragRoot);
			frag.closeRoot();
		}	
	}
	
	private void outputContents(XMLStreamWriter writer, SMNamespace ns,SMOutputElement obj) throws XMLStreamException {
		SMOutputElement drsObject = obj.addElement(ns, "drsObject");
		if(accessFlag != null) {
			accessFlag.output(drsObject,ns, "accessFlag");
		}
		for(AdminCategory category : adminCategories) {
			category.output(drsObject, ns);
		}
		if(adminFlags != null) {
			for(AdminFlag flag : adminFlags) {
				flag.output(drsObject,ns);
			}
		}
		if(billingCode != null) {
			billingCode.output(drsObject, ns, "billingCode");
		}
		if(captionBehavior != null) {
			drsObject.addElementWithCharacters(ns, "captionBehavior", captionBehavior);
		}
		if(captionDefault != null) {
			drsObject.addElementWithCharacters(ns, "captionDefault", captionDefault);
		}
		if(captionUnitName != null) {
			drsObject.addElementWithCharacters(ns, "captionUnitName", captionUnitName);
		}
		if(captionDescription != null) {
			drsObject.addElementWithCharacters(ns, "captionDescription", captionDescription);
		}
		if(contentModelID != null) {
			drsObject.addElementWithCharacters(ns, "contentModelID", contentModelID);
		}
		if(insertionDate != null) {
			drsObject.addElementWithCharacters(ns, "insertionDate", XmlDateFormat.formatDateTime(insertionDate));
		}
		if(latestModificationDate != null) {
			drsObject.addElementWithCharacters(ns, "latestModificationDate", XmlDateFormat.formatDateTime(latestModificationDate));
		}
		if(methodology != null) {
			drsObject.addElementWithCharacters(ns, "methodology", methodology);
		}
		if(ownerCode != null) {
			ownerCode.output(drsObject, ns, "ownerCode");
		}
		if(ownerSuppliedName != null) {
			drsObject.addElementWithCharacters(ns, "ownerSuppliedName", ownerSuppliedName);
		}
		for(DrsObjectMD.Role role : roles) {
			drsObject.addElementWithCharacters(ns, "role", role.toString());
		}
		if(status != null) {
			drsObject.addElementWithCharacters(ns, "Status", status);
		}
		for(DrsURI uri : URIs){
			uri.output(drsObject,ns);
		}
	}


	@Override
	public HulDrsAdminMD parse(XMLStreamReader reader) throws XMLStreamException {
		int event = reader.getEventType();
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("hulDrsAdmin"))) {	
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();
				if(localName.equals("accessFlag")) {
					accessFlag.parse(reader);
				}
				else if(localName.equals("adminCategory")) {
					adminCategories.add(new AdminCategory(reader));
				}
				else if(localName.equals("adminFlag")) {
					AdminFlag flag = new AdminFlag();
					flag.parse(reader);
					adminFlags.add(flag);
				}
				else if(localName.equals("billingCode")) {
					billingCode.parse(reader);
				}
				else if(localName.equals("captionBehavior")) {
					captionBehavior = reader.getElementText();
				}
				else if(localName.equals("captionDefault")) {
					captionDefault = reader.getElementText();
				}
				else if(localName.equals("captionUnitName")) {
					captionUnitName = reader.getElementText();
				}
				else if(localName.equals("captionDescription")) {
					captionDescription = reader.getElementText();
				}
				else if(localName.equals("contentModelID")) {
					contentModelID = reader.getElementText();
				}	
				else if(localName.equals("insertionDate")) {
					try {
						insertionDate = XmlDateFormat.parseDateTime(reader.getElementText());
					} catch (ParseException e) {
						throw new XMLStreamException("Invalid Date: "+reader.getElementText(),e);
					}
				}
				else if(localName.equals("latestModificationDate")) {
					try {
						latestModificationDate = XmlDateFormat.parseDateTime(reader.getElementText());
					} catch (ParseException e) {
						throw new XMLStreamException("Invalid Date: "+reader.getElementText(),e);
					}
				}			
				else if(localName.equals("methodology")) {
					methodology = reader.getElementText();
				}
				else if(localName.equals("ownerCode")) {
					ownerCode.parse(reader);
				}
				else if(localName.equals("ownerSuppliedName")) {
					ownerSuppliedName = reader.getElementText();
				}
				else if(localName.equals("role")) {
					roles.add(DrsObjectMD.Role.valueOf(reader.getElementText()));
				}
				else if(localName.equals("Status")) {
					status = reader.getElementText();
				}
				else if(localName.equals("URI")) {
					URIs.add(new DrsURI(reader));
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
