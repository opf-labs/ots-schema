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


public class PacketListTest extends junit.framework.TestCase {

    private String sample = "<packetList>\n" +
        "  <packet fileOffset=\"0\" packetLength=\"1024\"/>\n" + 
        "  <packet fileOffset=\"1024\" packetLength=\"1024\"/>\n" + 
        "  <packet fileOffset=\"2048\" packetLength=\"1024\"/>\n" + 
        "</packetList>\n";
    
    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(sample));
        xmlr.nextTag();
        PacketList pl = new PacketList (xmlr);
        List<Packet> packets = pl.getPackets();
        assertTrue (packets.size () == 3);
        Packet pkt = packets.get (2);
        assertEquals ((Integer) 2048, pkt.getFileOffset());
        assertEquals ((Integer) 1024, pkt.getPacketLength());
    }
}
