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


public class PhysPropsTest extends junit.framework.TestCase {

    private final static String sample =
        "<physicalProperties>\n" +
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
        "</physicalProperties>\n";


    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(sample));
        xmlr.nextTag();
        PhysProps p  = new PhysProps (xmlr);
        OpticalStructure os = p.getOpticalStructure ();
        assertNotNull (os);
        Dimensions d = p.getDimensions ();
        assertNotNull (d);
        ShellDimensions sd = p.getShellDimensions();
        assertNotNull (sd);
    }
}