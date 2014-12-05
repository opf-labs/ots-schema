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

/** The place MODS element, which comes under originInfo. */
public class Place extends ModsElement {

    private final static String ELEM_PLACETERM = "placeTerm";

    /**  Constructor for this element
     */
    public Place (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "place";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public Place () {
        super();
        name = "place";
        initAllElements();
        defineRestrictions();
    }
    
    /** Define restrictions */
    private void defineRestrictions () {
    }
    
    /** Returns the List of PlaceTerms. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getPlaceTerms () {
        return (List<StringElement>) genericList(getList (ELEM_PLACETERM));
    }

    /** Adds to the List of PlaceTerms. 
     *  @return the StringElement which is created
     *  @throws XmlContentException */
    public StringElement addPlaceTerm (String s) throws XmlContentException {
        StringElement se = new StringElement (s, ELEM_PLACETERM);
        addToList(se, ELEM_PLACETERM);
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
                localName, ELEM_PLACETERM,
                StringElement.class))
            return;
        
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
