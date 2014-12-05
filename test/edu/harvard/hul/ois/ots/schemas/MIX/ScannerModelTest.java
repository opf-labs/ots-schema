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

package edu.harvard.hul.ois.ots.schemas.MIX;

import edu.harvard.hul.ois.ots.schemas.MIX.ScannerModel;
import java.io.StringReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;


public class ScannerModelTest extends junit.framework.TestCase {

    private String xmlSample = "<mix:ScannerModel xmlns:mix=\"http://www.loc.gov/mix/v20\"> " +
           "<mix:scannerModelName>TOPAZ iX Scanner</mix:scannerModelName> " +
           "</mix:ScannerModel>";
    
    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(xmlSample));
        xmlr.nextTag();
        ScannerModel sm = new ScannerModel (xmlr);
        assertEquals ("TOPAZ iX Scanner", sm.getScannerModelName().toString());
    }
    
    
    
    public void testComposition () throws Exception  {
        ScannerModel sm = new ScannerModel ();
        sm.setScannerModelName("Xerox Superscan");
        sm.setScannerModelNumber("9001");
        sm.setScannerModelSerialNo("12345678");
        
        assertEquals ("9001", sm.getScannerModelNumber().toString());
        assertEquals ("12345678", sm.getScannerModelSerialNo().toString());
        assertEquals ("Xerox Superscan", sm.getScannerModelName().toString());
    }
}
