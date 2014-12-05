/* 
 * Copyright 2010 Harvard University Library
 * 
 * This file is part of OTS-Schemas.
 * 
 * OTS-Schemas is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * OTS-Schemas is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with OTS-Schemas.  If not, see <http://www.gnu.org/licenses/>.
 */

package edu.harvard.hul.ois.ots.schemas.ModsMD;

import java.util.List;

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class PhysicalDescription extends LangGroupElement {

    private final static String ELEM_FORM = "form";
    private final static String ELEM_REFQUALITY = "reformattingQuality";
    private final static String ELEM_INTMEDIATYPE = "internetMediaType";
    private final static String ELEM_EXTENT = "extent";
    private final static String ELEM_DIGORIGIN = "digitalOrigin";
    private final static String ELEM_NOTE = "note";

    /**  Constructor for this element
     */
    public PhysicalDescription (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "physicalDescription";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public PhysicalDescription () {
        super();
        name = "physicalDescription";
        initAllElements();
        defineRestrictions();
    }
    
    /** Define restrictions */
    private void defineRestrictions () {
    }
    
    /** Get the forms */
    @SuppressWarnings("unchecked")
	public List<StringElement> getForms () {
        return (genericList(getList (ELEM_FORM)));
    }
    
    /** Add a Form 
     * @throws XmlContentException */
    public StringElement addForm (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_FORM);
        addToList (se, ELEM_FORM);
        return se;
    }
    
    /** Get the reformattingQualities */
    @SuppressWarnings("unchecked")
	public List<StringElement> getReformattingQualities () {
        return (genericList(getList (ELEM_REFQUALITY)));
    }
    
    /** Add a reformattingQuality 
     * @throws XmlContentException */
    public StringElement addReformattingQuality (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_REFQUALITY);
        addToList (se, ELEM_REFQUALITY);
        return se;
    }

    /** Get the internetMediaTypes */
    @SuppressWarnings("unchecked")
	public List<StringElement> getInternetMediaTypes () {
        return (genericList(getList (ELEM_INTMEDIATYPE)));
    }
    
    /** Add a internetMediaType 
     * @throws XmlContentException */
    public StringElement addInternetMediaType (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_INTMEDIATYPE);
        addToList (se, ELEM_INTMEDIATYPE);
        return se;
    }

    /** Get the extents */
    @SuppressWarnings("unchecked")
	public List<StringElement> getExtents () {
        return (genericList(getList (ELEM_EXTENT)));
    }
    
    /** Add an extent
     * @throws XmlContentException */
    public StringElement addExtent (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_EXTENT);
        addToList (se, ELEM_EXTENT);
        return se;
    }

    /** Get the digitalOrigins */
    @SuppressWarnings("unchecked")
	public List<StringElement> getDigitalOrigins () {
        return (genericList(getList (ELEM_DIGORIGIN)));
    }
    
    /** Add a digitalOrigin
     * @throws XmlContentException */
    public StringElement addDigitalOrigin (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_DIGORIGIN);
        addToList (se, ELEM_DIGORIGIN);
        return se;
    }

    /** Get the notes */
    @SuppressWarnings("unchecked")
	public List<StringElement> getNotes () {
        return (genericList(getList (ELEM_NOTE)));
    }
    
    /** Add a note
     * @throws XmlContentException */
    public StringElement addNote (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_NOTE);
        addToList (se, ELEM_NOTE);
        return se;
    }


    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    protected void dispatch (String localName, XMLStreamReader reader) 
         throws XMLStreamException, XmlContentException {
        if (matchList (reader, this, 
                localName, ELEM_FORM,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_REFQUALITY,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_INTMEDIATYPE,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_EXTENT,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_DIGORIGIN,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_NOTE,
                StringElement.class))
            return;
            
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
