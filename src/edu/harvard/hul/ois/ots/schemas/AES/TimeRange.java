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

public class TimeRange extends AESObject {

    private final static String ELEM_START = "startTime";
    private final static String ELEM_DURATION = "duration";
    
    /**  Constructor for this element
     */
    public TimeRange (XMLStreamReader reader, String name) 
           throws XMLStreamException, XmlContentException {
        this.name = name;
        parse (reader);
    }
    
    public TimeRange (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        parse (reader);
    }
    
    /** Constructor with name only. */
    public TimeRange (String name) {
        super();
        this.name = name;
    }
    
    public EditUnitNumber getStartTime () {
        return (EditUnitNumber) getField (ELEM_START);
    }
    
    public void setStartTime (EditUnitNumber t) throws XmlContentException {
        setField (ELEM_START, t);
    }

    public EditUnitNumber getDuration () {
        return (EditUnitNumber) getField (ELEM_DURATION);
    }
    
    public void setDuration (EditUnitNumber t) throws XmlContentException {
        setField (ELEM_DURATION, t);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputGenericElement (ns, ELEM_START, mainElem);
        outputGenericElement (ns, ELEM_DURATION, mainElem);
    }

    @Override
    public boolean validate() {
        return true;
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    @Override
    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        if (match(reader, this, localName, ELEM_START, EditUnitNumber.class))
            return;
        if (match(reader, this, localName, ELEM_DURATION, EditUnitNumber.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
