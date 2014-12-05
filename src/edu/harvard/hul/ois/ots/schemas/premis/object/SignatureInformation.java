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



public class SignatureInformation {

	private String encoding;
	private String signer;
	private String method;
	private String value;
	private String validationRules;
	private List<String> properties;
	private XmlContent keyInformation;
	private List<XmlContent> extension;

	public SignatureInformation() {
		extension = new ArrayList<XmlContent>();
		properties = new ArrayList<String>();
	}
	
	public SignatureInformation(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this();
		parse(reader);
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getSigner() {
		return signer;
	}

	public void setSigner(String signer) {
		this.signer = signer;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValidationRules() {
		return validationRules;
	}

	public void setValidationRules(String validationRules) {
		this.validationRules = validationRules;
	}

	public List<String> getProperties() {
		return properties;
	}

	public void setProperties(List<String> properties) {
		this.properties = properties;
	}

	public void addProperty(String property) {
		properties.add(property);
	}

	public XmlContent getKeyInformation() {
		return keyInformation;
	}

	public void setKeyInformation(XmlContent keyInformation) {
		this.keyInformation = keyInformation;
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
		SMOutputElement signatureInfo = frag.addElement(Premis.ns,"signatureInformation");	
		if(encoding != null || signer != null || method != null || value != null || validationRules != null
				|| properties != null || keyInformation != null) {
			SMOutputElement signature = signatureInfo.addElement(Premis.ns,"signature");	
			if(encoding != null) {
				signature.addElementWithCharacters(Premis.ns, "signatureEncoding",encoding);
			}
			if(signer != null) {
				signature.addElementWithCharacters(Premis.ns, "signer",signer);
			}
			if(method != null) {
				signature.addElementWithCharacters(Premis.ns, "signatureMethod",method);
			}
			if(value != null) {
				signature.addElementWithCharacters(Premis.ns, "signatureValue",value);
			}
			if(validationRules != null) {
				signature.addElementWithCharacters(Premis.ns, "signatureValidationRules",validationRules);
			}
			for(String property : properties) {
				signature.addElementWithCharacters(Premis.ns, "signatureProperties",property);
			}
			if(keyInformation != null) {
				signature.addElement(Premis.ns,"keyInformation");
				keyInformation.output(writer);
			}
		}
		for(XmlContent xml : extension) {
			signatureInfo.addElement(Premis.ns,"signatureInformationExtension");
			xml.output(writer);
		}
		frag.closeRoot();
	}

	public SignatureInformation parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		reader.require(START_ELEMENT, Premis.XMLNS, "signatureInformation");	
		for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("signatureEncoding")) {
						encoding = reader.getElementText();
					}
					else if(localName.equals("signer")) {
						signer = reader.getElementText();
					}
					else if(localName.equals("signatureMethod")) {
						method = reader.getElementText();
					}
					else if(localName.equals("signatureValue")) {
						value = reader.getElementText();
					}
					else if(localName.equals("signatureValidationRules")) {
						validationRules = reader.getElementText();
					}
					else if(localName.equals("signatureProperties")) {
						properties.add(reader.getElementText());
					}
					else if(localName.equals("keyInformation")) {
						//advance to next element
						reader.nextTag();
						//get new local name of the current element
						localName = reader.getLocalName();
						//get a parser
						XmlContentParser parser = PremisParser.getParser(localName);
						keyInformation = parser.parse(reader);
					}
					else if(localName.equals("signatureInformationExtension")) {
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
				if(reader.getLocalName().equals("signatureInformation")) {
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
		if(keyInformation != null) {
			contexts.addAll(keyInformation.getAllNamespaceSchemaContexts());
		}	
		if(extension != null) {
			for(XmlContent content : extension) {
				contexts.addAll(content.getAllNamespaceSchemaContexts());
			}
		}
		return contexts;
	}

}
