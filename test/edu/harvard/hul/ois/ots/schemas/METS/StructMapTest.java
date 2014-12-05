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


public class StructMapTest extends XMLTestCase {
    /** This example is taken from the LOC site */
    private String xmlSample = 
        "<mets:structMap xmlns:mets=\"http://www.loc.gov/METS/\"> \n" +
        "<mets:div TYPE=\"text\" LABEL=\"Mary Refugio Carpenter diary - 1861\" ADMID=\"RMD1\" DMDID=\"DMR1 DM1\"> \n" +
        "  <mets:div TYPE=\"cover\" LABEL=\"cover\" DMDID=\"DM2\"> \n" +
        "   <mets:fptr FILEID=\"FID1\"/> \n" +
        "   <mets:fptr FILEID=\"FID187\"/> \n" +
        "   <mets:fptr FILEID=\"FID373\"/> \n" +
        "   <mets:fptr FILEID=\"FID559\"/> \n" +
        "  </mets:div> \n" +
        "  <mets:div TYPE=\"cover\" LABEL=\"title page\" DMDID=\"DM3\"> \n" +
        "   <mets:fptr FILEID=\"FID2\"/> \n" +
        "   <mets:fptr FILEID=\"FID188\"/> \n" +
        "   <mets:fptr FILEID=\"FID374\"/> \n" +
        "   </mets:div> \n" +
        "  </mets:div> \n" +
        "</mets:structMap>";

    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        System.out.println (xmlSample);
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(xmlSample));
        //move to root tag
        xmlr.nextTag();
        StructMap smap = new StructMap (xmlr);
        // This structure has a div with two nested divs
        Div topdiv = smap.getDiv();
        assertNotNull (topdiv);
        //assertTrue (topdiv.size() == 1);
        //List<Div> childDivs = topdiv.getDivs().get(0);
        assertTrue ("text".equals (topdiv.getType ()));
        List<String> dmdIds = topdiv.getDmdids();
        assertNotNull (dmdIds);
        assertTrue (dmdIds.size() == 2);
        assertTrue ("DMR1".equals (dmdIds.get(0)));
        assertTrue ("DM1".equals (dmdIds.get(1)));
        List<Div> divs = topdiv.getDivs();
        assertTrue (divs.size() == 2);
        Div div0 = divs.get(0);
        assertTrue ("cover".equals (div0.getType()));
        assertTrue ("cover".equals (div0.getLabel()));
        List<Fptr> fptrs = div0.getFptrs();
        assertNotNull (fptrs);
        assertTrue (fptrs.size() == 4);
        Fptr fptr = fptrs.get(3);
        assertNotNull (fptr);
        assertTrue ("FID559".equals (fptr.getFileID()));
        
        Div div1 = divs.get(1);
        assertTrue ("cover".equals (div1.getType()));
        assertTrue ("title page".equals (div1.getLabel()));
        fptrs = div1.getFptrs();
        assertNotNull (fptrs);
        assertTrue (fptrs.size() == 3);
        fptr = fptrs.get(0);
        assertNotNull (fptr);
        assertTrue ("FID2".equals (fptr.getFileID()));
        
    }
}
