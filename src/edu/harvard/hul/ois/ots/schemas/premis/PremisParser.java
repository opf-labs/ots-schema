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

package edu.harvard.hul.ois.ots.schemas.premis;

import java.util.Hashtable;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import edu.harvard.hul.ois.ots.schemas.XmlComponent;
import edu.harvard.hul.ois.ots.schemas.XmlContent.AnyXml;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentParser;
import edu.harvard.hul.ois.ots.schemas.premis.agent.PremisAgent;
import edu.harvard.hul.ois.ots.schemas.premis.event.PremisEvent;
import edu.harvard.hul.ois.ots.schemas.premis.object.ObjectBitStream;
import edu.harvard.hul.ois.ots.schemas.premis.object.ObjectFile;
import edu.harvard.hul.ois.ots.schemas.premis.object.ObjectRepresentation;
import edu.harvard.hul.ois.ots.schemas.premis.rights.PremisRights;



public class PremisParser implements XmlContentParser{
	
	public static Map<String,XmlContentParser> delegates = new Hashtable<String,XmlContentParser>();
	
	@Override
	public XmlComponent parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		//reader.next(); // advance to first element
		String localName = reader.getLocalName();
		if(localName.equals("premis")) {
			return new Premis(reader);
		}
		else if(localName.equals("object")) {
			String type = reader.getAttributeValue(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,"type");
			if(type != null) {
				if(type.contains(ObjectFile.type)) {
					ObjectFile pObject = new ObjectFile(reader,false);
					pObject.setType(type);
					return pObject;
				}
				else if (type.contains(ObjectBitStream.type)) {
					ObjectBitStream pBitstream = new ObjectBitStream(reader,false);
					pBitstream.setType(type);
					return pBitstream;							
				}
				else if (type.contains(ObjectRepresentation.type)) {
					ObjectRepresentation pRepresentation = new ObjectRepresentation(reader,false);
					pRepresentation.setType(type);
					return pRepresentation;						
				}
			}
		}
		else if(localName.equals("event")) {
			return new PremisEvent(reader,false);
		}
		else if(localName.equals("agent")) {
			return new PremisAgent(reader);
		}
		else if(localName.equals("rights")) {
			return new PremisRights(reader);
		}
		return null;
	}
	
	public static void registerParser(String name,XmlContentParser parser) {
		delegates.put(name,parser);
	}
	
	public static XmlContentParser getParser(String name) {
		XmlContentParser parser =  delegates.get(name);
		if(parser != null) {
			return parser;
		}
		else {
			return new AnyXml();
		}
	}
}
