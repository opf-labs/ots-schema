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

import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContent;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentParser;
import edu.harvard.hul.ois.ots.schemas.premis.Premis;
import edu.harvard.hul.ois.ots.schemas.premis.PremisDate;
import edu.harvard.hul.ois.ots.schemas.premis.PremisDateException;
import edu.harvard.hul.ois.ots.schemas.premis.PremisParser;

public class CreatingApplication {
	
	private String name;
	private String version;
	private PremisDate dateCreated;
	private List<XmlContent> extension;
	
	public CreatingApplication() {
		extension = new ArrayList<XmlContent>();
		dateCreated = new PremisDate();
	}
	
	public CreatingApplication(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this();
		parse(reader);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public PremisDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) throws PremisDateException {
		this.dateCreated.setValue(dateCreated);
	}

	public List<XmlContent> getExtension() {
		return extension;
	}

	public void setExtension(List<XmlContent> extension) {
		this.extension = extension;
	}

	public void addExtension(XmlContent extension) {
		this.extension.add(extension);
	}
	
	public void output(XMLStreamWriter writer, SMOutputElement parent) throws XMLStreamException {
		SMOutputElement creatingApplication = parent.addElement(Premis.ns,"creatingApplication");	
		if(name != null)  {
			creatingApplication.addElementWithCharacters(Premis.ns,"creatingApplicationName",name);
		}
		if(version != null)  {
			creatingApplication.addElementWithCharacters(Premis.ns,"creatingApplicationVersion",version);
		}
		if(dateCreated.toString() != null)  {
			creatingApplication.addElementWithCharacters(Premis.ns,"dateCreatedByApplication",dateCreated.toString());
		}
		for(XmlContent xml : extension) {
			creatingApplication.addElement(Premis.ns, "creatingApplicationExtension");
			xml.output(writer);
		}
	}

	public CreatingApplication parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		reader.require(START_ELEMENT, Premis.XMLNS, "creatingApplication");	
		for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("creatingApplicationName")) {
						name = reader.getElementText();
					}
					else if(localName.equals("creatingApplicationVersion")) {
						version = reader.getElementText();
					}
					else if(localName.equals("dateCreatedByApplication")) {
						setDateCreated(reader.getElementText());
					}
					else if(localName.equals("creatingApplicationExtension")) {
						//advance to next element
						reader.nextTag();
						//get new local name of the current element
						localName = reader.getLocalName();
						//get a parser
						XmlContentParser parser = PremisParser.getParser(localName);
						extension.add(parser.parse(reader));
					}
				break;		
				case END_ELEMENT:
				//return when we're at the end of the element
				if(reader.getLocalName().equals("creatingApplication")) {
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
			for(XmlContent content : extension) {
				contexts.addAll(content.getAllNamespaceSchemaContexts());
			}
		}
		return contexts;
	}

}
