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

/** The part MODS element, which is one of the top-level elements
 *  of a modsGroup. */
public class Part extends ModsElement {

    private final static String ELEM_DETAIL = "detail";
    private final static String ELEM_EXTENT = "extent";
    private final static String ELEM_DATE = "date";
    private final static String ELEM_TEXT = "text";

    /**  Constructor for this element
     */
    public Part (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "part";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public Part () {
        super();
        name = "part";
        initAllElements();
        defineRestrictions();
    }
    
    /** Define restrictions */
    private void defineRestrictions () {
    }

    /** Get Detail objects */
    @SuppressWarnings("unchecked")
	public List<Detail> getDetails () {
        return (List<Detail>) genericList(getList (ELEM_DETAIL));
    }
    
    /** Set a Detail object 
     * @throws XmlContentException
     */
    public void addDetail (Detail x) throws XmlContentException {
        addToList (x, ELEM_DETAIL);
    }

    /** Get Extent objects */
    @SuppressWarnings("unchecked")
	public List<Extent> getExtents () {
        return (List<Extent>) genericList(getList (ELEM_EXTENT));
    }

    /** Add a Extent object 
     * @throws XmlContentException
     */
    public void addExtent (Extent x) throws XmlContentException {
        addToList(x, ELEM_EXTENT);
    }

    /** Returns the List of dates. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getDates () {
        return (List<StringElement>)   genericList(getList (ELEM_DATE));
    }

    /** Adds a date.
     *  @return  the created StringElement 
     */
    public StringElement addDate (String name) throws XmlContentException {
        StringElement se = new StringElement (name, ELEM_DATE);
        addToList(se, ELEM_DATE);
        return se;
    }

    /** Returns the List of texts. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getTexts () {
        return (List<StringElement>)   genericList(getList (ELEM_TEXT));
    }

    /** Adds a text.
     *  @return  the created StringElement 
     */
    public StringElement addText (String name) throws XmlContentException {
        StringElement se = new StringElement (name, ELEM_TEXT);
        addToList(se, ELEM_TEXT);
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
                 localName, ELEM_DETAIL,
                 Detail.class))
             return;
        if (matchList (reader, this, 
                localName, ELEM_EXTENT,
                Extent.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_DATE,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_TEXT,
                StringElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
