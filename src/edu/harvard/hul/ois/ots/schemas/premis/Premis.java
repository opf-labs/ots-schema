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
package edu.harvard.hul.ois.ots.schemas.premis;

import static javax.xml.stream.XMLStreamConstants.END_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_DOCUMENT;

import java.lang.reflect.Field;
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
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.premis.AnnotationDefs.RequiredField;
import edu.harvard.hul.ois.ots.schemas.premis.agent.PremisAgent;
import edu.harvard.hul.ois.ots.schemas.premis.event.PremisEvent;
import edu.harvard.hul.ois.ots.schemas.premis.object.ObjectBitStream;
import edu.harvard.hul.ois.ots.schemas.premis.object.ObjectFile;
import edu.harvard.hul.ois.ots.schemas.premis.object.ObjectRepresentation;
import edu.harvard.hul.ois.ots.schemas.premis.object.PremisObject;
import edu.harvard.hul.ois.ots.schemas.premis.rights.PremisRights;


public class Premis implements XmlComponent {

	@RequiredField
	public static final String VERSION = "2.0";

	private List<PremisObject> objects;
	private List<PremisEvent> events;
	private List<PremisAgent> agents;
	private List<PremisRights> rights;
	
	public static final String XMLNS = "info:lc/xmlns/premis-v2";
	public static final String SCHEMA_LOCATION = "http://www.loc.gov/standards/premis/premis.xsd";
	public static final String XML_DEFAULT_PREFIX = "premis";
		
	public static NamespaceSchemaContext nsSchemaContext = new NamespaceSchemaContext(XMLNS, XML_DEFAULT_PREFIX, SCHEMA_LOCATION);
	
	public static SMNamespace ns = null;
	
	private boolean isRoot;
	
	public Premis() {
		objects = new ArrayList<PremisObject>();
		events  = new ArrayList<PremisEvent>();
		agents  = new ArrayList<PremisAgent>();
		rights  = new ArrayList<PremisRights>();
		isRoot = false;
	}
	
	public Premis(boolean isRoot) {
		this();
		this.isRoot = isRoot;
	}
	
	public Premis(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this();
		parse(reader);
	}
	
	public Premis(XMLStreamReader reader, boolean isRoot) throws XMLStreamException, XmlContentException {
		this(isRoot);
		parse(reader);
	}
	
	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public List<PremisObject> getObjects() {
		return objects;
	}

	public void setObjects(List<PremisObject> objects) {
		this.objects = objects;
	}

	public void addObject(PremisObject object) {
		this.objects.add(object);
	}

	public List<PremisEvent> getEvents() {
		return events;
	}

	public void setEvents(List<PremisEvent> events) {
		this.events = events;
	}

	public void addEvent(PremisEvent event) {
		this.events.add(event);
	}

	public List<PremisAgent> getAgents() {
		return agents;
	}

	public void setAgents(List<PremisAgent> agents) {
		this.agents = agents;
	}

	public void addAgent(PremisAgent agent) {
		this.agents.add(agent);
	}

	public List<PremisRights> getRights() {
		return rights;
	}

	public void setRights(List<PremisRights> rights) {
		this.rights = rights;
	}

	public void addRights(PremisRights rights) {
		this.rights.add(rights);
	}
	
	public boolean validate() {
		boolean valid = true;
		try {
			for (Field f : this.getClass().getDeclaredFields()) {
				if (!f.isAnnotationPresent(RequiredField.class)
						|| (f.isAnnotationPresent(RequiredField.class) && f
								.get(this) != null)) {
					continue;
				} else {
					valid = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;
	}

	public Premis parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		int event = reader.getEventType();
		if(event == START_DOCUMENT) {
			reader.nextTag();
		}
		reader.require(START_ELEMENT, XMLNS, "premis");		
		for (event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {	
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();
					if(localName.equals("object")) {
						String type = reader.getAttributeValue(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,"type");
						if(type.contains(ObjectFile.type)) {
							ObjectFile pObject = new ObjectFile(reader,false);
							pObject.setType(type);
							this.addObject(pObject);
						}
						else if (type.contains(ObjectBitStream.type)) {
							ObjectBitStream pBitstream = new ObjectBitStream(reader,false);
							pBitstream.setType(type);
							this.addObject(pBitstream);							
						}
						else if (type.contains(ObjectRepresentation.type)) {
							ObjectRepresentation pRepresentation = new ObjectRepresentation(reader,false);
							pRepresentation.setType(type);
							this.addObject(pRepresentation);							
						}
					}
					else if(localName.equals("event")) {
						PremisEvent pEvent = new PremisEvent(reader,false);
						this.addEvent(pEvent);
					}
					else if(localName.equals("agent")) {
						PremisAgent pAgent = new PremisAgent(reader);
						this.addAgent(pAgent);
					}
					else if(localName.equals("rights")) {
						PremisRights pRights = new PremisRights(reader);
						this.addRights(pRights);
					}
				break;		
				case END_ELEMENT:
				//return premis when we're at the end of the element
				if(reader.getLocalName().equals("premis")) {
					return this;
				}
			}
		}
		return this;
	}

	public void output(XMLStreamWriter writer) throws XMLStreamException {
		
		if(isRoot) {
			SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer);		
			
			ns = doc.getNamespace(XMLNS,nsSchemaContext.getPrefix());
			/*
			String xmlPrefix = nsSchemaContext.getPrefix();
			if(xmlPrefix != null && xmlPrefix.length() > 0) {
				ns = doc.getNamespace(XMLNS,xmlPrefix);			
			}
			else {
				ns = null;
			}*/
			
			SMOutputElement root = doc.addElement(ns,"premis");		
			RootXmlHelper.output(writer,getAllNamespaceSchemaContexts(),root);		
			outputContents(writer,null,root);
			doc.closeRootAndWriter();  
		}
		else {
			SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
			ns = frag.getNamespace(XMLNS,nsSchemaContext.getPrefix());
			SMOutputElement root = frag.addElement(ns,"premis");
			outputContents(writer,ns,root);
			frag.closeRoot();
		}
	}
	
	private void outputContents(XMLStreamWriter writer,SMNamespace textmd, SMOutputElement root) throws XMLStreamException {	
		//SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer,"1.0","UTF-8",false);
		//SMNamespace xsi = doc.getNamespace(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,SCHEMA_INSTANCE_PREFIX);
		//ns = doc.getNamespace(XMLNS,nsSchemaContext.getPrefix());
		
		//doc.setIndentation("\n ", 1, 1); 
		//SMOutputElement root = doc.addElement(ns,"premis");
		//root.addAttribute(XMLConstants.XMLNS_ATTRIBUTE, XMLNS);
		
		//root.addAttribute(xsi,"schemaLocation", SCHEMA_LOCATION);		
		root.addAttribute("version",VERSION);
		for(PremisObject obj : objects) {
			obj.output(writer);
		}		
		for(PremisEvent event : events) {
			event.output(writer);
		}		
		for(PremisAgent agent : agents) {
			agent.output(writer);
		}		
		for(PremisRights right : rights) {
			right.output(writer);
		}		
		//doc.closeRootAndWriter();  
	}

	@Override
	public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
		List<NamespaceSchemaContext> contexts = new ArrayList<NamespaceSchemaContext>();	
		//Add namespace schema context for this object
		contexts.add(Premis.nsSchemaContext);
		for(PremisObject obj : objects) {
			contexts.addAll(obj.getAllNamespaceSchemaContexts());
		}		
		for(PremisEvent event : events) {
			contexts.addAll(event.getAllNamespaceSchemaContexts());
		}		
		for(PremisAgent agent : agents) {
			contexts.addAll(agent.getAllNamespaceSchemaContexts());
		}		
		for(PremisRights right : rights) {
			contexts.addAll(right.getAllNamespaceSchemaContexts());
		}	
		return contexts;
	}

	@Override
	public NamespaceSchemaContext getNamespaceSchemaContext() {
		return Premis.nsSchemaContext;
	}
}
