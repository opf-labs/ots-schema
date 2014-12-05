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


/** The location MODS element, which is one of the top-level elements
 *  of a modsGroup. */
public class Location extends ModsElement {

    private final static String ELEM_PHYSLOC = "physicalLocation";
    private final static String ELEM_SHELFLOC = "shelfLocator";
    private final static String ELEM_URL = "url";
    private final static String ELEM_HOLDINGSIMPLE = "holdingSimple";
    private final static String ELEM_HOLDINGEXT = "holdingExternal";

    /** Constructor from XML. */
    public Location(XMLStreamReader reader) 
            throws XMLStreamException, XmlContentException {
        name = "location";
        initAllElements();
        parse(reader);
    }
    
    /** No-argument constructor. This can be used when the caller is
     *  composing a Mix element from its constituent classes. */
    public Location () {
        super();
        name = "location";
    }


    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    /** Returns the  physicalLocations. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getPhysicalLocations () {
        return (List<StringElement>) genericList(getList (ELEM_PHYSLOC));
    }


    /** Adds a physicalLocation. Any number cal be added, but they all occur together
     * in the location element.
     *  @return  the created StringElement 
     */
    public StringElement addPhysicalLocation (String name) throws XmlContentException {
        StringElement se = new StringElement (name, ELEM_PHYSLOC);
        addToList(se, ELEM_PHYSLOC);
        return se;
    }

    /** Returns the List of shelfLocators. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getShelfLocators () {
        return (List<StringElement>)   genericList(getList (ELEM_PHYSLOC));
    }

    /** Adds a shelfLocator.
     *  @return  the created StringElement 
     */
    public StringElement addShelfLocator (String name) throws XmlContentException {
        StringElement se = new StringElement (name, ELEM_SHELFLOC);
        addToList(se, ELEM_SHELFLOC);
        return se;
    }

    /** Returns the List of URLS. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getURLs () {
        return (List<StringElement>)   genericList(getList (ELEM_URL));
    }

    /** Adds a URL.
     *  @return  the created StringElement 
     */
    public StringElement addURL (String name) throws XmlContentException {
        StringElement se = new StringElement (name, ELEM_URL);
        addToList(se, ELEM_URL);
        return se;
    }

    /** Returns the holdingSimple (non-repeatable) */
    public HoldingSimple getHoldingSimple () {
        return (HoldingSimple) getField (ELEM_HOLDINGSIMPLE);
    }
    
    /** Sets the holdingSimple 
     * @throws XmlContentException */
    public void setHoldingSimple (HoldingSimple h) throws XmlContentException {
        setField (ELEM_HOLDINGSIMPLE, h);
    }
    
    /** Returns the holdingExternal (non-repeatable) */
    public HoldingExternal getHoldingExternal () {
        return (HoldingExternal) getField (ELEM_HOLDINGEXT);
    }
    
    /** Sets the holdingExternal
     * @throws XmlContentException */
    public void setHoldingExternal (HoldingExternal h) throws XmlContentException {
        setField (ELEM_HOLDINGEXT, h);
    }

    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        if (matchList (reader, this, 
                localName, ELEM_PHYSLOC,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_SHELFLOC,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_URL,
                StringElement.class))
            return;
        if (match (reader, this, 
                localName, ELEM_HOLDINGSIMPLE,
                StringElement.class))
            return;
        if (match (reader, this, 
                localName, ELEM_HOLDINGEXT,
                StringElement.class))
            return;
        
        throw new XmlContentException ("Unknown element name " + localName);
    }
}
