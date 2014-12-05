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

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

public class MetsHdr {

	private String id;
	private ArrayList<String> admid;
	private String createDate;
	private List<Agent> agents;
	
	public MetsHdr() {
		admid = new ArrayList<String>();
		agents = new ArrayList<Agent>();
	}
	
	public MetsHdr(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getAdmID() {
		return admid;
	}
	public void setAdmID(ArrayList<String> admid) {
		this.admid = admid;
	}
	public void addAdmID(String id) {
		if(!admid.contains(id)) {
			admid.add(id);
		}
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}		
	public List<Agent> getAgents() {
		return agents;
	}

	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}
	
	public void addAgent(Agent agent) {
		agents.add(agent);
	}

	private MetsHdr parse(XMLStreamReader reader) throws XMLStreamException {
		id = reader.getAttributeValue(null,"ID");
		String admids = reader.getAttributeValue(null,"ADMID");
		if (admids != null) {
			for(String s : admids.split(" ")) {
				admid.add(s);
			}
		    //admid = Arrays.asList(admids.split(" "));
		}
		else admid = new ArrayList<String> ();
		createDate = reader.getAttributeValue(null,"CREATEDATE");
		int event = reader.getEventType();
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("metsHdr"))) {
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();
				if(localName.equals("agent")) {
					Agent agent = new Agent();
					agent.setId(reader.getAttributeValue(null,"ID"));
					agent.setRole(reader.getAttributeValue(null,"ROLE"));
					agent.setType(reader.getAttributeValue(null,"TYPE"));
					//advance to name element
					reader.nextTag();
					agent.setName(reader.getElementText());
					agents.add(agent);
				}
				break;
			case END_ELEMENT:
				localName = reader.getLocalName();
				break;
			}
		}		
		return this;
	}
	
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
		SMOutputElement metsHdr = frag.addElement(METS.ns,"metsHdr");
		if(id != null)
			metsHdr.addAttribute("ID",id);
		String admid_s = "";
		for(String id : admid) {
			admid_s = admid_s + " " + id;
		}
		if(admid_s.length() > 0) {
			admid_s = admid_s.trim();
			metsHdr.addAttribute("ADMID",admid_s);
		}
		if(createDate != null)
			metsHdr.addAttribute("CREATEDATE",createDate);
		for(Agent agent : agents) {
			SMOutputElement agentElement = metsHdr.addElement(METS.ns,"agent");			
			agentElement.addAttribute("ID",agent.getId());
			if(agent.getOthertype() != null)
				agentElement.addAttribute("OTHERTYPE",agent.getOthertype());
			if(agent.getRole() != null)
				agentElement.addAttribute("ROLE",agent.getRole());
			if(agent.getType() != null)
				agentElement.addAttribute("TYPE",agent.getType());
			SMOutputElement agentElementName = agentElement.addElement(METS.ns,"name");
			agentElementName.addCharacters(agent.getName());			
		}
		frag.closeRoot();
	}
}
