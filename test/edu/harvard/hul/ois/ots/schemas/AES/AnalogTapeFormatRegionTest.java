/**********************************************************************
 * Copyright (c) 2011 by the President and Fellows of Harvard College
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

public class AnalogTapeFormatRegionTest extends junit.framework.TestCase {

    /** Sample string for testing */
    private final static String discSample =
        "<formatRegion " +
        "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
        "    xsi:type=\"analogTapeFormatRegionType\"\n" +
        "    label=\"Label\" " +
        "    ownerRef=\"123\" >\n" +
        " <physicalProperties/> \n" +
        " <speed>" +
        "   <speedDesignated unit=\"Inches per second\">15</speedDesignated>\n" +
        " </speed> \n" +
        " <trackLayout>4-track</trackLayout>\n" +
        " <soundField>abcdef</soundField>\n" +
        " <noiseReduction>aceg</noiseReduction>\n" +
        " <equalization>xyz</equalization>\n" +
        "</formatRegion>";


    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(discSample));
        xmlr.nextTag();
        FormatRegion fr = new FormatRegion (xmlr);
        FormatRegionSubtype content = fr.getContent ();
        assertNotNull (content);
        assertTrue (content instanceof AnalogTapeFormatRegion);
        AnalogTapeFormatRegion a = (AnalogTapeFormatRegion) content;
        assertNotNull (a.getPhysicalProperties());
        assertNotNull (a.getSpeed ());
        assertEquals ("4-track", a.getTrackLayout().toString());
        assertEquals ("abcdef", a.getSoundField ().toString());
        assertEquals ("aceg", a.getNoiseReduction ().toString());
    }

}
