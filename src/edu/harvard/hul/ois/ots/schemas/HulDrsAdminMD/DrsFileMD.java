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
import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlDateFormat;


public class DrsFileMD extends HulDrsAdminMD {
	
	public static enum Role {ARCHIVAL_MASTER,CONTAINER,DELIVERABLE,DOCUMENTATION,LICENSE,LOG,ORIGINAL_ORDER,
		OBJECT_DESCRIPTOR,PAGE_COORDINATES,PAGE_IMAGE,PAGE_TEXT,PRODUCTION_MASTER,STYLE_SHEET,
		TARGET_DESCRIPTION,TARGET_IMAGE,THUMBNAIL}

	public static enum IsFirstGenerationInDrs {yes,no,unspecified}
	public static enum IsPreferredDeliverableSource {yes,no,unspecified}
	
    public final static int[] QUALITY = {1,2,3,4,5,6,7,8,9,10};
	
	public static enum UsageClass {HIGHUSE,LOWUSE}
	
	private FileIdentification fileIdentification;
	private FormatValidation validation;
	private Boolean firstGenerationInDrs;
	private Boolean preferredDeliverableSource;
	private List<String> processing;
	private Integer quality;
	
	private List<DrsFileMD.Role> roles;
	private String suppliedFilename;
	private String suppliedDirectory;
	private String usageClass;
	
	private boolean isRoot;
	
	public DrsFileMD() {
		this(false);
	}
	
	public DrsFileMD(boolean isRoot) {
		super();
		roles = new ArrayList<DrsFileMD.Role>();
		processing = new ArrayList<String>();
		fileIdentification = new FileIdentification();
		//validation = new FormatValidation();
		this.isRoot = isRoot;
	}
	
	public DrsFileMD(XMLStreamReader reader, boolean isRoot) throws XMLStreamException {
		this(isRoot);
		parse(reader);
	}
	
	public DrsFileMD(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}
	
	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public Date getInsertionDate() {
		return insertionDate;
	}

	public void setInsertionDate(Date insertionDate) {
		this.insertionDate = insertionDate;
	}

	public Boolean isFirstGenerationInDrs() {
		return firstGenerationInDrs;
	}

	public void setFirstGenerationInDrs(Boolean firstGenerationInDrs) {
		this.firstGenerationInDrs = firstGenerationInDrs;
	}

	public Boolean isPreferredDeliverableSource() {
		return preferredDeliverableSource;
	}

	public void setPreferredDeliverableSource(Boolean preferredDeliverableSource) {
		this.preferredDeliverableSource = preferredDeliverableSource;
	}

	public Date getLatestModificationDate() {
		return latestModificationDate;
	}

	public void setLatestModificationDate(Date latestModificationDate) {
		this.latestModificationDate = latestModificationDate;
	}

	public String getOwnerSuppliedName() {
		return ownerSuppliedName;
	}

	public void setOwnerSuppliedName(String ownerSuppliedName) {
		this.ownerSuppliedName = ownerSuppliedName;
	}

	public List<String> getProcessing() {
		return processing;
	}

	public void setProcessing(List<String> processing) {
		this.processing = processing;
	}
	
	public void addProcessing(String processing) {
		this.processing.add(processing);
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public List<DrsFileMD.Role> getRoles() {
		return roles;
	}

	public void setRoles(List<DrsFileMD.Role> role) {
		this.roles = role;
	}
	
	/**
	 * Adds a role if it doesn't already exist
	 * @param role
	 */
	public void addRole(DrsFileMD.Role role) {
		if(!roles.contains(role)) {
			this.roles.add(role);
		}
	}

	public String getSuppliedFileName() {
		return suppliedFilename;
	}

	public void setSuppliedFileName(String suppliedFileName) {
		this.suppliedFilename = suppliedFileName;
	}

	public String getSuppliedDirectory() {
		return suppliedDirectory;
	}

	public void setSuppliedDirectory(String suppliedDirectory) {
		this.suppliedDirectory = suppliedDirectory;
	}

	public String getUsageClass() {
		return usageClass;
	}

	public void setUsageClass(String usageClass) {
		this.usageClass = usageClass;
	}
		

	public FileIdentification getFileIdentification() {
		return fileIdentification;
	}

	public void setFileIdentification(FileIdentification fileIdentification) {
		this.fileIdentification = fileIdentification;
	}
	
	public FormatValidation getFormatValidation() {
		return validation;
	}

	public void setFormatValidation(FormatValidation formatValidation) {
		this.validation = formatValidation;
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
		SMOutputElement drsFile = obj.addElement(ns, "drsFile");
		if(accessFlag != null) {
			accessFlag.output(drsFile,ns, "accessFlag");
		}
		for(AdminCategory category : adminCategories) {
			category.output(drsFile, ns);
		}
		if(adminFlags != null) {
			for(AdminFlag flag : adminFlags) {
				flag.output(drsFile,ns);
			}
		}
		if(fileIdentification != null) {
			fileIdentification.output(drsFile,ns);
		}
		if(validation != null) {
			validation.output(drsFile,ns);
		}
		if(insertionDate != null) {
			drsFile.addElementWithCharacters(ns, "insertionDate", XmlDateFormat.formatDateTime(insertionDate));
		}
		if(firstGenerationInDrs != null && firstGenerationInDrs) {
			drsFile.addElementWithCharacters(ns, "isFirstGenerationInDrs", "yes");
		}
		else if(firstGenerationInDrs != null && !firstGenerationInDrs){
			drsFile.addElementWithCharacters(ns, "isFirstGenerationInDrs", "no");
		}
		else {
			drsFile.addElementWithCharacters(ns, "isFirstGenerationInDrs", "unspecified");
		}
		
		if(preferredDeliverableSource != null && preferredDeliverableSource) {
			drsFile.addElementWithCharacters(ns, "isPreferredDeliverableSource", "yes");
		}
		else if(preferredDeliverableSource != null && !preferredDeliverableSource) {
			drsFile.addElementWithCharacters(ns, "isPreferredDeliverableSource", "no");
		}
		if(latestModificationDate != null) {
			drsFile.addElementWithCharacters(ns, "latestModificationDate", XmlDateFormat.formatDateTime(latestModificationDate));
		}
		if(methodology != null) {
			drsFile.addElementWithCharacters(ns, "methodology", methodology);
		}
		if(ownerSuppliedName != null) {
			drsFile.addElementWithCharacters(ns, "ownerSuppliedName", ownerSuppliedName);
		}
		for(String process : processing) {
			drsFile.addElementWithCharacters(ns, "processing", process);
		}
		if(quality != null) {
			drsFile.addElementWithCharacters(ns, "quality", String.valueOf(quality));
		}
		for(DrsFileMD.Role role : roles) {
			drsFile.addElementWithCharacters(ns, "role", role.toString());
		}
		if(status != null) {
			drsFile.addElementWithCharacters(ns, "Status", status);
		}
		if(suppliedFilename != null) {
			drsFile.addElementWithCharacters(ns, "suppliedFilename", suppliedFilename);
		}
		if(suppliedDirectory != null) {
			drsFile.addElementWithCharacters(ns, "suppliedDirectory", suppliedDirectory);
		}
		for(DrsURI uri : URIs){
			uri.output(drsFile,ns);
		}
		if(usageClass != null) {
			drsFile.addElementWithCharacters(ns, "usageClass", usageClass);
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
				else if(localName.equals("fileIdentification")) {
					fileIdentification.parse(reader);
				}
				else if(localName.equals(FormatValidation.ELEM_NAME)) {
					validation = new FormatValidation();
					validation.parse(reader);
				}
				else if(localName.equals("insertionDate")) {
					try {
						insertionDate = XmlDateFormat.parseDateTime(reader.getElementText());
					} catch (ParseException e) {
						throw new XMLStreamException("Invalid Date: "+reader.getElementText(),e);
					}
				}
				else if(localName.equals("isFirstGenerationInDrs")) {
					String value = reader.getElementText();
					if(value.equals("yes")) {
						firstGenerationInDrs = true;
					}
					else if(value.equals("no")) {
						firstGenerationInDrs = false;
					}
				}
				else if(localName.equals("isPreferredDeliverableSource")) {
					String value = reader.getElementText();
					if(value.equals("yes")) {
						preferredDeliverableSource = true;
					}
					else if(value.equals("no")) {
						preferredDeliverableSource = false;
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
				else if(localName.equals("ownerSuppliedName")) {
					ownerSuppliedName = reader.getElementText();
				}
				else if(localName.equals("processing")) {
					processing.add(reader.getElementText());
				}
				else if(localName.equals("quality")) {
					quality = new Integer(reader.getElementText());
				}
				else if(localName.equals("role")) {
					roles.add(DrsFileMD.Role.valueOf(reader.getElementText()));
				}
				else if(localName.equals("Status")) {
					status = reader.getElementText();
				}
				else if(localName.equals("suppliedFilename")) {
					suppliedFilename = reader.getElementText();
				}
				else if(localName.equals("suppliedDirectory")) {
					suppliedDirectory = reader.getElementText();
				}
				else if(localName.equals("URI")) {
					URIs.add(new DrsURI(reader));
				}
				else if(localName.equals("usageClass")) {
					usageClass = reader.getElementText();
				}				
				break;
			case END_ELEMENT:
				localName = reader.getLocalName();
				break;								
			}
		}		
		return this;
	}

	@Override
	public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
		List<NamespaceSchemaContext> contexts = new ArrayList<NamespaceSchemaContext>();	
		//Add namespace schema context for this object
		contexts.add(nsSchemaContext);
		//check if there is an identity block and add FITS
		if(fileIdentification != null && fileIdentification.getIdentities().size() > 0) {
			contexts.add(fileIdentification.getNamespaceSchemaContext());
		}
		if(validation != null) {
			contexts.add(validation.getNamespaceSchemaContext());
		}
		return contexts;
	}
	
}
