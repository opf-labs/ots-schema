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

public class SpeedTest extends junit.framework.TestCase {

    /** Sample string for testing */
    private final static String speedSample =
        "<speed>\n" +
        " <speedDesignated unit=\"Inches per second\">10</speedDesignated> \n" +
        " <varispeedAdjustment unit=\"Meters per second\">700</varispeedAdjustment>\n" +
        " <speedNote>abcdef</speedNote>\n" +
        "</speed>";

    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = 
            xmlif.createXMLStreamReader(new StringReader(speedSample));
        xmlr.nextTag();
        Speed spd = new Speed (xmlr);
        SpeedMeasurement spm = spd.getSpeedDesignated();
        assertEquals ("Inches per second", spm.getUnit());
        Double d = (Double) spm.toValue ();
        assertEquals ((Double) 10.0, d);
        spm = spd.getVariSpeedAdjustment();
        assertEquals ("Meters per second", spm.getUnit ());
        d = (Double) spm.toValue ();
        assertEquals ((Double) 700.0, d);
        assertEquals ("abcdef", spd.getSpeedNote().toString());
    }

}
