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

/** The titleInfo MODS element, which is one of the top-level elements
 *  of a modsGroup, and is also found as a subelement of subject. */
public class TitleInfo extends LangGroupElement {

    private final static String ELEM_TITLE = "title";
    private final static String ELEM_SUBTITLE = "subTitle";
    private final static String ELEM_PARTNO = "partNumber";
    private final static String ELEM_PARTNAME = "partName";
    private final static String ELEM_NONSORT = "nonSort";
    private final static String ATT_TYPE = "type";

    /** Constructor from XML. */
    public TitleInfo(XMLStreamReader reader) 
            throws XMLStreamException, XmlContentException {
        name = "titleInfo";
        initAllElements();
        parse(reader);
    }
    
    /** No-argument constructor. This can be used when the caller is
     *  composing a Mix element from its constituent classes. */
    public TitleInfo () {
        super();
        name = "titleInfo";
    }


    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    /** Get the Titles */
    @SuppressWarnings("unchecked")
	public List<StringElement> getTitles () {
        return (genericList(getList (ELEM_TITLE)));
    }
    
    /** Add a Title 
     * @throws XmlContentException
     * @return The created StringElement */
    public StringElement addTitle (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_TITLE);
    	addToList (se, ELEM_TITLE);
    	return se;
    }

    /** Get the subTitles */
    @SuppressWarnings("unchecked")
	public List<StringElement> getSubTitles () {
        return (genericList(getList (ELEM_SUBTITLE)));
    }
    
    /** Add a subTitle 
     * @throws XmlContentException */
    public StringElement addSubTitle (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_SUBTITLE);
    	addToList (se, ELEM_SUBTITLE);
    	return se;
    }

    /** Get the PartNos */
    @SuppressWarnings("unchecked")
	public List<StringElement> getPartNos () {
        return (List<StringElement>)
             genericList(getList (ELEM_PARTNO));
    }
    
    /** Add a PartNo 
     * @throws XmlContentException */
    public StringElement addPartNo (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_PARTNO);
    	addToList (se, ELEM_PARTNO);
    	return se;
    }

    /** Get the PartNames */
    @SuppressWarnings("unchecked")
	public List<StringElement> getPartNames () {
        return (List<StringElement>)
             genericList(getList (ELEM_PARTNAME));
    }
    
    /** Add a PartName
     * @throws XmlContentException */
    public StringElement addPartName (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_PARTNAME);
    	addToList (se, ELEM_PARTNAME);
    	return se;
    }

    /** Get the NonSorts */
    @SuppressWarnings("unchecked")
	public List<StringElement> getNonSorts () {
        return (List<StringElement>)
             genericList(getList (ELEM_NONSORT));
    }
    
    /** Add a NonSort 
     * @throws XmlContentException */
    public StringElement addNonSort (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_NONSORT);
        addToList (se, ELEM_NONSORT);
        return se;
    }
    
    /** Returns the type attribute */
    public String getType () {
        return getAttribute (ATT_TYPE);
    }
    
    /** Sets the type attribute */
    public void setType (String typ) {
        setAttribute (ATT_TYPE, typ);
    }


    /** Classes must implement this to handle START_ELEMENT cases.  */
    protected void dispatch (String localName, XMLStreamReader reader) 
                  throws XMLStreamException, XmlContentException {
        if (matchList (reader, this, 
            localName, ELEM_TITLE,
            StringElement.class))
        return;
        if (matchList (reader, this, 
            localName, ELEM_SUBTITLE,
            StringElement.class))
        return;
        if (matchList (reader, this, 
            localName, ELEM_PARTNO,
            StringElement.class))
        return;
        if (matchList (reader, this, 
            localName, ELEM_PARTNAME,
            StringElement.class))
        return;
        if (matchList (reader, this, 
            localName, ELEM_NONSORT,
            StringElement.class))
        return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
