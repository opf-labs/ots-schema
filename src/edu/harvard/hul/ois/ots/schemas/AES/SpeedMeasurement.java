/**********************************************************************
 * Copyright (c) 2010 by the President and Fellows of Harvard College
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 *
 * Contact information
 *
 * Office for Information Systems
 * Harvard University Library
 * Harvard University
 * Cambridge, MA  02138
 * (617)495-3724
 * hulois@hulmail.harvard.edu
 **********************************************************************/

package edu.harvard.hul.ois.ots.schemas.AES;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import edu.harvard.hul.ois.ots.schemas.XmlContent.DecimalElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class SpeedMeasurement extends DecimalElement {

    /* Permitted values for attribute unit */
    private final static String[] VALUES_UNIT = { "MILLIMETRES_PER_SECOND",
    "CENTIMETRES_PER_SECOND",
    "METRES_PER_SECOND" ,
    "REVOLUTIONS_PER_MINUTE" ,
    "PERCENT" ,
    "SEMI-TONES" ,
    "INCHES_PER_SECOND" ,
    "FEET_PER_SECOND"};

    /**  Constructor for this element
     */
    public SpeedMeasurement (XMLStreamReader reader, String name) 
           throws XMLStreamException, XmlContentException {
        super (reader, name);
        parse (reader);
    }

    public SpeedMeasurement (XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        super (reader);
    }
    
    /** Constructor with content. */
    public SpeedMeasurement (double content, String name) {
        super(content, name);
    }

    /** Return the unit attribute */
    public String getUnit() {
        return getAttribute("unit");
    }

    /** Set the unit attribute. Sets it only if the value is allowed. */
    public void setUnit(String t) throws XmlContentException {
        boolean ok = false;
        for (String s : VALUES_UNIT) {
            if (s.equals(t)) {
                ok = true;
                break;
            }
        }
        if (ok) {
            setAttribute("unit", t);
        }
        else {
            throw new XmlContentException ("Invalid speedUnit attribute: " + t);
        }
    }

}
