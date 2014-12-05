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
package edu.harvard.hul.ois.ots.schemas.HulDrsAdminMD;

import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentParser;


public class HulDrsAdminParser implements XmlContentParser{
	@Override
	public HulDrsAdminMD parse(XMLStreamReader reader) throws XMLStreamException {		
		reader.require(START_ELEMENT, HulDrsAdminMD.XMLNS, "hulDrsAdmin");
		reader.nextTag();
		if(reader.getLocalName().equals("drsObject")) {
			return new DrsObjectMD(reader);
		}
		else if(reader.getLocalName().equals("drsFile")) {
			return new DrsFileMD(reader);
		}
		else {
			return null;
		}
	}
}
