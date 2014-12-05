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

import static javax.xml.stream.XMLStreamConstants.END_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputDocument;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlComponent;
import edu.harvard.hul.ois.ots.schemas.StaxUtils.RootXmlHelper;
import edu.harvard.hul.ois.ots.schemas.XmlContent.NamespaceSchemaContext;
import edu.harvard.hul.ois.ots.schemas.XmlContent.SimpleLink;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class METS implements XmlComponent {
	
	protected String label;
	protected String type;
	protected String profile;
	protected String objid;
	protected MetsHdr metshdr;
	protected List<AmdSec> amdsecs;
	protected List<DmdSec> dmdsecs;
	protected FileSec filesec;
	protected StructMap structmap;
	protected int maxid;
	protected Map <String,AmdSec> amdsecmap;
	protected Map <String,DmdSec> dmdsecmap;
	protected Map <String,FileFLocat> filesByFileID; //files keyed by METS fptr FILEID
	protected Map <String,FileFLocat> filesByHref; //files keyed by METS FLocat xlink:href
	protected List<NamespaceSchemaContext> localContexts;
	protected NamespaceSchemaContext nsSchemaContext = new NamespaceSchemaContext(XMLNS, XML_DEFAULT_PREFIX, SCHEMA_LOCATION);
	
	public static final String XMLNS = "http://www.loc.gov/METS/";
	public static final String SCHEMA_LOCATION = "http://www.loc.gov/standards/mets/mets.xsd";	
	public static final String XML_DEFAULT_PREFIX = null;
	
	public static SMNamespace ns = null;
	
	public METS() {
		amdsecs = new ArrayList<AmdSec>();
		dmdsecs = new ArrayList<DmdSec>();
		filesByFileID  = new HashMap<String,FileFLocat>();
		filesByHref  = new HashMap<String,FileFLocat>();
		amdsecmap = new HashMap<String,AmdSec>();
		dmdsecmap = new HashMap<String,DmdSec>();
		localContexts = new ArrayList<NamespaceSchemaContext>();
		
		filesec = new FileSec();
		structmap = new StructMap();
		
		metshdr = new MetsHdr();
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getObjid() {
		return objid;
	}
	public void setObjid(String objid) {
		this.objid = objid;
	}
	public MetsHdr getMetshdr() {
		return metshdr;
	}
	public void setMetshdr(MetsHdr metshdr) {
		this.metshdr = metshdr;
	}
	public List<AmdSec> getAmdsecs() {
		return amdsecs;
	}
	public void setAmdsecs(List<AmdSec> amdsecs) {
		this.amdsecs = amdsecs;
	}
	public void addAmdsec(AmdSec amdsec) {
		amdsecs.add(amdsec);
		amdsecmap.put(amdsec.getID(),amdsec);
	}
	public List<DmdSec> getDmdsecs() {
		return dmdsecs;
	}
	public void setDmdsecs(List<DmdSec> dmdsecs) {
		this.dmdsecs = dmdsecs;
	}
	public void addDmdSec(DmdSec dmdsec) {
		dmdsecs.add(dmdsec);
		dmdsecmap.put(dmdsec.getID(),dmdsec);
	}
	public DmdSec getDmdSec(String id) {
		return dmdsecmap.get(id);
	}
	
	public AmdSec getAmdSec(String id) {
		return amdsecmap.get(id);
	}
	public FileSec getFilesec() {
		return filesec;
	}
	public void setFilesec(FileSec filesec) {
		this.filesec = filesec;
	}
	public StructMap getStructmap() {
		return structmap;
	}
	public void setStructmap(StructMap structmap) {
		this.structmap = structmap;
	}
	
	public synchronized String nextMaxID() {
		maxid++;
		return "ID"+maxid;
	}
	
	public void resetMaxID(int newIDVal) {
		maxid = newIDVal;
	}
	
	public String getMaxID() {
		return "ID"+maxid;
	}
	
	public int getMaxID_int() {
		return maxid;
	}
	
	protected void initializeMaxID() {
		if(metshdr != null) {
			checkMaxID(metshdr.getId());
			for(Agent agent : metshdr.getAgents()) {
				checkMaxID(agent.getId());
			}
		}
		for(DmdSec dmdsec : dmdsecs) {
			checkMaxID(dmdsec.getID());
			if(dmdsec.getMdwrap() != null) {
				checkMaxID(dmdsec.getMdwrap().getID());
			}
		}
		for(AmdSec amdsec : amdsecs) {
			checkMaxID(amdsec.getID());
			if(amdsec.getTechmd() != null) {
				checkMaxID(amdsec.getTechmd().getID());
				if(amdsec.getTechmd().getMdwrap() != null) {
					checkMaxID(amdsec.getTechmd().getMdwrap().getID());
				}
				if(amdsec.getTechmd().getMdref() != null) {
					checkMaxID(amdsec.getTechmd().getMdref().getID());
				}
			}
			for(DigiprovMD digi : amdsec.getDigiprovMD()) {
				checkMaxID(digi.getID());
				if(digi.getMdwrap() != null) {
					checkMaxID(digi.getMdwrap().getID());
				}
				if(digi.getMdref() != null) {
					checkMaxID(digi.getMdref().getID());
				}
			}
		}
		checkMaxID(filesec.getID());
		for(FileGrp filegrp : filesec.getFilegrp()) {
			checkMaxID(filegrp.getID());
			for(FileFLocat cf : filegrp.getFiles()) {
				checkMaxID(cf.getFileID());
				checkMaxID(cf.getFlocatID());
			}
		}
		checkMaxID(structmap.getID());
		checkDivMaxID(structmap.getDiv());
	}
	
	private void checkDivMaxID(Div div) {
		checkMaxID(div.getID());
		for(Fptr fptr : div.getFptrs()) {
			checkMaxID(fptr.getID());
		}
		for(Div d : div.getDivs()) {
			checkDivMaxID(d);
		}
	}
	
	private void checkMaxID(String idString) {
		int id = 0;
		if(idString != null && idString.startsWith("ID")) {
			id = Integer.parseInt(idString.substring(2));
		}
		if(id > maxid) {
			maxid = id;
		}
	}
	
	public boolean validate() {
		//TODO
		return true;
	}

	@Override
	public List<NamespaceSchemaContext> getAllNamespaceSchemaContexts() {
		List<NamespaceSchemaContext> contexts = new ArrayList<NamespaceSchemaContext>();	
		//Add namespace schema context for this object
		contexts.add(nsSchemaContext);
		//add local contexts
		contexts.addAll(localContexts);
		//add xlink
		contexts.add(SimpleLink.nsSchemaContext);	
		//get all schemas and namespaces in dmdsecs
		for(DmdSec dmdsec : dmdsecs) {
			contexts.addAll(dmdsec.getAllNamespaceSchemaContexts());
		}
		//get all schemas and namespaces in amdsecs
		for(AmdSec amdsec : amdsecs) {
			contexts.addAll(amdsec.getAllNamespaceSchemaContexts());
		}
		return contexts;
	}

	@Override
	public NamespaceSchemaContext getNamespaceSchemaContext() {
		return nsSchemaContext;
	}

	@Override
	public void output(XMLStreamWriter writer) throws XMLStreamException {		
		SMOutputDocument doc = SMOutputFactory.createOutputDocument(writer);

		String xmlPrefix = nsSchemaContext.getPrefix();
		if(xmlPrefix != null && xmlPrefix.length() > 0) {
			ns = doc.getNamespace(XMLNS,xmlPrefix);			
		}
		else {
			ns = null;
		}
		
		SMOutputElement root = doc.addElement(ns,"mets");	
		RootXmlHelper.output(writer,getAllNamespaceSchemaContexts(),root);
		
		if(label != null)
			root.addAttribute("LABEL", label);
		if(type != null)
			root.addAttribute("TYPE", type);
		if(profile != null)
			root.addAttribute("PROFILE", profile);
		if(objid != null)
			root.addAttribute("OBJID", objid);
		if(metshdr != null) 
			metshdr.output(writer);		
		for(DmdSec dmdsec : dmdsecs) {
			dmdsec.output(writer);
		}		
		
		for(AmdSec amdsec : amdsecs) {
			amdsec.output(writer);
		}		
		filesec.output(writer);		
		structmap.output(writer);
	}

	@Override
	public METS parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		int event = reader.next();
		reader.require(START_ELEMENT, METS.XMLNS, "mets");		
		while(event!=END_DOCUMENT) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("mets")) {				
						localContexts.addAll(RootXmlHelper.parse(reader));				
						label = reader.getAttributeValue(null,"LABEL");
						type = reader.getAttributeValue(null,"TYPE");
						profile = reader.getAttributeValue(null,"PROFILE");
						objid = reader.getAttributeValue(null,"OBJID");
					}
					else if(localName.equals("metsHdr")) {
						metshdr = new MetsHdr(reader);
					}
					else if(localName.equals("dmdSec")) {
						DmdSec dmdsec = new DmdSec(reader);
						dmdsecs.add(dmdsec);
						dmdsecmap.put(dmdsec.getID(),dmdsec);
					}					
					else if(localName.equals("amdSec")) {
						AmdSec amdsec = new AmdSec(reader);					
						amdsecs.add(amdsec);
						amdsecmap.put(amdsec.getID(),amdsec);
					}
					else if(localName.equals("fileSec")) {
						filesec = new FileSec(reader);						
						//for each file in a filegroup
						for(FileGrp filegrp : filesec.getFilegrp()) {
							for(FileFLocat fileFLocat : filegrp.getFiles()) {
								//add file to files hash
								filesByFileID.put(fileFLocat.getFileID(),fileFLocat);
								filesByHref.put(fileFLocat.getXlink().getHref().toString(),fileFLocat);
							}
						}
					}
					else if(localName.equals("structMap")) {
						structmap = new StructMap(reader);
					}
			}
			event=reader.next();
		}
		initializeMaxID();
		return this;
	}

	@Override
	public void setRoot(boolean root) {
		//do nothing
	}
}
