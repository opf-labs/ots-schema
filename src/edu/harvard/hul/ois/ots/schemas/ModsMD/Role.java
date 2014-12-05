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

public class Role extends ModsElement {

    private final static String ELEM_ROLETERM = "roleTerm";

    /**  Constructor for this element
     */
    public Role (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "role";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public Role () {
        super();
        name = "role";
        initAllElements();
        defineRestrictions();
    }
    
    void defineRestrictions () {
    }

    /** Returns the List of roleTerms. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getRoleTerms () {
        return (List<StringElement>)   genericList(getList (ELEM_ROLETERM));
    }

    /** Adds a roleTerm.
     *  @return  the created StringElement 
     */
    public StringElement addRoleTerm (String name) throws XmlContentException {
        StringElement se = new StringElement (name, ELEM_ROLETERM);
        addToList(se, ELEM_ROLETERM);
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
             localName, ELEM_ROLETERM,
             StringElement.class))
            return;

        throw new XmlContentException ("Unknown element name " + localName);
    }

}
