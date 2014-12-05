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
package edu.harvard.hul.ois.ots.schemas.premis.agent;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMOutputDocument;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

import edu.harvard.hul.ois.ots.schemas.XmlComponent;
import edu.harvard.hul.ois.ots.schemas.StaxUtils.RootXmlHelper;
import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;
import edu.harvard.hul.ois.ots.schemas.XmlContent.SimpleLink;
import edu.harvard.hul.ois.ots.schemas.premis.Premis;

public class PremisAgent implements XmlComponent {
	
	private List<String> names;
	private String type;
	private List<AgentIdentifier> agentIdentifiers;
	private String xmlID;
	private String version;
	
	private boolean isRoot;
	
	public PremisAgent() {
		this(false);
	}
	
	public PremisAgent(boolean isRoot) {
		names = new ArrayList<String>();
		agentIdentifiers = new ArrayList<AgentIdentifier>();
		this.isRoot = isRoot;
	}
	
	public PremisAgent(XMLStreamReader reader,boolean isRoot) throws XMLStreamException {
		this(isRoot);
		parse(reader);
	}
	
	public PremisAgent(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}
	
	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}
	
	public void addName(String name) {
		names.add(name);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<AgentIdentifier> getAgentIdentifiers() {
		return agentIdentifiers;
	}

	public void setAgentIdentifiers(List<AgentIdentifier> agentIdentifiers) {
		this.agentIdentifiers = agentIdentifiers;
	}
	
	public void addAgentIdentifier(AgentIdentifier agentIdentifier) {
		agentIdentifiers.add(agentIdentifier);
	}

	public String getXmlID() {
		return xmlID;
	}

	public void setXmlID(String xmlID) {
		this.xmlID = xmlID;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion() {
		this.version = Premis.VERSION;
	}

	public void output(XMLStreamWriter writer) throws XMLStreamException {
		if(isRoot) {
			SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer,"1.0","UTF-8",false);	
			Premis.ns = doc.getNamespace(Premis.XMLNS,Premis.nsSchemaContext.getPrefix());
			SMOutputElement root = doc.addElement(Premis.ns,"agent");			
			RootXmlHelper.output(writer,getAllNamespaceSchemaContexts(),root);
			outputContents(writer,root);
			doc.closeRoot();
		}
		else {
			SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
			Premis.ns = frag.getNamespace(Premis.XMLNS,Premis.nsSchemaContext.getPrefix());
			SMOutputElement fragRoot = frag.addElement(Premis.ns,"agent");
			outputContents(writer,fragRoot);
			frag.closeRoot();
		}		
	}
	
	private void outputContents(XMLStreamWriter writer,SMOutputElement obj) throws XMLStreamException {
		if(xmlID != null) {
			obj.addAttribute("xmlID",xmlID);
		}
		if(version != null) {
			obj.addAttribute("version",version);
		}
		for(AgentIdentifier identifier : agentIdentifiers) {
			identifier.output(writer/*,obj*/);
		}
		for(String name : names) {
			obj.addElementWithCharacters(Premis.ns,"agentName",name);
		}
		if(type != null) {
			obj.addElementWithCharacters(Premis.ns,"agentType",type);
		}
	}

	public PremisAgent parse(XMLStreamReader reader) throws XMLStreamException {
		int event = reader.getEventType();
		if(event == START_DOCUMENT) {
			reader.nextTag();
		}
		reader.require(START_ELEMENT, Premis.XMLNS, "agent");	
		xmlID = reader.getAttributeValue(null,"xmlID");
		version = reader.getAttributeValue(null,"version");
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("agent"))) {	
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();	
				if(localName.equals("agentIdentifier")) {
					agentIdentifiers.add(new AgentIdentifier(reader));
				}
				else if(localName.equals("agentName")) {
					names.add(reader.getElementText());
				}
				else if(localName.equals("agentType")) {
					type = reader.getElementText();
				}
				break;
			case END_ELEMENT:
				localName = reader.getLocalName();
				break;								
			}
		}

		return this;
	}

	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
		List<NamespaceSchemaContext> contexts = new ArrayList<NamespaceSchemaContext>();
		contexts.add(Premis.nsSchemaContext);
		//check agentIdentifiers for xlink
		if(agentIdentifiers.size() > 0) {
			SimpleLink xlink = agentIdentifiers.get(0).getXlink();
			if(xlink != null) {
				contexts.add(SimpleLink.nsSchemaContext);
			}
		}
		return contexts;
	}

	@Override
	public NamespaceSchemaContext getNamespaceSchemaContext() {
		return Premis.nsSchemaContext;
	}
}
