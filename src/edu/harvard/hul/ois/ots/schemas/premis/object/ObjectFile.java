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
import static javax.xml.stream.XMLStreamConstants.START_DOCUMENT;

import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputDocument;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

import edu.harvard.hul.ois.ots.schemas.XmlComponent;
import edu.harvard.hul.ois.ots.schemas.StaxUtils.RootXmlHelper;
import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;
import edu.harvard.hul.ois.ots.schemas.XmlContent.SimpleLink;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.premis.Premis;



public class ObjectFile extends PremisObject {

	public static final String type = "file";

	private String originalName;
	private SimpleLink originalNameXlink;
	private List<PreservationLevel> preservationLevels;
	private List<ObjectCharacteristics> objectCharacteristics;
	private List<Storage> storages;
	private List<SignatureInformation> signatureInformations;
	
	public ObjectFile() {
		this(false);
	}
	
	public ObjectFile(boolean isRoot) {
		super(isRoot);
		preservationLevels = new ArrayList<PreservationLevel>();
		objectCharacteristics = new ArrayList<ObjectCharacteristics>();
		storages = new ArrayList<Storage>();
		signatureInformations = new ArrayList<SignatureInformation>();
	}
	
	public ObjectFile(XMLStreamReader reader, boolean isRoot) throws XMLStreamException, XmlContentException {
		this(isRoot);
		parse(reader);
	}
	
	public ObjectFile(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this(false);
		parse(reader);
	}
	
	public List<PreservationLevel> getPreservationLevels() {
		return preservationLevels;
	}

	public void setPreservationLevels(List<PreservationLevel> preservationLevels) {
		this.preservationLevels = preservationLevels;
	}
	
	public void addPreservationLevel(PreservationLevel level) {
		preservationLevels.add(level);
	}
	
	public List<ObjectCharacteristics> getObjectCharacteristics() {
		return objectCharacteristics;
	}

	public void setObjectCharacteristics(
			List<ObjectCharacteristics> objectCharacteristics) {
		this.objectCharacteristics = objectCharacteristics;
	}
	
	public void addObjectCharacteristics(ObjectCharacteristics objectChar) {
		objectCharacteristics.add(objectChar);
	}

	public List<Storage> getStorage() {
		return storages;
	}

	public void setStorage(List<Storage> storage) {
		this.storages = storage;
	}
	
	public void addStorage(Storage storage) {
		this.storages.add(storage);
	}

	public List<SignatureInformation> getSignatureInformation() {
		return signatureInformations;
	}

	public void setSignatureInformation(List<SignatureInformation> signatureInformation) {
		this.signatureInformations = signatureInformation;
	}
	
	public void addSignature(SignatureInformation signature) {
		signatureInformations.add(signature);
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public SimpleLink getOriginalNameXlink() {
		return originalNameXlink;
	}

	public void setOriginalNameXlink(SimpleLink originalNameXlink) {
		this.originalNameXlink = originalNameXlink;
	}

	public List<PreservationLevel> getPreservationLevel() {
		return preservationLevels;
	}

	public void setPreservationLevel(List<PreservationLevel> preservationLevel) {
		this.preservationLevels = preservationLevel;
	}
	
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		if(isRoot) {
			SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer,"1.0","UTF-8",false);	
			Premis.ns = doc.getNamespace(Premis.XMLNS,Premis.nsSchemaContext.getPrefix());
			SMOutputElement root = doc.addElement(Premis.ns,"object");			
			RootXmlHelper.output(writer,getAllNamespaceSchemaContexts(),root);
			outputContents(writer,root);
			doc.closeRoot();
		}
		else {
			SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
			Premis.ns = frag.getNamespace(Premis.XMLNS,Premis.nsSchemaContext.getPrefix());
			SMOutputElement fragRoot = frag.addElement(Premis.ns,"object");
			outputContents(writer,fragRoot);
			frag.closeRoot();
		}	
	}
	
	private void outputContents(XMLStreamWriter writer,SMOutputElement obj) throws XMLStreamException {
		SMNamespace xsi = obj.getNamespace(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,Premis.SCHEMA_INSTANCE_PREFIX);
		if(Premis.nsSchemaContext.getPrefix() != null) {
			obj.addAttribute(xsi, "type",Premis.nsSchemaContext.getPrefix()+":"+ type);
		}
		else {
			obj.addAttribute(xsi, "type", type);
		}
		
		if(xmlID != null) {
			obj.addAttribute("xmlID",xmlID);
		}
		if(version != null) {
			obj.addAttribute("version",version);
		}
		for(ObjectIdentifier identifier : objectIdentifiers) {
			identifier.output(writer);
		}
		for(PreservationLevel pLevel : preservationLevels) {
			pLevel.output(writer);
		}
		for(SignificantProperties sigProp : significantProperties) {
			sigProp.output(writer);
		}
		for(ObjectCharacteristics objChars : objectCharacteristics) {
			objChars.output(writer);
		}
		if(originalName != null && originalName.length()>0) {
			SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
			SMOutputElement e = frag.addElement(Premis.ns,"originalName");	
			if(originalNameXlink != null) {
				originalNameXlink.output(writer);
			}
			e.addCharacters(originalName);
			frag.closeRoot();
		}
		for(Storage storage : storages) {
			storage.output(writer);
		}
		for(Environment environment : environments) {
			environment.output(writer);
		}
		for(SignatureInformation signatureInfo : signatureInformations) {
			signatureInfo.output(writer);
		}
		for(Relationship relationship : relationships) {
			relationship.output(writer);
		}
		for(LinkingEventIdentifier linkEventID : linkingEventIdentifiers) {
			linkEventID.output(writer);
		}
		for(LinkingIntellectualEntityIdentifier linkEntityID : linkingIntellectualEntityIdentifiers) {
			linkEntityID.output(writer);
		}
		for(LinkingRightsStatementIdentifier linkRightsID : linkingRightsStatementIdentifiers) {
			linkRightsID.output(writer);
		}
	}

	public XmlComponent parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		if(reader.getEventType() == START_DOCUMENT) {
			reader.nextTag();
		}
		reader.require(START_ELEMENT, Premis.XMLNS, "object");		
		xmlID = reader.getAttributeValue(null,"xmlID");
		version = reader.getAttributeValue(null,"version");
		for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("originalName")) {
						if(reader.getAttributeCount() > 0) {
							originalNameXlink = new SimpleLink(reader);
						}
						originalName = reader.getElementText();
					}
					else if(localName.equals("preservationLevel")) {
						PreservationLevel preservationLevel = new PreservationLevel(reader);
						preservationLevels.add(preservationLevel);
					}
					else if(localName.equals("objectCharacteristics")) {
						ObjectCharacteristics objCharacteristics = new ObjectCharacteristics(reader);
						objectCharacteristics.add(objCharacteristics);
					}
					else if(localName.equals("storage")) {
						Storage strg = new Storage(reader);
						storages.add(strg);
					}
					else if(localName.equals("signatureInformation")) {
						SignatureInformation signatureInfo = new SignatureInformation(reader);
						signatureInformations.add(signatureInfo);
					}
					else {
						readObjectElements(reader, localName);
					}
				break;		
				case END_ELEMENT:
				//return when we're at the end of the element
				if(reader.getLocalName().equals("object")) {
					return this;
				}
			}
		}
		return this;
	}
	@Override
	public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
		List<NamespaceSchemaContext> contexts = new ArrayList<NamespaceSchemaContext>();	
		//Add namespace schema context for this object
		contexts.add(Premis.nsSchemaContext);
		if(significantProperties != null) {
			for(SignificantProperties prop : significantProperties) {
				contexts.addAll(prop.getAllNamespaceSchemaContexts());
			}
		}
		if(environments != null) {
			for(Environment env : environments) {
				contexts.addAll(env.getAllNamespaceSchemaContexts());
			}
		}	
		if(objectCharacteristics != null) {
			for(ObjectCharacteristics objChar : objectCharacteristics) {
				contexts.addAll(objChar.getAllNamespaceSchemaContexts());
			}
		}
		if(signatureInformations != null) {
			for(SignatureInformation sig : signatureInformations) {
				contexts.addAll(sig.getAllNamespaceSchemaContexts());
			}
		}

		//find xlink ns
		boolean foundXlink = false;
		
		for(Storage storage : storages) {
			if(storage.getContentLocationXlink() != null) {
				foundXlink = true;
				break;
			}
		}
		
		if(!foundXlink)
		foundXlink = findXlinkNS();		
		if(foundXlink) {
			contexts.add(SimpleLink.nsSchemaContext);
		}
		
		return contexts;
	}
}
