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
import java.util.Arrays;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.staxmate.SMOutputFactory;
import org.codehaus.staxmate.out.SMOutputElement;
import org.codehaus.staxmate.out.SMRootFragment;

public class Div {
	
	public static final String PAGE = "PAGE";
	public static final String INTERMEDIATE = "INTERMEDIATE";
	public static final String CITATION = "CITATION";
	
	private String id;
	private String label;
	private List<String> dmdids;
	private String type;
	private String order;
	private String orderLabel;
	private List<Fptr> fptrs;
	private List<Div> divs;
		
	public Div() {
		dmdids = new ArrayList<String>();
		fptrs = new ArrayList<Fptr>();
		divs = new ArrayList<Div>();
	}
	
	public Div(XMLStreamReader reader) throws XMLStreamException {
		this();
		parse(reader);
	}
	
	public String getID() {
		return id;
	}
	public void setID(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<String> getDmdids() {
		return dmdids;
	}
	public void setDmdids(List<String> dmdids) {
		this.dmdids = dmdids;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getOrderLabel() {
		return orderLabel;
	}
	public void setOrderLabel(String orderLabel) {
		this.orderLabel = orderLabel;
	}
	public List<Fptr> getFptrs() {
		return fptrs;
	}
	public void setFptrs(List<Fptr> fptrs) {
		this.fptrs = fptrs;
	}
	
	public List<Div> getDivs() {
		return divs;
	}

	public void setDivs(List<Div> divs) {
		this.divs = divs;
	}

	public void addFptr(Fptr fptr) {
		fptrs.add(fptr);
	}
	
	public void addDmdID(String dmdid) {
		dmdids.add(dmdid);
	}
	
	public void addDiv(Div div) {
		divs.add(div);
	}
	
	private Div parse(XMLStreamReader reader)  throws XMLStreamException {
		id = reader.getAttributeValue(null,"ID");
		label = reader.getAttributeValue(null,"LABEL");
		type = reader.getAttributeValue(null,"TYPE");
		order = reader.getAttributeValue(null,"ORDER");
		orderLabel = reader.getAttributeValue(null,"ORDERLABEL");
		String dmdidVals = reader.getAttributeValue(null,"DMDID");
		if(dmdidVals != null) {
			dmdids = Arrays.asList(dmdidVals.split(" "));
		}
		
		int event = reader.getEventType();
		String localName = "";
		while(!(event == END_ELEMENT && localName.equals("div"))) {
			event = reader.next();
			switch(event) {
			case START_ELEMENT:
				localName = reader.getLocalName();
				if(localName.equals("div")) {
					Div subDiv = new Div(reader);
					divs.add(subDiv);
				}
				else if(localName.equals("fptr")) {
					Fptr fptr = new Fptr();
					fptr.setID(reader.getAttributeValue(null,"ID"));
					fptr.setFileID(reader.getAttributeValue(null,"FILEID"));
					fptrs.add(fptr);
				}
				break;
			case END_ELEMENT:
				localName = reader.getLocalName();
				break;
			}
		}
		
		return this;
	}
	
	/**
	 * outputs all files in the div
	 * @param writer
	 * @throws XMLStreamException
	 */
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		output(writer,null);
	}
	
	/**
	 * only outputs fptrs for files that are in the filtered identifier list
	 * @param writer
	 * @param filterIDs
	 * @throws XMLStreamException
	 */
	public void output(XMLStreamWriter writer, List<String> filterIDs) throws XMLStreamException {
		SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
		SMOutputElement div = frag.addElement(METS.ns, "div");
		if(id != null) {
			div.addAttribute("ID",id);
		}
		if(dmdids.size() > 0) {
			String allDmdids = "";
			for(String dmdid : dmdids) {
				allDmdids = allDmdids + " " + dmdid;
			}
			allDmdids = allDmdids.trim();
			div.addAttribute("DMDID",allDmdids);
		}
		if(order != null) {
			div.addAttribute("ORDER",order);
		}
		if(orderLabel != null) {
			div.addAttribute("ORDERLABEL",orderLabel);
		}
		if(label != null) {
			div.addAttribute("LABEL",label);
		}
		if(type != null) {
			div.addAttribute("TYPE",type);
		}
		for(Fptr fptr : fptrs) {
			boolean outputIt = true;
			//if we are filtering the output, and if filter doesn't contain the current ID then don't output
			if(filterIDs != null && !filterIDs.contains(fptr.getFileID())) {
				outputIt = false;
			}
			
			if(outputIt) {
				SMOutputElement fptr_e = div.addElement(METS.ns,"fptr");
				if(fptr.getID() != null) {
					fptr_e.addAttribute("ID", fptr.getID());
				}
				if(fptr.getFileID() != null) {
					fptr_e.addAttribute("FILEID", fptr.getFileID());
				}
			}
		}
		for(Div children : divs) {
			children.output(writer,filterIDs);
		}
		frag.closeRoot();
	}
}
