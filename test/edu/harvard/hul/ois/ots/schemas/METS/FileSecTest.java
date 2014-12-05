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
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.custommonkey.xmlunit.*;

public class FileSecTest extends XMLTestCase {
    /** This example is taken from the LOC site */
    private String xmlSample = "<mets:fileSec xmlns:mets=\"http://www.loc.gov/METS/\" " +
        "   xmlns:xlink=\"http://www.w3.org/TR/xlink\"> \n" +
        "<mets:fileGrp USE=\"MASTER\"> \n" +
        "<mets:file MIMETYPE=\"image/tiff\" GROUPID=\"G1\" ID=\"masterd1e102963\" " +
        "   ADMID=\"object1 agent1 event1  event2 event5\"> \n" +
        "<mets:FLocat LOCTYPE=\"URL\" xlink:href=\"http://lcweb2.loc.gov/natlib/ihas/warehouse/gottlieb/09601/ver01/0001.tif\"/> \n" +
        "</mets:file> \n" +
        "<mets:file MIMETYPE=\"image/tiff\" GROUPID=\"G1\" ID=\"masterd1e102965\" \n" +
        "   ADMID=\"object2 agent1 event3  event4 event6\"> \n" +
        "<mets:FLocat LOCTYPE=\"URL\" xlink:href=\"http://lcweb2.loc.gov/natlib/ihas/warehouse/gottlieb/09601/ver02/0001.tif\"/> \n" +
        "</mets:file> \n" +
        "</mets:fileGrp> \n" +
        "<mets:fileGrp USE=\"SERVICE\"> \n" +
        "<mets:file MIMETYPE=\"image/jpeg\" GROUPID=\"G1\" ID=\"serviced1e102963\"> \n" +
        "<mets:FLocat LOCTYPE=\"URL\" xlink:href=\"http://lcweb2.loc.gov/natlib/ihas/service/gottlieb/09601/ver01/0001v.jpg\"/> \n" +
        "</mets:file> \n" +
        "<mets:file MIMETYPE=\"image/jpeg\" GROUPID=\"G1\" ID=\"serviced1e102965\"> \n" +
        "<mets:FLocat LOCTYPE=\"URL\" xlink:href=\"http://lcweb2.loc.gov/natlib/ihas/service/gottlieb/09601/ver02/0001v.jpg\"/> \n" +
        "</mets:file> \n" +
        "</mets:fileGrp> \n" +
        "</mets:fileSec> ";
    
    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        System.out.println (xmlSample);
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(xmlSample));
        //move to root tag
        xmlr.nextTag();
        FileSec fsec = new FileSec (xmlr);       // this constructor automatically unmarshalls
        assertNull (fsec.getID());
        assertNotNull (fsec.getFilegrp());
    }
}
