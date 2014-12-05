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


public class FormatRegionTest extends junit.framework.TestCase {

    /** Sample string for testing */
    private final static String analogTapeSample =
        "<formatRegion type=\"analogTapeFormatRegionType\">\n" +
        " <speed>" +
        "   <speedDesignated unit=\"Inches per second\">15</speedDesignated>\n" +
        " </speed> \n" +
        " <trackLayout>8 tracks</trackLayout>\n" +
        " <noiseReduction>lots</noiseReduction>\n" +
        " <equalization>yes</equalization>\n" +
        "</formatRegion>";

    public void testAnalogTapeRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(analogTapeSample));
        xmlr.nextTag();
        FormatRegion fr = new FormatRegion (xmlr);
        FormatRegionSubtype content = fr.getContent ();
        assertNotNull (content);
        assertTrue (content instanceof AnalogTapeFormatRegion);
        AnalogTapeFormatRegion a = (AnalogTapeFormatRegion) content;
        assertNotNull (a.getSpeed());
        assertNotNull (a.getTrackLayout());
        assertNotNull (a.getNoiseReduction());
        assertNotNull (a.getEqualization());
    }

}
