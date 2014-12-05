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

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;


public class BitrateReductionTest extends junit.framework.TestCase {

    private final static String sample = 
        "<bitrateReduction>\n" +
        "  <codecName>MP3</codecName>\n" +
        "  <codecNameVersion>2.0</codecNameVersion>\n" +
        "  <codecCreatorApplication>Zowieware</codecCreatorApplication>\n" +
        "  <codecCreatorApplicationVersion>10.0</codecCreatorApplicationVersion>\n" +
        "  <dataRate>2 BPS</dataRate>\n" +
        "  <dataRateMode>FIXED</dataRateMode>\n" +
        "  <packetList/>\n" +
        "</bitrateReduction>\n";
    
    
    public void testRead () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(sample));
        xmlr.nextTag();
        BitrateReduction br  = new BitrateReduction (xmlr);
        assertEquals ("MP3", br.getCodecName().toString());
        assertEquals ("2.0", br.getCodecNameVersion().toString());
        assertEquals ("Zowieware", br.getCodecCreatorApplication().toString());
        assertEquals ("10.0", br.getCodecCreatorApplicationVersion().toString());
        assertEquals ("2 BPS", br.getDataRate().toString());
        assertEquals ("FIXED", br.getDataRateMode().toString());
        PacketList pl = br.getPacketList();
        List<Packet> packets = pl.getPackets();
        assertTrue (packets.size () == 0);
    }

    
    public void testComposition () throws Exception  {
        BitrateReduction br = new BitrateReduction ();
        br.setCodecName ("Squeeze");
        br.setCodecNameVersion ("4.2");
        br.setCodecCreatorApplication ("Miracle Audio");
        br.setCodecCreatorApplicationVersion ("98.6");
        boolean caughtEx = false;
        try {
            br.setCodecQuality ("SUCKY");
        }
        catch (XmlContentException e) {
            caughtEx = true;
        }
        assertTrue (caughtEx);
        br.setCodecQuality ("LOSSY");
        br.setDataRate ("5000 bps");
        br.setDataRateMode ("FIXED");
        PacketList p = new PacketList ();
        br.setPacketList (p);
        
        assertEquals ("Squeeze", br.getCodecName().toString());
        assertEquals ("4.2", br.getCodecNameVersion().toString());
        assertEquals ("Miracle Audio", br.getCodecCreatorApplication().toString());
        assertEquals ("98.6", br.getCodecCreatorApplicationVersion().toString());
        assertEquals ("LOSSY", br.getCodecQuality().toString());
        assertEquals ("5000 bps", br.getDataRate().toString());
        assertEquals ("FIXED", br.getDataRateMode().toString());
    }
}
