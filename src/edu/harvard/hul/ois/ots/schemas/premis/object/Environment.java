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

import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContent;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentParser;
import edu.harvard.hul.ois.ots.schemas.premis.Premis;
import edu.harvard.hul.ois.ots.schemas.premis.PremisParser;



public class Environment {

	private String characteristic;
	private List<String> purposes;
	private List<String> notes;
	private List<Dependency> dependencies;
	private List<Software> softwares;
	private List<Hardware> hardwares;
	private XmlContent extension;

	public Environment() {
		purposes = new ArrayList<String>();
		notes = new ArrayList<String>();
		dependencies = new ArrayList<Dependency>();
		softwares = new ArrayList<Software>();
		hardwares = new ArrayList<Hardware>();
	}
	
	public Environment(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this();
		parse(reader);
	}
	
	public String getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(String characteristics) {
		this.characteristic = characteristics;
	}

	public List<String> getPurposes() {
		return purposes;
	}

	public void setPurposes(List<String> purposes) {
		this.purposes = purposes;
	}

	public List<String> getNotes() {
		return notes;
	}

	public void setNotes(List<String> note) {
		this.notes = note;
	}

	public List<Dependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<Dependency> dependencies) {
		this.dependencies = dependencies;
	}

	public void addDependency(Dependency dependency) {
		dependencies.add(dependency);
	}

	public List<Software> getSoftware() {
		return softwares;
	}

	public void setSoftware(List<Software> software) {
		this.softwares = software;
	}

	public void addSoftware(Software software) {
		this.softwares.add(software);
	}

	public List<Hardware> getHardware() {
		return hardwares;
	}

	public void setHardware(List<Hardware> hardware) {
		this.hardwares = hardware;
	}

	public void addHardware(Hardware hardware) {
		this.hardwares.add(hardware);
	}
	
	public XmlContent getExtension() {
		return extension;
	}
	
	public void setExtension(XmlContent ext) {
		extension = ext;
	}

	public void output(XMLStreamWriter writer) throws XMLStreamException {
		SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
		SMOutputElement environment = frag.addElement(Premis.ns,"environment");	
		if(characteristic != null) {
			environment.addElementWithCharacters(Premis.ns,"environmentCharacteristic",characteristic);
		}
		for(String purpose : purposes) {
			environment.addElementWithCharacters(Premis.ns,"environmentPurpose",purpose);
		}
		for(String note : notes) {
			environment.addElementWithCharacters(Premis.ns,"environmentNote",note);
		}
		for(Dependency dependency : dependencies) {
			dependency.output(environment);
		}
		for(Software sw : softwares) {
			sw.output(environment);
		}
		for(Hardware hw : hardwares) {
			hw.output(environment);
		}
		if(extension != null) {
			environment.addElement(Premis.ns,"environmentExtension");
			extension.output(writer);
		}
		frag.closeRoot();
	}

	public Environment parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		reader.require(START_ELEMENT, Premis.XMLNS, "environment");	
		for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("environmentCharacteristic")) {
						characteristic = reader.getElementText();
					}
					else if(localName.equals("environmentPurpose")) {
						purposes.add(reader.getElementText());
					}
					else if(localName.equals("environmentNote")) {
						notes.add(reader.getElementText());
					}
					else if(localName.equals("dependency")) {
						Dependency dependency = new Dependency(reader);
						dependencies.add(dependency);
					}
					else if(localName.equals("software")) {
						Software sftware = new Software(reader);
						softwares.add(sftware);
					}
					else if(localName.equals("hardware")) {
						Hardware hrdware = new Hardware(reader);
						hardwares.add(hrdware);
					}
					else if(localName.equals("environmentExtension")) {
						//advance to next element
						reader.nextTag();
						//get new local name of the current element
						localName = reader.getLocalName();
						//get a parser
						XmlContentParser parser = PremisParser.getParser(localName);
						extension = parser.parse(reader);
					}
				break;		
				case END_ELEMENT:
				//return when we're at the end of the element
				if(reader.getLocalName().equals("environment")) {
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
		return contexts;
	}
}
