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

import edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD.FITS.GenericFitsElement;
import edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD.FITS.Tool;
import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;

public class FormatValidation {
	
	private List<GenericFitsElement> wellFormed = new ArrayList<GenericFitsElement>();
	private List<GenericFitsElement> valid = new ArrayList<GenericFitsElement>();
	private List<GenericFitsElement> messages = new ArrayList<GenericFitsElement>();
		
	public static final String XMLNS = "http://hul.harvard.edu/ois/xml/ns/fits/fits_output";
	public static final String XML_DEFAULT_PREFIX = "fits";
	public static final String SCHEMA_LOCATION = "http://hul.harvard.edu/ois/xml/xsd/fits/fits_output.xsd";
	
	public static final String ELEM_NAME = "formatValidation";
			
	private NamespaceSchemaContext nsSchemaContext = new NamespaceSchemaContext(XMLNS, XML_DEFAULT_PREFIX, SCHEMA_LOCATION);

	
	public FormatValidation() { 	}
	
	
	public List<GenericFitsElement> getWellFormed() {
		return wellFormed;
	}

	public void setWellFormed(List<GenericFitsElement> wellFormed) {
		this.wellFormed = wellFormed;
	}

	public List<GenericFitsElement> getValid() {
		return valid;
	}

	public void setValid(List<GenericFitsElement> valid) {
		this.valid = valid;
	}

	public List<GenericFitsElement> getMessages() {
		return messages;
	}

	public void setMessages(List<GenericFitsElement> messages) {
		this.messages = messages;
	}
	
	public void addWellFormed(GenericFitsElement wellFormed) {
		this.wellFormed.add(wellFormed);
	}
	
	public void addValid(GenericFitsElement valid) {
		this.valid.add(valid);
	}
	
	public void addMessage(GenericFitsElement message) {
		messages.add(message);
	}

	public void output(SMOutputElement parent, SMNamespace ns) throws XMLStreamException {
		SMOutputElement fileStatus = parent.addElement(ns, ELEM_NAME);
		SMNamespace fitsNS = fileStatus.getNamespace(XMLNS,nsSchemaContext.getPrefix());
		outputGenericFitsElement(fileStatus, fitsNS, wellFormed);
		outputGenericFitsElement(fileStatus, fitsNS, valid);
		outputGenericFitsElement(fileStatus, fitsNS, messages);
	}
	
	private void outputGenericFitsElement(SMOutputElement parent, SMNamespace ns, List<GenericFitsElement> fitsElements) throws XMLStreamException {
		for(GenericFitsElement fitsElement : fitsElements) {
			SMOutputElement element = parent.addElement(ns, fitsElement.getName());
			if(fitsElement.getTool() != null) {
				element.addAttribute("toolname",fitsElement.getTool().getToolName());
				element.addAttribute("toolversion",fitsElement.getTool().getToolVersion());
			}
			if(fitsElement.getStatus() != null) {
				element.addAttribute("status",fitsElement.getStatus());
			}
			element.addCharacters(fitsElement.getValue());
		}
	}

	public FormatValidation parse(XMLStreamReader reader) throws XMLStreamException {
		int event = reader.getEventType();
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals(ELEM_NAME))) {	
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();
				if(localName.equals("well-formed")) {
					GenericFitsElement wellFormed = new GenericFitsElement();
					Tool tool = new Tool();
					tool.setToolName(reader.getAttributeValue(null,"toolname"));
					tool.setToolVersion(reader.getAttributeValue(null,"toolversion"));
					wellFormed.setStatus(reader.getAttributeValue(null,"status"));
					wellFormed.setName("well-formed");
					wellFormed.setValue(reader.getElementText());
					wellFormed.setTool(tool);
					this.wellFormed.add(wellFormed);
				}
				else if(localName.equals("valid")) {
					GenericFitsElement valid = new GenericFitsElement();
					Tool tool = new Tool();
					tool.setToolName(reader.getAttributeValue(null,"toolname"));
					tool.setToolVersion(reader.getAttributeValue(null,"toolversion"));
					valid.setStatus(reader.getAttributeValue(null,"status"));
					valid.setName("valid");
					valid.setValue(reader.getElementText());
					valid.setTool(tool);
					this.valid.add(valid);
				}
				else if(localName.equals("message")) {
					GenericFitsElement message = new GenericFitsElement();
					Tool tool = new Tool();
					tool.setToolName(reader.getAttributeValue(null,"toolname"));
					tool.setToolVersion(reader.getAttributeValue(null,"toolversion"));
					message.setStatus(reader.getAttributeValue(null,"status"));
					message.setName("message");
					message.setValue(reader.getElementText());
					message.setTool(tool);
					messages.add(message);
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
