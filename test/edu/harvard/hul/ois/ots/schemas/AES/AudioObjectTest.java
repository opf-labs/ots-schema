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
import java.io.StringWriter;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.custommonkey.xmlunit.XMLTestCase;

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;


public class AudioObjectTest extends XMLTestCase {

    /* Sample for testing */
    private String audioObjectSample = 
        "<aes:audioObject xmlns:aes=\"http://www.aes.org/audioObject\" \n" +
        "   xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" >\n" + 
        "  <aes:format specificationVersion=\"1.0\">" +
        "MP3" +
        "</aes:format> \n" +
        "<aes:physicalProperties>" +
        "<aes:tapeStructure>" +
        "<aes:backcoatLayer>" +
        "<aes:thickness unit=\"MILS\">2" +
        "</aes:thickness>" +
        "</aes:backcoatLayer>\n" +
        "</aes:tapeStructure>" +
        "</aes:physicalProperties>\n" +
        "</aes:audioObject>";
    
    public void testAudioObject () throws Exception {
        // set up a parser
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(new StringReader(audioObjectSample));
        //move to root tag
        xmlr.nextTag();
        AudioObject ao = new AudioObject (xmlr);
        StringElement fmt = ao.getFormat();
        assertEquals ("MP3", fmt.toString());
    }
    
    public void testWrite () throws Exception {
        AudioObject ao = new AudioObject (true);
        ao.setAnalogDigitalFlag("PHYS_DIGITAL");
        ao.setTitle ("Beethoven Symphony No. 9");
        Format fmt = new Format ("xyz");
        ao.setFormat (fmt);
        PhysProps pp = new PhysProps ();
        ao.setPhysicalProperties(pp);
        AppSpecificData asd = new AppSpecificData("abcde");
        ao.addAppSpecificData(asd);
        ao.setAudioDataEncoding("123");
        ao.setByteOrder("LITTLE_ENDIAN");
        ao.setFirstSampleOffset(0);
        ao.setAudioDataBlockSize (2048);
        ao.setFirstValidByteOfBlock(0);
        ao.setLastValidByteOfBlock(178);
        Use u = new Use ();
        u.setUseType ("PRODUCTION_MASTER");
        ao.addUse (u);
        Identifier id = new Identifier ("42", "primaryIdentifier");
        id.setIdentifierType("OTHER");

        // Now write it out
        XMLOutputFactory f = XMLOutputFactory.newInstance();
        StringWriter stringWriter = new StringWriter();
        XMLStreamWriter sw = f.createXMLStreamWriter(stringWriter); 
        ao.output(sw);
        sw.close();
        String newXml = stringWriter.toString();
        stringWriter.close();
        System.out.println (newXml);

    }
}
