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

public class Cartographics extends ModsElement {

    private final static String ELEM_SCALE = "scale";
    private final static String ELEM_PROJECTION = "projection";
    private final static String ELEM_COORDS = "coordinates";

    /**  Constructor for this element
     */
    public Cartographics (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "cartographics";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public Cartographics () {
        super();
        name = "cartographics";
        defineRestrictions();
    }
    
    /** Define restrictions */
    private void defineRestrictions () {
    }

    public StringElement getScale () {
        return (StringElement) getField(ELEM_SCALE);
    }
    
    public void setScale (String s) throws XmlContentException {
        setField (ELEM_SCALE, s);
    }

    public StringElement getProjection () {
        return (StringElement) getField(ELEM_PROJECTION);
    }
    
    public void setProjection (String s) throws XmlContentException {
        setField (ELEM_PROJECTION, s);
    }

    /** Get the coordinates (not coordinateses) */
    @SuppressWarnings("unchecked")
	public List<StringElement> getCoordinates () {
        return (genericList(getList (ELEM_COORDS)));
    }
    
    /** Add a coordinates (sic)
     * @throws XmlContentException */
    public StringElement addCoordinates (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_COORDS);
        addToList (se, ELEM_COORDS);
        return se;
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    protected void dispatch (String localName, XMLStreamReader reader) 
                  throws XMLStreamException, XmlContentException {
        if (match (reader, this, 
            localName, ELEM_SCALE,
            StringElement.class))
        return;
        if (match (reader, this, 
            localName, ELEM_PROJECTION,
            StringElement.class))
        return;
        if (matchList (reader, this, 
            localName, ELEM_COORDS,
            StringElement.class))
        return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
