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
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;


public class OpticalStructureTest extends junit.framework.TestCase {

    /** Sample string for testing */
    private final static String sample =
        "<opticalStructure> " +
        " <substrateMaterialLayer> \n" +
        "  <thickness unit=\"FEET\">200</thickness>\n" +
        " </substrateMaterialLayer> \n" +
        " <binderLayer> \n" +
        "  <thickness unit=\"FEET\">900</thickness>\n" +
        " </binderLayer> \n" +
        " <dataLayer> \n" +
        "  <thickness unit=\"FEET\">900</thickness>\n" +
        " </dataLayer> \n" +
        " <reflectiveLayer>\n" +
        "  <thickness unit=\"FEET\">900</thickness>\n" +
        " </reflectiveLayer>\n" +
        " <protectiveLayer>\n" +
        "  <thickness unit=\"FEET\">900</thickness>\n" +
        " </protectiveLayer>\n" +
        " <protectiveLayer>\n" +
        "  <thickness unit=\"FEET\">900</thickness>\n" +
        " </protectiveLayer>\n" +
        "</opticalStructure>";

    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(sample));
        xmlr.nextTag();
        OpticalStructure os = new OpticalStructure (xmlr);
        List<Layer> lys = os.getSubstrateMaterialLayers();
        assertEquals (lys.size(), 1);
        Layer ly = lys.get(0);
        Measurement m = ly.getThickness();
        assertEquals ("FEET", m.getUnit());
        assertEquals ((Double) 200.0, m.toValue());
        ly = os.getBinderLayer();
        assertNotNull (ly);
        ly = os.getDataLayer();
        assertNotNull (ly);
        lys = os.getReflectiveLayers();
        assertEquals (lys.size(), 1);
        lys = os.getProtectiveLayers ();
        assertEquals (lys.size(), 2);
    }

    public void testComposition () throws Exception {
        OpticalStructure os = new OpticalStructure ();
        os.addSubstrateMaterialLayer (new Layer ("substrateMaterialLayer"));
        os.setBinderLayer (new Layer ("binderLayer"));
        os.setDataLayer (new Layer ("dataLayer"));
        os.addReflectiveLayer (new Layer ("reflectiveLayer"));
        os.addReflectiveLayer (new Layer ("reflectiveLayer"));
        os.addProtectiveLayer (new Layer ("protectiveLayer"));
        
        List<Layer> sml = os.getSubstrateMaterialLayers ();
        assertEquals (sml.size(), 1);
        Layer bl = os.getBinderLayer ();
        assertNotNull (bl);
        Layer dl = os.getDataLayer ();
        assertNotNull (dl);
        List<Layer> rl = os.getReflectiveLayers ();
        assertEquals (rl.size(), 2);
        List<Layer> pl = os.getProtectiveLayers();
        assertEquals (pl.size(), 1);
    }
}
