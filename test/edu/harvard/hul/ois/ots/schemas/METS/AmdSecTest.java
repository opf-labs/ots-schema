/* 
 * Copyright 2010 Harvard University Library
 * 
 * This file is part of OTS-Schemas.
 * 
 * OTS-Schemas is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * OTS-Schemas is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with OTS-Schemas.  If not, see <http://www.gnu.org/licenses/>.
 */

package edu.harvard.hul.ois.ots.schemas.METS;

import java.io.StringReader;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.custommonkey.xmlunit.*;

/** This tests AmdSec, and also does some testing of TechMD.
 */
public class AmdSecTest extends XMLTestCase {

    /** This example is taken from the LOC site and vastly reduced */
    private String xmlSample = 
       "<mets:amdSec xmlns:mets=\"http://www.loc.gov/METS/\" \n" + 
       "xmlns:rts=\"http://cosimo.stanford.edu/sdr/metsrights/\" \n" +
       "xmlns:mix=\"http://www.loc.gov/mix/v20\"> \n" +
       "<mets:techMD ID=\"ADM292\"> \n" +
       "<mets:mdWrap MDTYPE=\"NISOIMG\"> \n" +
       " <mets:xmlData> \n" +
       "  <mix:mix> \n" +
       "   <mix:BasicDigitalObjectInformation> \n" +
       "     <mix:ObjectIdentifier> \n" +
       "       <mix:objectIdentifierType>ark</mix:objectIdentifierType> \n" +
       "       <mix:objectIdentifierValue>ark:/28722/bk0001c5667</mix:objectIdentifierValue> \n" +
       "     </mix:ObjectIdentifier> \n" +
       "   </mix:BasicDigitalObjectInformation> \n" +
       "  </mix:mix> \n" +
       " </mets:xmlData> \n" +
       "</mets:mdWrap> \n" +
       "</mets:techMD> \n" +
       "</mets:amdSec>\n";
       

    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        System.out.println (xmlSample);
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(xmlSample));
        //move to root tag
        xmlr.nextTag();
        AmdSec asec = new AmdSec (xmlr);       // this constructor automatically unmarshalls
        assertNull (asec.getID());
        TechMD techMd = asec.getTechmd();
        assertNotNull (techMd);
        assertTrue ("ADM292".equals (techMd.getID()));
        MdWrap wrap = techMd.getMdwrap();
        assertNotNull (wrap);
        assertTrue ("NISOIMG".equals (wrap.getMdType()));
        assertNull (techMd.getMdref());
        List<DigiprovMD> digiprov = asec.getDigiprovMD();
        assertNotNull (digiprov);
        assertTrue (digiprov.size() == 0);
    }

}
