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

import edu.harvard.hul.ois.ots.schemas.XmlContent.DateElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class ConditionNote extends AESObject {

    private final static String ELEMENTNAME = "conditionNote";

    private final static String ELEM_NOTE = "note";
    private final static String ELEM_TIMERANGE = "timeRange";
    private final static String ELEM_CREATIONDATE = "creationDate";

    public ConditionNote (XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        this.name = ELEMENTNAME;
        parse (reader);
    }

    /**  Constructor for this element
     */
    public ConditionNote (XMLStreamReader reader, String name) 
           throws XMLStreamException, XmlContentException {
        this.name = name;
        parse (reader);
    }

    /** Constructor with no arguments. */
    public ConditionNote () {
        super();
        this.name = ELEMENTNAME;
    }
    
    public StringElement getNote () {
        return (StringElement) getField (ELEM_NOTE);
    }
    
    public void setNote(String note) throws XmlContentException {
        setField (ELEM_NOTE, note);
    }
    
    public TimeRange getTimeRange () {
        return (TimeRange) getField (ELEM_TIMERANGE);
    }
    
    public void setTimeRange (TimeRange tr) throws XmlContentException {
        setField (ELEM_TIMERANGE, tr);
    }
    
    public DateElement getCreationDate () {
        return (DateElement) getField (ELEM_CREATIONDATE);
    }
    
    public void setCreationDate (String s) throws XmlContentException {
        setField (ELEM_CREATIONDATE, new DateElement (s, "creationDate"));
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputGenericElement (ns, ELEM_NOTE, mainElem);
        outputGenericElement (ns, ELEM_TIMERANGE, mainElem);
        outputGenericElement (ns, ELEM_CREATIONDATE, mainElem);
    }

    @Override
    public boolean validate() {
        return true;
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    @Override
    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        if (matchList(reader, this, localName, ELEM_NOTE, StringElement.class))
            return;
        if (match(reader, this, localName, ELEM_TIMERANGE, TimeRange.class))
            return;
        if (matchList(reader, this, localName, ELEM_CREATIONDATE, DateElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
