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
package edu.harvard.hul.ois.ots.schemas.premis.rights;

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
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
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContent;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentParser;
import edu.harvard.hul.ois.ots.schemas.premis.Premis;
import edu.harvard.hul.ois.ots.schemas.premis.PremisParser;


public class PremisRights implements XmlComponent {
	
	private List<RightsStatement> rightsStatements;
	private List<XmlContent> extensions;
	private String xmlID;
	private String version;
	
	private boolean isRoot;
	
	public PremisRights() {
		this(false);
	}
	
	public PremisRights(boolean isRoot) {
		rightsStatements = new ArrayList<RightsStatement>();
		extensions = new ArrayList<XmlContent>();
		this.isRoot = isRoot;
	}
	
	public PremisRights(XMLStreamReader reader, boolean isRoot) throws XMLStreamException, XmlContentException {
		this(isRoot);
		parse(reader);
	}
	
	public PremisRights(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this();
		parse(reader);
	}
	
	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public List<RightsStatement> getRightsStatements() {
		return rightsStatements;
	}

	public void setRightsStatements(List<RightsStatement> rightsStatements) {
		this.rightsStatements = rightsStatements;
	}
	
	public void addRightsStatments(RightsStatement rights) {
		rightsStatements.add(rights);
	}

	public List<XmlContent> getExtensions() {
		return extensions;
	}

	public void setExtensions(List<XmlContent> extensions) {
		this.extensions = extensions;
	}
	
	public void addExtension(XmlContent content) {
		extensions.add(content);
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
			SMOutputElement root = doc.addElement(Premis.ns,"rights");			
			RootXmlHelper.output(writer,getAllNamespaceSchemaContexts(),root);
			outputContents(writer,root);
			doc.closeRoot();
		}
		else {
			SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
			Premis.ns = frag.getNamespace(Premis.XMLNS,Premis.nsSchemaContext.getPrefix());
			SMOutputElement fragRoot = frag.addElement(Premis.ns,"rights");
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
		for(RightsStatement stmt : rightsStatements) {
			stmt.output(writer,obj);
		}
		for(XmlContent xml : extensions) {
			//output rightsExtension then xml content
			obj.addElement(Premis.ns,"rightsExtension");
			xml.output(writer);
		}
	}

	public PremisRights parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		int event = reader.getEventType();
		if(event == START_DOCUMENT) {
			reader.nextTag();
		}
		reader.require(START_ELEMENT, Premis.XMLNS, "rights");	
		xmlID = reader.getAttributeValue(null,"xmlID");
		version = reader.getAttributeValue(null,"version");
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("rights"))) {	
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();	
				if(localName.equals("rightsStatement")) {
					rightsStatements.add(new RightsStatement(reader));
				}
				else if(localName.equals("rightsExtension")) {
					//advance to next element
					reader.nextTag();
					QName qname = reader.getName();
					//get a parser
					XmlContentParser parser = PremisParser.getParser(qname.getPrefix()+":"+qname.getLocalPart());
					extensions.add(parser.parse(reader));
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
		//check exentions
		for(XmlContent xml : extensions) {
			if(xml != null) {
				contexts.addAll(xml.getAllNamespaceSchemaContexts());
			}
		}
		//check rightstatements xlink
		for(RightsStatement stmt : rightsStatements) {
			if(stmt.getStatementIdentifierXlink() != null) {
				contexts.add(SimpleLink.nsSchemaContext);
				break;
			}
		}
		return contexts;
	}

	@Override
	public NamespaceSchemaContext getNamespaceSchemaContext() {
		return Premis.nsSchemaContext;
	}
}
