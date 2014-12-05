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

public class MetsHdrTest extends XMLTestCase {
    /** This example is taken from the LOC site and vastly reduced */
    private String xmlSample = 
       "<mets:metsHdr  xmlns:mets=\"http://www.loc.gov/METS/\"\n" +
       "   CREATEDATE=\"2005-10-11T11:43:40\" LASTMODDATE=\"2008-06-11T06:40:23\"> \n" +
       "<mets:agent ROLE=\"CREATOR\" TYPE=\"ORGANIZATION\"> \n" +
       " <mets:name>Library Systems Office, University of California, Berkeley</mets:name> \n" +
       "</mets:agent> \n" +
       "</mets:metsHdr> \n";

    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        System.out.println (xmlSample);
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(xmlSample));
        //move to root tag
        xmlr.nextTag();
        MetsHdr hdr = new MetsHdr (xmlr);       // this constructor automatically unmarshalls
        String createDate = hdr.getCreateDate();
        assertTrue ("2005-10-11T11:43:40".equals (createDate));
        List<Agent> agents = hdr.getAgents ();
        assertNotNull (agents);
        assertTrue (agents.size() == 1);
    }

}
