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

import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import java.text.ParseException;
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
import edu.harvard.hul.ois.ots.schemas.XmlContent.SimpleLink;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContent;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentParser;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlDateFormat;



public class AmdSec {
	
	private String id;
	private TechMD techmd;
	private List<DigiprovMD> digiprovMD;
	
	public AmdSec() {
		digiprovMD = new ArrayList<DigiprovMD>();
	}
	
	public AmdSec(String id) {
		this();
		this.id = id;
	}
	
	public AmdSec(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this();
		parse(reader);
	}
	
	public String getID() {
		return id;
	}
	public void setID(String id) {
		this.id = id;
	}
	public TechMD getTechmd() {
		return techmd;
	}
	public void setTechmd(TechMD techmd) {
		this.techmd = techmd;
	}
	public List<DigiprovMD> getDigiprovMD() {
		return digiprovMD;
	}
	public void setDigiprovMD(List<DigiprovMD> digiprovMD) {
		this.digiprovMD = digiprovMD;
	}
	public void addDigiprovMD(DigiprovMD digiprov) {
		digiprovMD.add(digiprov);
	}
	
	private AmdSec parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		id = reader.getAttributeValue(null,"ID");
		//move to either techmd or digiprovmd
		int event = reader.nextTag();
		String localName = reader.getLocalName();
		if(localName.equals("techMD")) {
			techmd = new TechMD();
			techmd.setID(reader.getAttributeValue(null,"ID"));
			//move to mdwrap or mdref
			reader.nextTag();
			localName = reader.getLocalName();
			if(localName.equals("mdWrap")) {
				techmd.setMdwrap(makeMdWrap(reader));
				//move to xmlData
				reader.nextTag();							
				XmlData xmldata = makeXmlData(reader);
				techmd.getMdwrap().setXmlData(xmldata);
			}
			else if(localName.equals("mdRef")) {
				techmd.setMdref(makeMdRef(reader));
			}
		}
		else if(localName.equals("digiprovMD")) {
			DigiprovMD digiprov = new DigiprovMD();
			digiprov.setID(reader.getAttributeValue(null,"ID"));
			digiprov.setStatus(reader.getAttributeValue(null,"STATUS"));
			String createdDate = reader.getAttributeValue(null,"CREATED");
			if(createdDate != null) {
				try {
					digiprov.setCreated(XmlDateFormat.parseDateTime(createdDate));
				}
				catch (ParseException e) {
					throw new XMLStreamException("Invalid Date: "+reader.getElementText(),e);
				}
			}
			//if at the start of a digiprov md section, loop until end of amdsec						
			while(!(event == END_ELEMENT && localName.equals("amdSec"))) {
				event = reader.next();
				switch(event) {
				case START_ELEMENT:
					localName = reader.getLocalName();
					if(localName.equals("mdWrap")) {
						digiprov.setMdwrap(makeMdWrap(reader));
						//move to xmlData
						event = reader.nextTag();							
						XmlData xmldata = makeXmlData(reader);
						digiprov.getMdwrap().setXmlData(xmldata);
					}
					else if(localName.equals("mdRef")) {
						digiprov.setMdref(makeMdRef(reader));
					}
					else if(localName.equals("digiprovMD")) {
						digiprov = new DigiprovMD();
						digiprov.setID(reader.getAttributeValue(null,"ID"));
						digiprov.setStatus(reader.getAttributeValue(null,"STATUS"));
						String createdDate2 = reader.getAttributeValue(null,"CREATED");
						if(createdDate2 != null) {
							try {
								digiprov.setCreated(XmlDateFormat.parseDateTime(createdDate2));
							}
							catch (ParseException e) {
								throw new XMLStreamException("Invalid Date: "+reader.getElementText(),e);
							}
						}
					}
						
					break;
				case END_ELEMENT:
					localName = reader.getLocalName();
					if(localName.equals("digiprovMD")) {
						digiprovMD.add(digiprov);
					}
					break;
				}
			}

		}	
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
		//get new local name of the current element
		//String localName = reader.getLocalName();
		QName qname = reader.getName();
		//get a parser
		//XmlContentParser parser = ObjectDescriptor.getParser(qname.getPrefix()+":"+qname.getLocalPart());
		XmlContentParser parser = ParserContainer.getParser(qname.getPrefix()+":"+qname.getLocalPart());
		xmldata.setContent(parser.parse(reader));
		return xmldata;
	}
	
	private MdRef makeMdRef(XMLStreamReader reader) throws XMLStreamException {
		MdRef mdref = new MdRef();
		//<mdRef ID="ID28" LOCTYPE="URN" MDTYPE="OTHER" OTHERMDTYPE="TARGET" xlink:href="urn-3:HUL.DRS.OBJECT:600" />
		mdref.setID(reader.getAttributeValue(null,"ID"));	
		mdref.setLoctype(reader.getAttributeValue(null,"LOCTYPE"));
		mdref.setOtherloctype(reader.getAttributeValue(null,"OTHERLOCTYPE"));
		mdref.setMdtype(reader.getAttributeValue(null,"MDTYPE"));
		mdref.setOthermdtype(reader.getAttributeValue(null,"OTHERMDTYPE"));
		mdref.setLabel(reader.getAttributeValue(null,"LABEL"));
		mdref.setMimetype(reader.getAttributeValue(null,"MIMETYPE"));
		SimpleLink xlink = new SimpleLink(reader);
		mdref.setXlink(xlink);
		return mdref;
	}
	
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
		SMOutputElement amdSec = frag.addElement(METS.ns, "amdSec");
		if(id != null) {
			amdSec.addAttribute("ID",id);
		}
		if(techmd != null) {
			techmd.output(writer);
		}
		if(digiprovMD != null) {
			for(DigiprovMD digi : digiprovMD) {
				digi.output(writer);
			}
		}
		frag.closeRoot();
	}
	
	public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
		List<NamespaceSchemaContext> contexts = new ArrayList<NamespaceSchemaContext>();
		for(DigiprovMD digi : digiprovMD) {
			MdWrap mdwrap = digi.getMdwrap();
			if(mdwrap != null) {
				XmlContent xml = mdwrap.getXmlData().getContent();
				List<NamespaceSchemaContext> subcontexts = xml.getAllNamespaceSchemaContexts();
				if(subcontexts != null) {
					contexts.addAll(subcontexts);
				}
			}
		}
		if(techmd != null) {
			MdWrap mdwrap = techmd.getMdwrap();
			if(mdwrap != null) {
				XmlContent xml = mdwrap.getXmlData().getContent();
				if(xml != null) {
					List<NamespaceSchemaContext> subcontexts = xml.getAllNamespaceSchemaContexts();
					if(subcontexts != null) {
						contexts.addAll(subcontexts);
					}
				}
			}
		}
		
		return contexts;
	}
}
