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

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class Packet extends AESObject {

    private final static String ELEMENTNAME = "packet";
    
    private final static String ATT_FILEOFF = "fileOffset";
    private final static String ATT_LENGTH = "packetLength";
    
    public Packet (XMLStreamReader reader) 
                 throws XMLStreamException, XmlContentException {
        this.name = ELEMENTNAME;
        parse (reader);
    }
    
    public Packet () {
        this.name = ELEMENTNAME;
    }
    
    public Integer getFileOffset () {
        try {
            return Integer.parseInt (getAttribute(ATT_FILEOFF));
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public void setFileOffset (int i) {
        setAttribute (ATT_FILEOFF, Integer.toString(i));
    }
    
    public Integer getPacketLength () {
        try {
            return Integer.parseInt (getAttribute (ATT_LENGTH));
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public void setPacketLength (int i) {
        setAttribute (ATT_LENGTH, Integer.toString (i));
    }
    
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputAttrs (mainElem);
        // No elements to output
    }

    @Override
    public boolean validate() {
        return true;
    }

}
