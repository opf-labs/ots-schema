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
package edu.harvard.hul.ois.ots.schemas.METS;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;
import edu.harvard.hul.ois.ots.schemas.XmlContent.ParserContainer;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContent;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentParser;



public class DmdSec {
	
	private String id;
	private String groupid;
	private MdWrap mdwrap;
	
	public DmdSec() { }
	
	public DmdSec(String id) { 
		this.id = id;
	}
	
	public DmdSec(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		parse(reader);
	}
	
	public String getID() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public MdWrap getMdwrap() {
		return mdwrap;
	}
	public void setMdwrap(MdWrap mdwrap) {
		this.mdwrap = mdwrap;
	}

	private DmdSec parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		id = reader.getAttributeValue(null,"ID");
		groupid = reader.getAttributeValue(null,"GROUPID");
		//move to mdwrap
		reader.nextTag();
		mdwrap = makeMdWrap(reader);
		//move to xmlData
		reader.nextTag();						
		XmlData xmldata = makeXmlData(reader);		
		mdwrap.setXmlData(xmldata);
		return this;
	}
	
	private MdWrap makeMdWrap(XMLStreamReader reader) {
		MdWrap mdwrap = new MdWrap();
		mdwrap.setID(reader.getAttributeValue(null,"ID"));						
		mdwrap.setMdType(reader.getAttributeValue(null,"MDTYPE"));
		mdwrap.setOthermdType(reader.getAttributeValue(null,"OTHERMDTYPE"));
		mdwrap.setMimetype(reader.getAttributeValue(null,"MIMETYPE"));
		return mdwrap;
	}
	
	private XmlData makeXmlData(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		XmlData xmldata = new XmlData();
		//advance to xmldata contents
		reader.nextTag();
		//get name of the current element
		QName qname = reader.getName();
		//get a parser
		//XmlContentParser parser = ObjectDescriptor.getParser(qname.getPrefix()+":"+qname.getLocalPart());
		XmlContentParser parser = ParserContainer.getParser(qname.getPrefix()+":"+qname.getLocalPart());
		xmldata.setContent(parser.parse(reader));
		return xmldata;
	}
	
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
		SMOutputElement dmdSec = frag.addElement(METS.ns, "dmdSec");
		if(id != null) {
			dmdSec.addAttribute("ID",id);
		}
		if(groupid != null) { 
			dmdSec.addAttribute("GROUPID",groupid);
		}
		if(mdwrap != null) {
			mdwrap.output(writer);
		}
		frag.closeRoot();
	}
	
	public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
		List<NamespaceSchemaContext> contexts = new ArrayList<NamespaceSchemaContext>();
		if(mdwrap != null) {
			XmlContent xml = mdwrap.getXmlData().getContent();
			List<NamespaceSchemaContext> subcontexts = xml.getAllNamespaceSchemaContexts();
			if(subcontexts != null) {
				contexts.addAll(subcontexts);
			}
		}
		return contexts;
	}
}
