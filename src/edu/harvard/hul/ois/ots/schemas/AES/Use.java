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

public class Use extends AESObject {

    private final static String ELEMENTNAME = "use";

    /* Permitted values for attribute useType */
    public final static String[] VALUES_USETYPE = { "ORIGINAL_MASTER",
            "PRESERVATION_MASTER", "PRODUCTION_MASTER",
            "SERVICE", "PREVIEW", "OTHER"};

    public Use (XMLStreamReader reader) 
            throws XMLStreamException, XmlContentException {
        this.name = ELEMENTNAME;
        parse (reader);
    }

    /** Constructor with no arguments. */
    public Use () {
        super();
        this.name = ELEMENTNAME;
    }


    public String getUseType () {
        return getAttribute ("useType");
    }
    
    public void setUseType (String t) throws XmlContentException {
        boolean ok = false;
        for (String s : VALUES_USETYPE) {
            if (s.equals(t)) {
                ok = true;
                break;
            }
        }
        if (ok) {
            setAttribute("useType", t);
        }
        else {
            throw new XmlContentException ("Invalid useType attribute: " + t);
        }
    }
    
    public String getOtherType () {
        return getAttribute ("otherType");
    }
    
    public void setOtherType (String t) {
        setAttribute ("otherType", t);
    }
    
    public void output(SMNamespace ns, SMOutputElement parent)
                throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        
        //output any attributes
        outputAttrs (mainElem);

        // There's no other content
    }
    
    

    @Override
    public boolean validate() {
        return true;
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    @Override
    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        //There are no elements, shouldn't get here
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
