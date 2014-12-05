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
package edu.harvard.hul.ois.ots.schemas.premis.object;

import static javax.xml.stream.XMLStreamConstants.END_DOCUMENT;
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

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.premis.Premis;
import edu.harvard.hul.ois.ots.schemas.premis.PremisDate;
import edu.harvard.hul.ois.ots.schemas.premis.PremisDateException;

public class PreservationLevel {
	
	private String value;
	private String role;
	private List<String> rationales;
	private PremisDate dateAssigned;
	
	public PreservationLevel() {
		rationales = new ArrayList<String>();
		dateAssigned = new PremisDate();
	}	
	
	public PreservationLevel(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		this();
		parse(reader);
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<String> getRationales() {
		return rationales;
	}

	public void setRationales(List<String> rationales) {
		this.rationales = rationales;
	}
	
	public void addRationale(String rationale) {
		this.rationales.add(rationale);
	}
	
	public PremisDate getDateAssigned() {
		return dateAssigned;
	}

	public void setDateAssigned(String dateAssigned) throws PremisDateException {
		this.dateAssigned.setValue(dateAssigned);
	}
	
	public void output(XMLStreamWriter writer) throws XMLStreamException {
		SMRootFragment frag = SMOutputFactory.createOutputFragment(writer);
		SMOutputElement pLevel = frag.addElement(Premis.ns,"preservationLevel");	
		if(value != null)  {
			pLevel.addElementWithCharacters(Premis.ns,"preservationLevelValue",value);
		}
		if(role != null)  {
			pLevel.addElementWithCharacters(Premis.ns,"preservationLevelRole",role);
		}	
		for(String rationale : rationales) {
			pLevel.addElementWithCharacters(Premis.ns,"preservationLevelRationale",rationale);
		}
		if(dateAssigned.toString() != null)  {
			pLevel.addElementWithCharacters(Premis.ns,"preservationLevelDateAssigned",dateAssigned.toString());
		}
		frag.closeRoot();		
	}

	public PreservationLevel parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		reader.require(START_ELEMENT, Premis.XMLNS, "preservationLevel");		
		for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("preservationLevelValue")) {
						value = reader.getElementText();
					}
					else if(localName.equals("preservationLevelRole")) {
						role = reader.getElementText();
					}
					else if(localName.equals("preservationLevelRationale")) {
						rationales.add(reader.getElementText());
					}
					else if(localName.equals("preservationLevelDateAssigned")) {
						setDateAssigned(reader.getElementText());
					}
				break;		
				case END_ELEMENT:
				//return when we're at the end of the element
				if(reader.getLocalName().equals("preservationLevel")) {
					return this;
				}
			}
		}
		return this;

	}

	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

}
