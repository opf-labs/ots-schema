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

/** The originInfo MODS element, which is one of the top-level elements
 *  of a modsGroup. */
public class OriginInfo extends LangGroupElement {

    private final static String ELEM_PLACE = "place";
    private final static String ELEM_PUBLISHER = "publisher";
    private final static String ELEM_DATEISSUED = "dateIssued";
    private final static String ELEM_DATECREATED = "dateCreated";
    private final static String ELEM_DATECAPTURED = "dateCaptured";
    private final static String ELEM_DATEVALID = "dateValid";
    private final static String ELEM_DATEMODIFIED = "dateModified";
    private final static String ELEM_COPYRIGHTDATE = "copyrightDate";
    private final static String ELEM_DATEOTHER = "dateOther";
    private final static String ELEM_EDITION = "edition";
    private final static String ELEM_ISSUANCE = "issuance";
    private final static String ELEM_FREQUENCY = "frequency";

    /* Permitted values for issuance */
    private final static String[] VALUES_ISSUANCE = 
    { "continuing", "monographic" };


    /** Constructor from XML. */
    public OriginInfo(XMLStreamReader reader) 
            throws XMLStreamException, XmlContentException {
        name = "originInfo";
        defineRestrictions ();
        parse(reader);
    }
    
    /** No-argument constructor. This can be used when the caller is
     *  composing a Mix element from its constituent classes. */
    public OriginInfo () {
        super();
        name = "originInfo";
        defineRestrictions ();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_ISSUANCE, VALUES_ISSUANCE);
    }

    /** Get the Places */
    @SuppressWarnings("unchecked")
	public List<Place> getPlaces () {
        return (genericList(getList (ELEM_PLACE)));
    }
    
    /** Add a Place 
     * @throws XmlContentException */
    public void addPlace (Place x) throws XmlContentException {
        addToList (x, ELEM_PLACE);
    }

    /** Get the publishers */
    @SuppressWarnings("unchecked")
    public List<StringElement> getPublishers () {
        return (genericList(getList (ELEM_PUBLISHER)));
    }
    
    /** Add a Publisher 
     * @throws XmlContentException */
    public StringElement addPublisher (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_PUBLISHER);
        addToList (se, ELEM_PUBLISHER);
        return se;
    }

    /** Get the dateIssueds. MODS dates are always StringElements. */
    @SuppressWarnings("unchecked")
    public List<StringElement> getDatesIssued () {
        return (genericList(getList (ELEM_DATEISSUED)));
    }
    
    /** Add a dateIssued
     * @throws XmlContentException */
    public StringElement addDateIssued (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_DATEISSUED);
        addToList (se, ELEM_DATEISSUED);
        return se;
    }

    /** Get the dateCreateds. MODS dates are always StringElements. */
    @SuppressWarnings("unchecked")
    public List<StringElement> getDatesCreated () {
        return (genericList(getList (ELEM_DATECREATED)));
    }
    
    /** Add a dateCreated
     * @throws XmlContentException */
    public StringElement addDateCreated (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_DATECREATED);
        addToList (se, ELEM_DATECREATED);
        return se;
    }

    /** Get the dateCaptureds. MODS dates are always StringElements. */
    @SuppressWarnings("unchecked")
    public List<StringElement> getDatesCaptured () {
        return (genericList(getList (ELEM_DATECAPTURED)));
    }
    
    /** Add a dateCaptured
     * @throws XmlContentException */
    public StringElement addDateCaptured (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_DATECAPTURED);
        addToList (se, ELEM_DATECAPTURED);
        return se;
    }

    /** Get the dateValids. MODS dates are always StringElements. */
    @SuppressWarnings("unchecked")
    public List<StringElement> getDatesValid () {
        return (genericList(getList (ELEM_DATEVALID)));
    }
    
    /** Add a dateValid
     * @throws XmlContentException */
    public StringElement addDateValid (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_DATEVALID);
        addToList (se, ELEM_DATEVALID);
        return se;
    }
    
    /** Get the dateModifieds. MODS dates are always StringElements. */
    @SuppressWarnings("unchecked")
    public List<StringElement> getDatesModified () {
        return (genericList(getList (ELEM_DATEMODIFIED)));
    }
    
    /** Add a dateModified
     * @throws XmlContentException */
    public StringElement addDateModified (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_DATEMODIFIED);
        addToList (se, ELEM_DATEMODIFIED);
        return se;
    }
    
    /** Get the copyrightDates. MODS dates are always StringElements. */
    @SuppressWarnings("unchecked")
    public List<StringElement> getCopyrightDates () {
        return (genericList(getList (ELEM_COPYRIGHTDATE)));
    }
    
    /** Add a CopyrightDate
     * @throws XmlContentException */
    public StringElement addCopyrightDate (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_COPYRIGHTDATE);
        addToList (se, ELEM_COPYRIGHTDATE);
        return se;
    }


    /** Get the dateOthers. MODS dates are always StringElements. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getDatesOther () {
        return (genericList(getList (ELEM_DATEOTHER)));
    }
    
    /** Add a dateOther
     * @throws XmlContentException */
    public StringElement addDateOther (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_DATEOTHER);
        addToList (se, ELEM_DATEOTHER);
        return se;
    }

    /** Get the editions. MODS dates are always StringElements. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getEditions () {
        return (genericList(getList (ELEM_EDITION)));
    }
    
    /** Add an edition
     * @throws XmlContentException */
    public StringElement addEdition (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_EDITION);
        addToList (se, ELEM_EDITION);
        return se;
    }

    /** Get the issuances. MODS dates are always StringElements. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getIssuances () {
        return (genericList(getList (ELEM_ISSUANCE)));
    }
    
    /** Add an issuance
     * @throws XmlContentException */
    public StringElement addIssuance (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_ISSUANCE);
        addToList (se, ELEM_ISSUANCE);
        return se;
    }

    /** Get the frequencies. MODS dates are always StringElements. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getFrequencies () {
        return (genericList(getList (ELEM_FREQUENCY)));
    }
    
    /** Add a frequency
     * @throws XmlContentException */
    public StringElement addFrequency (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_FREQUENCY);
        addToList (se, ELEM_FREQUENCY);
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
                localName, ELEM_PLACE,
                Place.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_PUBLISHER,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_DATEISSUED,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_DATECREATED,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_DATECAPTURED,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_DATEVALID,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_DATEMODIFIED,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_COPYRIGHTDATE,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_DATEOTHER,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_EDITION,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_ISSUANCE,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_FREQUENCY,
                StringElement.class))
            return;
            
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
