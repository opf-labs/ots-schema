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

/** The name MODS element, which is one of the top-level elements
 *  of a modsGroup, and is also found as a subelement of subject. */
public class Name extends LangGroupElement {

    private final static String ELEM_NAMEPART = "namePart";
    private final static String ELEM_DISPLAYFORM = "displayForm";
    private final static String ELEM_AFFILIATION = "affiliation";
    private final static String ELEM_ROLE = "role";
    private final static String ELEM_DESCRIPTION = "description";
    
    //private final static String ATT_ID = "xsd:ID";
    //private final static String ATT_TYPE = "type";
    //private final static String ATT_AUTHORITY = "authority";
    

    /**  Constructor for this element
     */
    public Name (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "name";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public Name () {
        super();
        name = "name";
        initAllElements();
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
    }
    
    /** Returns the List of nameParts. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getNameParts () {
        return (List<StringElement>)   genericList(getList (ELEM_NAMEPART));
    }


    /** Adds a namePart.
     *  @return  the created StringElement 
     */
    public StringElement addNamePart (String name) throws XmlContentException {
        StringElement se = new StringElement (name, ELEM_NAMEPART);
        addToList(se, ELEM_NAMEPART);
        return se;
    }

    /** Returns the List of displayForms. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getDisplayForms () {
        return (List<StringElement>)   genericList(getList (ELEM_DISPLAYFORM));
    }


    /** Adds a displayForm.
     *  @return  the created StringElement 
     */
    public StringElement addDisplayForm (String name) throws XmlContentException {
        StringElement se = new StringElement (name, ELEM_DISPLAYFORM);
        addToList(se, ELEM_DISPLAYFORM);
        return se;
    }

    /** Returns the List of affiliations. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getAffiliations () {
        return (List<StringElement>)   genericList(getList (ELEM_AFFILIATION));
    }


    /** Adds an affiliation.
     *  @return  the created StringElement 
     */
    public StringElement addAffiliation (String name) throws XmlContentException {
        StringElement se = new StringElement (name, ELEM_AFFILIATION);
        addToList(se, ELEM_AFFILIATION);
        return se;
    }

    /** Returns the List of roles. */
    @SuppressWarnings("unchecked")
	public List<Role> getRoles () {
        return (List<Role>)   genericList(getList (ELEM_ROLE));
    }


    /** Adds a role.
     */
    public Role addRole (Role x) throws XmlContentException {
        addToList(x, ELEM_ROLE);
        return x;
    }
    
    /** Returns the List of descriptions. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getDescriptions () {
        return (List<StringElement>)   genericList(getList (ELEM_DESCRIPTION));
    }


    /** Adds a description.
     *  @return  the created StringElement 
     */
    public StringElement addDescription (String name) throws XmlContentException {
        StringElement se = new StringElement (name, ELEM_DESCRIPTION);
        addToList(se, ELEM_DESCRIPTION);
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
                localName, ELEM_NAMEPART,
                StringElement.class))
           return;
        if (matchList (reader, this, 
                localName, ELEM_DISPLAYFORM,
                StringElement.class))
           return;
        if (matchList (reader, this, 
                localName, ELEM_AFFILIATION,
                StringElement.class))
           return;
        if (matchList (reader, this, 
                localName, ELEM_ROLE,
                Role.class))
           return;
        if (matchList (reader, this, 
                localName, ELEM_DESCRIPTION,
                StringElement.class))
           return;

        throw new XmlContentException ("Unknown element name " + localName);
    }
}
