/**
 * @author Gary McGath
 *
 * Copyright (c) 
 * 2010 by the President and Fellows of Harvard College
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
 */
package edu.harvard.hul.ois.ots.schemas.AES;

import java.io.StringReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

public class ChannelAssignmentTest extends junit.framework.TestCase {

    private final static String sample = 
        "<channelAssignment channelNum=\"7\" " +
        "      leftRightPosition=\"1\" " +
        "      frontRearPosition=\"0\">\n" +
        "</channelAssignment>\n";
    
    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(sample));
        xmlr.nextTag();
        ChannelAssignment c  = new ChannelAssignment (xmlr);
        assertEquals (1.0, c.getLeftRightPosition());
        assertEquals (0.0, c.getFrontRearPosition());
    }

    public void testComposition () throws Exception  {
        ChannelAssignment ca = new ChannelAssignment ();
        ca.setChannelNum (7);
        ca.setLeftRightPosition(24.0);
        ca.setFrontRearPosition(-1.0);
        
        assertEquals (new Integer(7), ca.getChannelNum());
        assertEquals (new Double(24.0), ca.getLeftRightPosition());
        assertEquals (new Double(-1.0), ca.getFrontRearPosition());
    }

}
