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
package edu.harvard.hul.ois.ots.schemas.MIX;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContent;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentParser;

/** The XmlContentParser for MIX. The ObjectDescriptor will designate
 *  this as a delegate for the "mix" element in the MIX namespace.
 *  
 *  This implements the MIX 2.0 schema: http://www.loc.gov/standards/mix/mix20/mix20.xsd
 *  
 *  The MIX homepage is at http://www.loc.gov/standards/mix/ 
 */
public class MixParser implements XmlContentParser {

    /** This implements the XmlContentParser parse method, which parses a mix
     *  element.
     * @throws XmlContentException 
     */
	@Override
	public XmlContent parse(XMLStreamReader reader) throws XMLStreamException, XmlContentException {
		return new Mix(reader);
	}

	/*
	 * I want something that runs on dispatches, so that the amount of code is small. A state
	 * machine might be a good approach. If it's something which can be applied to a recursive
	 * schema like MODS, so much the better.
	 * 
	 * So what exactly would this be? There should be a state number, which might be a base N
	 * number for a sufficiently large N. (64 might be enough.) The state specifies the stacked
	 * elements; most state numbers are meaningless.
	 * 
	 * The Mix class will be the one which FITS knows about; everything else is negotiation.
	 * But the pattern set up so far is to have a hierarchy of objects. These need to be known
	 * to the outside world, and thus have to be defined with sufficient awareness of what that
	 * outside world (mostly FITS) is going to expect, or what we're telling it to expect.
	 * 
	 * Getters and setters probably have to be tediously hand-coded, but something can be done
	 * about the parser (unmarshal) to reduce it to a number of standard cases. Define a set
	 * of tables which give an element name, a variable, and a type.  E.g.,
	 * 
	 * "yCbCrSubsampleHoriz", yCbCrSubSampleHoriz, TYPE_INTEGER
	 * 
	 * In some cases, the type to invoke is an application-defined class. In that case,
	 * a fourth element is needed, so it would look something like
	 * 
	 * "GPSData", gpsData, TYPE_APPLICATION, "GPSData"
	 * 
	 * where gpsData is a variable, and GPSData is the name of the class.
	 * 
	 */
}
