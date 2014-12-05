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
package edu.harvard.hul.ois.ots.schemas.HulDrsBatch;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_DOCUMENT;
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

import edu.harvard.hul.ois.ots.schemas.XmlComponent;
import edu.harvard.hul.ois.ots.schemas.StaxUtils.RootXmlHelper;
import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlDateFormat;

public class HulDrsBatch implements XmlComponent{
	
	public static final String XMLNS = "http://hul.harvard.edu/ois/xml/ns/hulDrsBatch";
	public static final String SCHEMA_LOCATION = "http://hul.harvard.edu/ois/xml/xsd/drs/hulDrsBatch.xsd";
	public static final String XML_DEFAULT_PREFIX = "hulDrsBatch";
	private NamespaceSchemaContext nsSchemaContext = new NamespaceSchemaContext(XMLNS, XML_DEFAULT_PREFIX, SCHEMA_LOCATION);
	private boolean isRoot;
	
	private static final String BATCH_ID = "batchId";
	private static final String BATCH_NAME = "batchName";
	private static final String BATCH_DIR_NAME = "batchDirectoryName";
	private static final String LOAD_START_TIME = "loadStartTime";
	
	private String batchId;
	private String batchName;
	private String batchDirectoryName;
	private Date loadStartTime;

	public HulDrsBatch() { 
		isRoot = false;
	}
	
	public HulDrsBatch(boolean isRoot) { 
		this.isRoot = isRoot;
	}
	
	public HulDrsBatch(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this();
		parse(reader);
	}
	
	public HulDrsBatch(XMLStreamReader reader, boolean isRoot) throws XMLStreamException, XmlContentException {
		this(isRoot);
		parse(reader);
	}
	
	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
		
	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getBatchDirectoryName() {
		return batchDirectoryName;
	}

	public void setBatchDirectoryName(String batchDirectoryName) {
		this.batchDirectoryName = batchDirectoryName;
	}

	public Date getLoadStartTime() {
		return loadStartTime;
	}

	public void setLoadStartTime(Date loadStartTime) {
		this.loadStartTime = loadStartTime;
	}

	@Override
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		if(isRoot) {
			SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer);			
			SMNamespace ns = doc.getNamespace(XMLNS,nsSchemaContext.getPrefix());
			SMOutputElement root = doc.addElement(ns,XML_DEFAULT_PREFIX);		
			RootXmlHelper.output(writer,getAllNamespaceSchemaContexts(),root);		
			outputContents(writer,ns,root);
			doc.closeRoot();
		}
		else {
			SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
			SMNamespace ns = frag.getNamespace(XMLNS,nsSchemaContext.getPrefix());
			SMOutputElement root = frag.addElement(ns,XML_DEFAULT_PREFIX);
			outputContents(writer,ns,root);
			frag.closeRoot();
		}

	}	
	
	private void outputContents(XMLStreamWriter writer,SMNamespace ns, SMOutputElement root) throws XMLStreamException {	
		root.addElementWithCharacters(ns,BATCH_ID,batchId);
		root.addElementWithCharacters(ns,BATCH_NAME,batchName);
		root.addElementWithCharacters(ns,BATCH_DIR_NAME,batchDirectoryName);
		root.addElementWithCharacters(ns,LOAD_START_TIME, XmlDateFormat.formatDateTime(loadStartTime));
	}

	@Override
	public boolean validate() {
		if(batchId != null && batchName != null && batchDirectoryName != null && loadStartTime != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public HulDrsBatch parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		int event = reader.getEventType();
		if(event == START_DOCUMENT) {
			reader.nextTag();
		}
		reader.require(START_ELEMENT, HulDrsBatch.XMLNS, "hulDrsBatch");		
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("hulDrsBatch"))) {	
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();	
				if(localName.equals(BATCH_ID)) {
					batchId = reader.getElementText();
				}
				else if(localName.equals(BATCH_NAME)) {
					batchName = reader.getElementText();
				}
				else if(localName.equals(BATCH_DIR_NAME)) {
					batchDirectoryName = reader.getElementText();
				}
				else if(localName.equals(LOAD_START_TIME)) {
					String date = null;
					try {
						date = reader.getElementText();
						loadStartTime = XmlDateFormat.parseDateTime(date);
					} catch (ParseException e) {
						throw new XMLStreamException("Invalid Date: "+date,e);
					}
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
		//hulDrsAdmin cannot contain xml from other schemas
		return contexts;
	}

	@Override
	public NamespaceSchemaContext getNamespaceSchemaContext() {
		return nsSchemaContext;
	}

}
