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
package edu.harvard.hul.ois.ots.schemas.pds;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import java.util.ArrayList;
import java.util.List;

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

public class PdsMD implements XmlComponent{
	
	public static final String XMLNS = "http://hul.harvard.edu/ois/xml/ns/pds";
	public static final String SCHEMA_LOCATION = "http://hul.harvard.edu/ois/xml/xsd/pds/pds.xsd";
	public static final String XML_DEFAULT_PREFIX = "pds";
	
	private NamespaceSchemaContext nsSchemaContext = new NamespaceSchemaContext(XMLNS, XML_DEFAULT_PREFIX, SCHEMA_LOCATION);
	
	private PdsDate pdsDate;
	private List<PdsFunction> functions;
	private String header;
	
	private boolean isRoot;
	
	public PdsMD() { 
		functions = new ArrayList<PdsFunction>();
		isRoot = false;
	}
	
	public PdsMD(boolean isRoot) { 
		functions = new ArrayList<PdsFunction>();
		this.isRoot = isRoot;
	}
	
	public PdsMD(XMLStreamReader reader) throws XMLStreamException, PdsDateException {
		this();
		parse(reader);
	}
	
	public PdsMD(XMLStreamReader reader, boolean isRoot) throws XMLStreamException, PdsDateException {
		this(isRoot);
		parse(reader);
	}
	
	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	
	public PdsDate getPdsDate() {
		return pdsDate;
	}
	public void setPdsDate(PdsDate pdsDate) {
		this.pdsDate = pdsDate;
	}
	public List<PdsFunction> getFunctions() {
		return functions;
	}
	public void setFunctions(List<PdsFunction> functions) {
		this.functions = functions;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}

	@Override
	public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
		List<NamespaceSchemaContext> contexts = new ArrayList<NamespaceSchemaContext>();	
		//Add namespace schema context for this object
		contexts.add(nsSchemaContext);
		//pds cannot contain xml from other schemas
		return contexts;
	}

	@Override
	public NamespaceSchemaContext getNamespaceSchemaContext() {
		return nsSchemaContext;
	}

	@Override
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		if(isRoot) {
			SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer);			
			SMNamespace pdsNS = doc.getNamespace(XMLNS,nsSchemaContext.getPrefix());
			SMOutputElement root = doc.addElement(pdsNS,"pds");		
			RootXmlHelper.output(writer,getAllNamespaceSchemaContexts(),root);		
			outputContents(writer,pdsNS,root);
			doc.closeRoot();
		}
		else {
			SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
			SMNamespace pdsNS = frag.getNamespace(XMLNS,nsSchemaContext.getPrefix());
			SMOutputElement root = frag.addElement(pdsNS,"pds");
			outputContents(writer,pdsNS,root);
			frag.closeRoot();
		}	
	}
	
	private void outputContents(XMLStreamWriter writer,SMNamespace pdsNS, SMOutputElement root) throws XMLStreamException {	
		if(pdsDate != null) {
			SMOutputElement date_e = root.addElement(pdsNS,"date");
			if(pdsDate.getFromDate() != null) {
				date_e.addElementWithCharacters(pdsNS, "fromDate",pdsDate.getFromDate());
			}
			if(pdsDate.getToDate() != null) {
				date_e.addElementWithCharacters(pdsNS, "toDate",pdsDate.getToDate());
			}
		}
		for(PdsFunction function : functions) {
			SMOutputElement function_e = root.addElement(pdsNS,"function");
			if(function.getStatus() != null) {
				function_e.addAttribute("status",function.getStatus().toString());
			}
			function_e.addCharacters(function.getValue());
		}
		if(header != null) {
			root.addElementWithCharacters(pdsNS,"header",header);
		}
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PdsMD parse(XMLStreamReader reader) throws XMLStreamException, PdsDateException {
		int event = reader.getEventType();
		if(event == START_DOCUMENT) {
			reader.nextTag();
		}
		reader.require(START_ELEMENT, XMLNS, "pds");		
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("pds"))) {	
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();	
				if(localName.equals("date")) {
					pdsDate = new PdsDate();
					while(!(event == END_ELEMENT && localName.equals("date"))) {	
						event = reader.next();
						switch(event) {
						case START_ELEMENT:
							localName = reader.getLocalName();	
							if(localName.equals("fromDate")) {
								pdsDate.setFromDate(reader.getElementText());
							}
							else if(localName.equals("toDate")) {
								pdsDate.setToDate(reader.getElementText());
							}
							break;
						case END_ELEMENT:
							localName = reader.getLocalName();
							break;								
						}
					}
				}	
				else if(localName.equals("function")) {
					PdsFunction pdsFunction = new PdsFunction();
					String status = reader.getAttributeValue(XMLNS,"status");
					pdsFunction.setStatus(Boolean.parseBoolean(status));
					pdsFunction.setValue(reader.getElementText());
					functions.add(pdsFunction);
				}
				else if(localName.equals("header")) {
					header = reader.getElementText();
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
