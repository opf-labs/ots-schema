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

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

public class StructMap {
	
	private String id;
	private String type;
	private Div div;
	
	public StructMap() {
		div = new Div();
	}
	
	public StructMap(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public Div getDiv(){
		return div;
	}

	public void setDiv(Div div) {
		this.div = div;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	private StructMap parse(XMLStreamReader reader) throws XMLStreamException {
		id = reader.getAttributeValue(null,"ID");
		type = reader.getAttributeValue(null,"TYPE");
		int event = reader.getEventType();
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("structMap"))) {
			//advance to a fileGrp
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();
				if(localName.equals("div")) {
					div = new Div(reader);
				}
				break;							
			case END_ELEMENT:
				localName = reader.getLocalName();
				break;								
			}
		}
		return this;
	}
	
	public int getMaxIdAttribute() {
		//get last fptr of last div
		Div lastDiv = getLastDiv(div.getDivs().get(div.getDivs().size()-1));
		List<Fptr> fptrs = lastDiv.getFptrs();
		Fptr lastFptr = fptrs.get(fptrs.size()-1);
		String maxId = lastFptr.getID();
		int max = 0;
		if(maxId != null) {
			maxId = maxId.substring(2);
			max = Integer.parseInt(maxId);
		}
		return max;
	}
	
	private Div getLastDiv(Div div) {
		if(div.getDivs().size() > 0) {
			List<Div> divList = div.getDivs();
			Div lastDiv = divList.get(divList.size()-1);
			return getLastDiv(lastDiv);
		}
		else {
			return div;
		}
	}
	
	/**
	 * outputs all files in the filesec
	 * @param writer
	 * @throws XMLStreamException
	 */
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		output(writer,null);
	}
	
	/**
	 * only puts fptr elements for those with id references in the filterIDs list
	 * @param writer
	 * @param filterIDs
	 * @throws XMLStreamException
	 */
	public void output(XMLStreamWriter writer, List<String> filterIDs) throws XMLStreamException {
		SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
		SMOutputElement structmap = frag.addElement(METS.ns, "structMap");
		if(id != null) {
			structmap.addAttribute("ID",id);
		}
		if(type != null) {
			structmap.addAttribute("TYPE",type);
		}
		div.output(writer,filterIDs);
		frag.closeRoot();
	}
	
	public Div findDiv(String divID) {
		Div foundDiv = null;
		for(Div d : div.getDivs()){
			foundDiv = findDiv(d,divID);
			if(foundDiv != null) {
				break;
			}
		}
		return foundDiv;
	}
	
	/**
	 * finds and returns the div with the given divID
	 * @param divID
	 * @return
	 */
	private Div findDiv(Div div, String divID) {
		Div foundDiv = null;
		if(div.getID().equals(divID)) {
			foundDiv = div;
		}
		//look for sub divs
		else {
			for(Div d : div.getDivs()) {
				foundDiv = findDiv(d,divID);	
				if(foundDiv != null) {
					break;
				}
			}
		}
		return foundDiv;
	}

	
}
