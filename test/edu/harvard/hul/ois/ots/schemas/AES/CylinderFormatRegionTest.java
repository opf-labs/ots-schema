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

import java.io.StringReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;


public class CylinderFormatRegionTest extends junit.framework.TestCase {

    
    /** Sample string for testing */
    private final static String cylinderSample =
        "<formatRegion " +
        "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
        "    xsi:type=\"cylinderFormatRegionType\">\n" +
        " <physicalProperties/> \n" +
        " <speed>" +
        "   <speedDesignated unit=\"Inches per second\">15</speedDesignated>\n" +
        " </speed> \n" +
        " <grooveOrientation>VERTICAL</grooveOrientation>\n" +
        " <grooveWidth>\n" +
        "   <min unit=\"FEET\">40000</min>\n" +
        "   <max unit=\"MILLIMETRES\">2</max>\n" +
        " </grooveWidth>\n" +
        " <grooveCreationMethod>PRESS_MOULDED</grooveCreationMethod>\n" +
        " <soundField>abcdef</soundField>\n" +
        "</formatRegion>";

    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(cylinderSample));
        xmlr.nextTag();
        FormatRegion fr = new FormatRegion (xmlr);
        FormatRegionSubtype content = fr.getContent ();
        assertNotNull (content);
        assertTrue (content instanceof CylinderFormatRegion);
        CylinderFormatRegion a = (CylinderFormatRegion) content;
        assertNotNull (a.getPhysicalProperties());
        assertNotNull (a.getSpeed ());
        assertEquals ("VERTICAL", a.getGrooveOrientation().toString());
        GrooveWidth gw = a.getGrooveWidth();
        assertNotNull (gw.getMin());
        assertNotNull (gw.getMax());
        assertEquals ("PRESS_MOULDED", a.getGrooveCreationMethod ().toString());
        assertEquals ("abcdef", a.getSoundField ().toString());
    }

}
