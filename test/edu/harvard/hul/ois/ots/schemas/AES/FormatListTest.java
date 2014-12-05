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

import edu.harvard.hul.ois.ots.schemas.AES.FormatRegion.regionTypeEnum;


public class FormatListTest extends junit.framework.TestCase {

    private final static String sample = 
        "<formatList>\n" +
        "  <formatRegion type=\"analogTapeFormatRegionType\">\n" +
        "    <physicalProperties>\n" +
            "  <opticalStructure>\n" +
            "    <substrateMaterialLayer role=\"SUPPORT_LAYER\"> " +
            "         order=\"1\">\n" +
            "      <thickness unit=\"MILS\">5</thickness>\n" +
            "    </substrateMaterialLayer>\n" +
            "    <binderLayer role=\"SUPPORT_LAYER\"> " +
            "         order=\"2\">\n" +
            "      <thickness unit=\"MILS\">5</thickness>\n" +
            "    </binderLayer>\n" +
            "    <dataLayer role=\"SUPPORT_LAYER\"> " +
            "         order=\"3\">\n" +
            "      <thickness unit=\"MILS\">5</thickness>\n" +
            "    </dataLayer>\n" +
            "    <reflectiveLayer role=\"SUPPORT_LAYER\"> " +
            "         order=\"1\">\n" +
            "      <thickness unit=\"MILS\">5</thickness>\n" +
            "    </reflectiveLayer>\n" +
            "  </opticalStructure>\n" +
            "  <dimensions type=\"opticalDiscDimensionsType\">\n" +
            "    <shape>toroidal</shape>\n" +
            "  </dimensions>\n" +
            "  <shellDimensions>\n" +
            "    <diameter unit=\"MILS\">100</diameter>\n" +
            "  </shellDimensions>\n" +
        "    </physicalProperties>\n" +
        "    <equalization>FLAT</equalization>\n" +
        "  </formatRegion>\n" +
        "  <formatRegion type=\"wireFormatRegionType\">\n" +
        "    <speed>\n" +
        "       <speedDesignated unit=\"Percent\">10</speedDesignated>\n" +
        "    </speed>\n" +
        "  </formatRegion>\n" +
        "</formatList>";
    
    
    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(sample));
        xmlr.nextTag();
        FormatList fl  = new FormatList (xmlr);
        List<FormatRegion> regions = fl.getFormatRegions();
        assertTrue (regions.size() == 2);
        FormatRegion rgn = regions.get(0);
        FormatRegionSubtype content = rgn.getContent ();
        assertTrue (content instanceof AnalogTapeFormatRegion);
        AnalogTapeFormatRegion argon = (AnalogTapeFormatRegion) content;
        PhysProps p = argon.getPhysicalProperties();
        OpticalStructure opts = p.getOpticalStructure();
        assertNotNull (opts);
        assertEquals ("FLAT", argon.getEqualization().toString());
        
        rgn = regions.get(1);
        content = rgn.getContent ();
        assertTrue(content instanceof WireFormatRegion);
        WireFormatRegion wagon = (WireFormatRegion) content;
        Speed sp = wagon.getSpeed();
        assertNotNull (sp);
        SpeedMeasurement sm = sp.getSpeedDesignated ();
        assertEquals ("Percent", sm.getUnit());
    }
    
    
    public void testComposition () throws Exception  {
        FormatList fl = new FormatList();
        //FormatRegion rgn = new FormatRegion ("analogTapeFormatRegionType");
        FormatRegion rgn = new FormatRegion (regionTypeEnum.ANALOG_TAPE);
        AnalogTapeFormatRegion content = 
             (AnalogTapeFormatRegion) rgn.getContent ();
        assertNotNull (content);
        PhysProps pp = new PhysProps ();
        content.setPhysicalProperties (pp);
        TapeStructure ts = new TapeStructure ();
        pp.setTapeStructure (ts);
        Dimensions dims = new Dimensions ("tapeDimensionsType");
        TapeDimensions dimContent = (TapeDimensions) dims.getContent ();
        assertNotNull (dimContent);
        Measurement m = new Measurement (1.0, "gauge");
        m.setUnit ("MILS");
        dimContent.setWidth(m);
        pp.setDimensions(dims);
        fl.addFormatRegion (rgn);
        
        // Now read it back
        List<FormatRegion> rgns = fl.getFormatRegions();
        assertTrue (rgns.size() == 1);
        rgn =  rgns.get(0);
        content = (AnalogTapeFormatRegion) rgn.getContent ();
        pp = content.getPhysicalProperties();
        assertNotNull (pp);
        ts = pp.getTapeStructure ();
        assertNotNull (ts);
        dims = pp.getDimensions();
        dimContent = (TapeDimensions) dims.getContent ();
        m = dimContent.getWidth ();
        assertEquals ("MILS", m.getUnit());
    }
}
