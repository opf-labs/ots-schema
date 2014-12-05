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


public class SignificantProperties  {
	
	private String type;
	private String value;
	private List<XmlContent> extension;
	
	public SignificantProperties() { 
		extension = new ArrayList<XmlContent>();
	}
	
	public SignificantProperties(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this();
		parse(reader);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
	
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
		SMOutputElement sigProperties = frag.addElement(Premis.ns,"significantProperties");	
		if(type != null)  {
			sigProperties.addElementWithCharacters(Premis.ns,"significantPropertiesType",type);
		}
		if(value != null)  {
			sigProperties.addElementWithCharacters(Premis.ns,"significantPropertiesValue",value);
		}	
		for(XmlContent xml : extension) {
			sigProperties.addElement(Premis.ns,"significantPropertiesExtension");
			xml.output(writer);
		}
		frag.closeRoot();		
	}

	public SignificantProperties parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException  {
		reader.require(START_ELEMENT, Premis.XMLNS, "significantProperties");	
		for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("significantPropertiesType")) {
						type = reader.getElementText();
					}
					else if(localName.equals("significantPropertiesValue")) {
						value = reader.getElementText();
					}
					else if(localName.equals("significantPropertiesExtension")) {
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
				if(reader.getLocalName().equals("significantProperties")) {
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
		for(XmlContent content : extension) {
			contexts.addAll(content.getAllNamespaceSchemaContexts());
		}	
		return contexts;
	}
	
}
