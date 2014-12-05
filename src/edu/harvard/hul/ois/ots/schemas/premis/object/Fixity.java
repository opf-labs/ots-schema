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

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.premis.Premis;

public class Fixity {
	
	private String messageDigestAlgorithm;
	private String messageDigest;
	private String messageDigestOriginator;
	
	public Fixity() { }
	
	public Fixity(XMLStreamReader reader) throws XMLStreamException {
		parse(reader);
	}
	
	public String getMessageDigestAlgorithm() {
		return messageDigestAlgorithm;
	}
	public void setMessageDigestAlgorithm(String messageDigestAlgorithm) {
		this.messageDigestAlgorithm = messageDigestAlgorithm;
	}
	public String getMessageDigest() {
		return messageDigest;
	}
	public void setMessageDigest(String messageDigest) {
		this.messageDigest = messageDigest;
	}
	public String getMessageDigestOriginator() {
		return messageDigestOriginator;
	}
	public void setMessageDigestOriginator(String messageDigestOriginator) {
		this.messageDigestOriginator = messageDigestOriginator;
	}
	public void output(SMOutputElement parent) throws XMLStreamException {
		SMOutputElement fixity = parent.addElement(Premis.ns,"fixity");	
		if(messageDigestAlgorithm != null)  {
			fixity.addElementWithCharacters(Premis.ns,"messageDigestAlgorithm",messageDigestAlgorithm);
		}
		if(messageDigest != null)  {
			fixity.addElementWithCharacters(Premis.ns,"messageDigest",messageDigest);
		}
		if(messageDigestOriginator != null)  {
			fixity.addElementWithCharacters(Premis.ns,"messageDigestOriginator",messageDigestOriginator);
		}
	}
	public Fixity parse(XMLStreamReader reader) throws XMLStreamException {
		reader.require(START_ELEMENT, Premis.XMLNS, "fixity");	
		for (int event=reader.next(); event!=END_DOCUMENT; event=reader.next()) {
			switch(event) {
				case START_ELEMENT:
					String localName = reader.getLocalName();			
					if(localName.equals("messageDigestAlgorithm")) {
						messageDigestAlgorithm = reader.getElementText();
					}
					else if(localName.equals("messageDigest")) {
						messageDigest = reader.getElementText();
					}
					else if(localName.equals("messageDigestOriginator")) {
						messageDigestOriginator = reader.getElementText();
					}
				break;		
				case END_ELEMENT:
				//return when we're at the end of the element
				if(reader.getLocalName().equals("fixity")) {
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
