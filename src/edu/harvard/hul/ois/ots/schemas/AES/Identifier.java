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

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


/** Elements represented by this class may have different names,
 *  so the name must be given to the constructor. */
public class Identifier extends StringElement {

    /* Permitted values for attribute identifierType */
    private final static String[] VALUES_IDTYPE = { "UMID",
            "FILE_NAME", "SHELF_NUMBER", "OTHER"};

    /**  Constructor for this element
     */
    public Identifier (XMLStreamReader reader, String name) 
           throws XMLStreamException, XmlContentException {
        super (reader, name);
        parse (reader);
    }
    
    /** Constructor with just reader */
    public Identifier (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        super(reader);
        // Superclass calls parse
    }

    /** Constructor with content. */
    public Identifier (String content, String name) {
        super(content, name);
    }
    
    public String getIdentifierType () {
        return getAttribute ("identifierType");
    }
    
    public void setIdentifierType (String t) throws XmlContentException {
        boolean ok = false;
        for (String s : VALUES_IDTYPE) {
            if (s.equals(t)) {
                ok = true;
                break;
            }
        }
        if (ok) {
            setAttribute("identifierType", t);
        }
        else {
            throw new XmlContentException ("Invalid speedUnit attribute: " + t);
        }
    }


    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

}
