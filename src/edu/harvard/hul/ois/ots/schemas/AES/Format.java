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

/** The format element is a component of the audioObject element */
public class Format extends StringElement {

    private final static String ELEMENTNAME = "format";
    
    /**  Constructor for this element
     */
    public Format (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        super (reader, ELEMENTNAME);
    }
    
    /** Constructor with content. */
    public Format (String content) {
        super(content, ELEMENTNAME);
    }
    
    public void setSpecificationVersion (String t) {
        setAttribute("specificationVersion", t);
    }
    
    public String getSpecificationVersion () {
        return getAttribute("specificationVersion");
    }

    // output doesn't need overriding

    @Override
    public boolean validate() {
        return true;
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    @Override
    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        // Format has no elements, hence can never get here with good XML
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
