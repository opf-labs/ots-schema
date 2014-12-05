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

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

/** The detail MODS element, which comes under part. */
public class Detail extends ModsElement {

    private final static String ELEM_NUMBER = "number";
    private final static String ELEM_CAPTION = "caption";
    private final static String ELEM_TITLE = "title";

    /**  Constructor for this element
     */
    public Detail (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "detail";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public Detail () {
        super();
        name = "detail";
        initAllElements();
        defineRestrictions();
    }
    
    /** Define restrictions */
    private void defineRestrictions () {
    }

    /** Returns the List of numbers. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getNumbers () {
        return (List<StringElement>) genericList (getList (ELEM_NUMBER));
    }


    /** Adds a number.
     *  @return  the created StringElement 
     */
    public StringElement addNumber (String name) throws XmlContentException {
        StringElement se = new StringElement (name, ELEM_NUMBER);
        addToList(se, ELEM_NUMBER);
        return se;
    }

    /** Returns the List of captions. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getCaptions () {
        return (List<StringElement>) genericList (getList (ELEM_CAPTION));
    }


    /** Adds a caption.
     *  @return  the created StringElement 
     */
    public StringElement addCaption (String name) throws XmlContentException {
        StringElement se = new StringElement (name, ELEM_CAPTION);
        addToList(se, ELEM_CAPTION);
        return se;
    }

    /** Returns the List of titles. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getTitles () {
        return (List<StringElement>) genericList (getList (ELEM_TITLE));
    }


    /** Adds a title.
     *  @return  the created StringElement 
     */
    public StringElement addTitle (String name) throws XmlContentException {
        StringElement se = new StringElement (name, ELEM_TITLE);
        addToList(se, ELEM_TITLE);
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
                localName, ELEM_NUMBER,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_CAPTION,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_TITLE,
                StringElement.class))
            return;
        
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
