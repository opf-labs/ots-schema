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

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;
import edu.harvard.hul.ois.ots.schemas.XmlContent.SimpleLink;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContent;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentParser;
import edu.harvard.hul.ois.ots.schemas.premis.Premis;
import edu.harvard.hul.ois.ots.schemas.premis.PremisParser;



public class ObjectCharacteristics {
	
	private Integer compositionLevel;
	private List<Fixity> fixities;
	private Long size;
	//format fields
	private String formatRegistryName;
	private String formatRegistryKey;
	private String formatRegistryRole;
	private SimpleLink formatRegistryXlink;
	private String formatDesignationName;
	private String formatDesignationVersion;
	private List<String> formatNotes;
	//end of format fields
	private List<CreatingApplication> creatingApplications;
	private List<Inhibitor> inhibitors;
	private XmlContent extension;
		
	public ObjectCharacteristics() { 
		formatNotes = new ArrayList<String>();
		fixities = new ArrayList<Fixity>();
		creatingApplications = new ArrayList<CreatingApplication>();
		inhibitors = new ArrayList<Inhibitor>();
	}

	public ObjectCharacteristics(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this();
		parse(reader);
	}
	
	public Integer getCompositionLevel() {
		return compositionLevel;
	}

	public void setCompositionLevel(int compositionLevel) {
		this.compositionLevel = compositionLevel;
	}

	public List<Fixity> getFixities() {
		return fixities;
	}

	public void setFixities(List<Fixity> fixities) {
		this.fixities = fixities;
	}
	
	public void addFixity(Fixity fixity) {
		fixities.add(fixity);
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFormatRegistryName() {
		return formatRegistryName;
	}

	public void setFormatRegistryName(String registryName) {
		this.formatRegistryName = registryName;
	}

	public String getFormatRegistryKey() {
		return formatRegistryKey;
	}

	public void setFormatRegistryKey(String registryKey) {
		this.formatRegistryKey = registryKey;
	}

	public String getFormatRegistryRole() {
		return formatRegistryRole;
	}

	public void setFormatRegistryRole(String registryRole) {
		this.formatRegistryRole = registryRole;
	}

	public SimpleLink getFormatRegistryXLink() {
		return formatRegistryXlink;
	}

	public void setFormatRegistryXLink(SimpleLink registryXlink) {
		this.formatRegistryXlink = registryXlink;
	}

	public String getFormatDesignationName() {
		return formatDesignationName;
	}

	public void setFormatDesignationName(String designationName) {
		this.formatDesignationName = designationName;
	}

	public String getFormatDesignationVersion() {
		return formatDesignationVersion;
	}

	public void setFormatDesignationVersion(String designationVersion) {
		this.formatDesignationVersion = designationVersion;
	}

	public List<String> getFormatNotes() {
		return formatNotes;
	}

	public void setFormatNotes(List<String> notes) {
		this.formatNotes = notes;
	}
	
	public void addFormatNote(String note) {
		this.formatNotes.add(note);
	}
	
	public List<CreatingApplication> getCreatingApplications() {
		return creatingApplications;
	}

	public void setCreatingApplications(List<CreatingApplication> creatingApplications) {
		this.creatingApplications = creatingApplications;
	}
	
	public void addCreatingApplication(CreatingApplication app) {
		creatingApplications.add(app);
	}

	public List<Inhibitor> getInhibitors() {
		return inhibitors;
	}

	public void setInhibitors(List<Inhibitor> inhibitors) {
		this.inhibitors = inhibitors;
	}
	
	public void addInhibitor(Inhibitor inhibitor) {
		inhibitors.add(inhibitor);
	}

	public XmlContent getExtension() {
		return extension;
	}

	public void setExtension(XmlContent extension) {
		this.extension = extension;
	}

	public void output(XMLStreamWriter writer) throws XMLStreamException {
		SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
		SMOutputElement objChars = frag.addElement(Premis.ns,"objectCharacteristics");
		if(compositionLevel != null) {
			objChars.addElementWithCharacters(Premis.ns,"compositionLevel",compositionLevel.toString());
		}	
		for(Fixity fixity : fixities) {			
			fixity.output(objChars);
		}
		if(size != null) {
			objChars.addElementWithCharacters(Premis.ns,"size",size.toString());
		}
		if(formatDesignationName != null || formatRegistryName != null) {
			SMOutputElement format = objChars.addElement(Premis.ns,"format");
			if(formatDesignationName != null) {
				SMOutputElement formatDesignation = format.addElement(Premis.ns,"formatDesignation");
				formatDesignation.addElementWithCharacters(Premis.ns, "formatName",formatDesignationName);
				if(formatDesignationVersion != null) {
					formatDesignation.addElementWithCharacters(Premis.ns, "formatVersion",formatDesignationVersion);
				}
			}
			if(formatRegistryName != null) {
				if(formatRegistryXlink != null) {
					formatRegistryXlink.output(writer);
				}
				SMOutputElement formatRegistry = format.addElement(Premis.ns, "formatRegistry");
				formatRegistry.addElementWithCharacters(Premis.ns, "formatRegistryName", formatRegistryName);
				if(formatRegistryKey != null) {
					formatRegistry.addElementWithCharacters(Premis.ns, "formatRegistryKey", formatRegistryKey);
				}
				if(formatRegistryRole != null) {
					formatRegistry.addElementWithCharacters(Premis.ns, "formatRegistryRole", formatRegistryRole);
				}
			}
			for(String note : formatNotes) {
				format.addElementWithCharacters(Premis.ns,"formatNote",note);
			}
		}
		for(CreatingApplication creatingApp : creatingApplications) {
			creatingApp.output(writer,objChars);
		}
		for(Inhibitor inhibitor : inhibitors) {
			inhibitor.output(writer,objChars);
		}
		if(extension != null) {
			objChars.addElement(Premis.ns, "objectCharacteristicsExtension");
			extension.output(writer);
		}
		frag.closeRoot();
		
	}

	public ObjectCharacteristics parse(XMLStreamReader reader) throws NumberFormatException, XMLStreamException, XmlContentException {
		reader.require(START_ELEMENT, Premis.XMLNS, "objectCharacteristics");	
		for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("compositionLevel")) {
						compositionLevel = new Integer(reader.getElementText());
					}
					else if(localName.equals("fixity")) {
						Fixity fixity = new Fixity(reader);
						fixities.add(fixity);
					}
					else if(localName.equals("size")) {
						size = Long.parseLong(reader.getElementText());
					}
					else if(localName.equals("formatNote")) {
						formatNotes.add(reader.getElementText());
					}
					else if(localName.equals("formatName")) {
						formatDesignationName = reader.getElementText();
					}
					else if(localName.equals("formatVersion")) {
						formatDesignationVersion = reader.getElementText();
					}
					else if(localName.equals("formatRegistryName")) {
						formatRegistryName = reader.getElementText();
					}
					else if(localName.equals("formatRegistryKey")) {
						formatRegistryKey = reader.getElementText();
					}
					else if(localName.equals("formatRegistryRole")) {
						formatRegistryRole = reader.getElementText();
					}					
					else if(localName.equals("creatingApplication")) {
						CreatingApplication creatingApp = new CreatingApplication(reader);
						creatingApplications.add(creatingApp);
					}
					else if(localName.equals("inhibitors")) {
						Inhibitor inhibitor = new Inhibitor(reader);
						inhibitors.add(inhibitor);
					}
					else if(localName.equals("objectCharacteristicsExtension")) {
						//advance to next element
						reader.nextTag();
						//get new local name of the current element
						//localName = reader.getLocalName();
						QName qname = reader.getName();
						//get a parser
						XmlContentParser parser = PremisParser.getParser(qname.getPrefix()+":"+qname.getLocalPart());
						extension = parser.parse(reader);
					}
				break;		
				case END_ELEMENT:
				//return when we're at the end of the element
				if(reader.getLocalName().equals("objectCharacteristics")) {
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
	
	public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
		List<NamespaceSchemaContext> contexts = new ArrayList<NamespaceSchemaContext>();	
		if(extension != null) {
			contexts.addAll(extension.getAllNamespaceSchemaContexts());
		}	
		if(creatingApplications != null) {
			for(CreatingApplication app : creatingApplications) {
				contexts.addAll(app.getAllNamespaceSchemaContexts());
			}
		}
		if(formatRegistryXlink != null) {
			contexts.add(SimpleLink.nsSchemaContext);
		}
		return contexts;
	}

}
