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

public class FaceTest extends junit.framework.TestCase {

    private final static String sample = 
        "<face   " +
        "  direction=\"FORWARD\" " +
        "  audioObjectRef=\"123\" " +
        "  label=\"LABEL\"> \n" +
        " <timeline> \n" +
        "    <startTime>50000</startTime>\n" +
        "    <duration>400</duration>\n" +
        " </timeline> \n" +
        " <region> \n" +
        "   <timeRange>" +
        "      <startTime>60000</startTime>\n" +
        "      <duration>300</duration>\n" +
        "   </timeRange>\n" +
        "   <numChannels>2</numChannels>\n" +
        "   <conditionNote>\n" +
        "     <note>abcdef</note>\n" +
        "     <creationDate>10-Dec-2010</creationDate>\n" +
        "   </conditionNote>\n" +
        "   <securityNote>xyz</securityNote>\n" +
        "   <stream label=\"aa\" faceRegionRef=\"3456\" >\n" +
        "     <channelAssignment channelNum=\"1\" " +
        "      leftRightPosition=\"10\" " +
        "      />\n" +
        "   </stream>\n" +
        " </region> \n" +
        " <region> \n" +
        "   <timeRange address=\"5\" frequency=\"100\" />\n" +
        "   <numChannels>3</numChannels>\n" +
        "   <conditionNote>\n" +
        "     <note>abcghi</note>\n" +
        "     <creationDate>12-Dec-2010</creationDate>\n" +
        "   </conditionNote>\n" +
        "   <securityNote>xyzzy</securityNote>\n" +
        "   <stream label=\"bb\" faceRegionRef=\"345678\" >\n" +
        "     <channelAssignment channelNum=\"2\" " +
        "      leftRightPosition=\"50\" " +
        "      />\n" +
        "   </stream>\n" +
        " </region> \n" +
        "</face>\n";
    
    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(sample));
        xmlr.nextTag();
        Face f  = new Face (xmlr);
        TimeRange tr = f.getTimeline();
        assertNotNull (tr);
        EditUnitNumber t = tr.getStartTime();
        assertNotNull (t);
        List<FaceRegion> rl = f.getRegions();
        assertEquals (rl.size(), 2);
        FaceRegion fr = rl.get(0);
        tr = fr.getTimeRange();
        assertNotNull(tr);
        assertEquals (fr.getNumChannels().toValue(), 2);
        List<ConditionNote> cns = fr.getConditionNotes();
        assertEquals (cns.size(), 1);
        List<Stream> strms = fr.getStreams();
        assertEquals (strms.size(), 1);
    }

}
