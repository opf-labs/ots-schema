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

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;

public class AnalogDiscDimensionsTest extends junit.framework.TestCase {

    /** Sample string for testing */
    private final static String sample =
        "<dimensions " +
        "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
        "    xsi:type=\"analogDiscDimensionsType\">\n" +
        " <shape>Moebius</shape> \n" +
        " <diameter unit=\"INCHES\">10</diameter> \n" +
        " <thickness unit=\"MILLIMETRES\">3</thickness> \n" +
        "</dimensions>";

    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(sample));
        xmlr.nextTag();
        Dimensions fr = new Dimensions (xmlr);
        DimensionsSubtype content = fr.getContent ();
        assertNotNull (content);
        assertTrue (content instanceof AnalogDiscDimensions);
        AnalogDiscDimensions a = (AnalogDiscDimensions) content;
        StringElement shape = a.getShape();
        assertEquals ("Moebius", shape.toString());
        Measurement m = a.getDiameter();
        assertEquals ("INCHES", m.getUnit());
        assertEquals ((Double) 10.0, m.toValue());
        m = a.getThickness();
        assertEquals ("MILLIMETRES", m.getUnit());
        assertEquals ((Double) 3.0, m.toValue());
    }

    public void testComposition () throws Exception  {
        Dimensions dim = new Dimensions (AnalogDiscDimensions.TYPENAME);
        AnalogDiscDimensions add = (AnalogDiscDimensions) dim.getContent();
        assertNotNull (add);
        add.setShape ("Hypercube");
        Measurement m = new Measurement (10, "shape");
        m.setUnit("INCHES");
        add.setDiameter(m);
        m = new Measurement (4, "diameter");
        m.setUnit("MILLIMETRES");
        add.setThickness (m);
        
        // Now read it back
        assertEquals ("Hypercube", add.getShape().toString());

        m = add.getThickness ();
        assertEquals ("MILLIMETRES", m.getUnit());
        assertEquals (4.0, m.toValue());
        m = add.getDiameter ();
        assertEquals ("INCHES", m.getUnit());
        assertEquals (10.0, m.toValue());
    }

}
