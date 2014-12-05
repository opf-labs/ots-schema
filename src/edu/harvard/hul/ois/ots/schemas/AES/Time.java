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

/** This class was used earlier but isn't used now. Should be deleted
 *  from CVS soon unless they change their minds again. 
 *  
 */
@Deprecated
public class Time extends AESObject {

    private final static String ATT_ADDR = "address";
    private final static String ATT_FREQ = "frequency";
    
    public Time (XMLStreamReader reader) 
                   throws XMLStreamException, XmlContentException {
        parse (reader);
    }
    
    public Time (String name) {
        this.name = name;
    }

    /** The address attribute is defined as integer, but all attributes
     *  are treated as strings. */
    public String getAddress () {
        return getAttribute (ATT_ADDR);
    }
    
    /** Sets the address. The argument is a string but must parse
     *  to an integer. */
    public void setAddress (String a) throws XmlContentException {
        try {
            Integer.parseInt(a);
        }
        catch (Exception e) {
            throw new XmlContentException ("Attribute address must be an integer");
        }
        setAttribute (ATT_ADDR, a);
    }
    
    public String getFrequency () {
        return getAttribute (ATT_FREQ);
    }
    
    /** Sets the frequence. The argument is a string but must parse to
     *  an integer or double. */
    public void setFrequency (String f) throws XmlContentException {
        try {
            Double.parseDouble(f);
        }
        catch (Exception e) {
            throw new XmlContentException ("Attribute double must be a decimal number");
        }
        setAttribute (ATT_FREQ, f);
    }
    
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        // No elements
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

}
