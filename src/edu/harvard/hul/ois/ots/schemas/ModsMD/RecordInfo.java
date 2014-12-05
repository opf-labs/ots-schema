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


/** The recordInfo MODS element, which is one of the top-level elements
 *  of a modsGroup. */
public class RecordInfo extends LangGroupElement {

    private final static String ELEM_RECCONTENTSRC = "recordContentSource";
    private final static String ELEM_RECCREDATE = "recordCreationDate";
    private final static String ELEM_RECCHGDATE = "recordChangeDate";
    private final static String ELEM_RECID = "recordIdentifier";
    private final static String ELEM_LANG = "languageOfCataloging";
    private final static String ELEM_RECORG = "recordOrigin";
    private final static String ELEM_DESCSTD = "descriptionStandard";

    /**  Constructor for this element
     */
    public RecordInfo (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "recordInfo";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public RecordInfo () {
        super();
        name = "recordInfo";
        initAllElements();
        defineRestrictions();
    }
    
    /** Define restrictions */
    private void defineRestrictions () {
    }
    
    /** Get List of RecordContentSource objects, which are StringElements */
    @SuppressWarnings("unchecked")
	public List<StringElement> getRecordContentSources () {
        return (List<StringElement>)
        genericList(getList (ELEM_RECCONTENTSRC));
    }
    
    /** Add a RecordContentSource object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addRecordContentSource (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_RECCONTENTSRC);
        addToList(se, ELEM_RECCONTENTSRC);
        return se;
    }

    /** Get List of RecordCreationDate objects, which are StringElements */
    @SuppressWarnings("unchecked")
	public List<StringElement> getRecordCreationDates () {
        return (List<StringElement>)
        genericList(getList (ELEM_RECCREDATE));
    }
    
    /** Add a RecordCreationDate object. This is stored as a StringElement,
     * since MODS does not restrict date formats.
     *  
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addRecordCreationDate (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_RECCREDATE);
        addToList(se, ELEM_RECCREDATE);
        return se;
    }

    /** Get List of RecordChangeDate objects, which are StringElements */
    @SuppressWarnings("unchecked")
	public List<StringElement> getRecordChangeDates () {
        return (List<StringElement>)
        genericList(getList (ELEM_RECCHGDATE));
    }
    
    /** Add a RecordChangeDate object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addRecordChangeDate (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_RECCHGDATE);
        addToList(se, ELEM_RECCHGDATE);
        return se;
    }
    
    /** Get List of RecordIdentifier objects, which are StringElements */
    @SuppressWarnings("unchecked")
	public List<StringElement> getRecordIdentifiers () {
        return (List<StringElement>)
        genericList(getList (ELEM_RECID));
    }
    
    /** Add a RecordChangeDate object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addRecordIdentifier (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_RECID);
        addToList(se, ELEM_RECID);
        return se;
    }

    /** Get List of LanguageOfCataloging objects, which are StringElements */
    @SuppressWarnings("unchecked")
	public List<StringElement> getLanguagesOfCataloging () {
        return (List<StringElement>)
        genericList(getList (ELEM_LANG));
    }
    
    /** Add a LanguageOfCataloging object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addLanguageOfCataloging (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_LANG);
        addToList(se, ELEM_LANG);
        return se;
    }

    /** Get List of RecordOrigin objects, which are StringElements */
    @SuppressWarnings("unchecked")
	public List<StringElement> getRecordOrigins () {
        return (List<StringElement>)
        genericList(getList (ELEM_RECORG));
    }
    
    /** Add a RecordOrigin object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addRecordOrigin (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_RECORG);
        addToList(se, ELEM_RECORG);
        return se;
    }

    /** Get List of DescriptionStandard objects, which are StringElements */
    @SuppressWarnings("unchecked")
	public List<StringElement> getDescriptionStandards () {
        return (List<StringElement>)
        genericList(getList (ELEM_DESCSTD));
    }
    
    /** Add a DescriptionStandard object 
     * @throws XmlContentException
     * @return the StringElement which is created
     */
    public StringElement addDescriptionStandard (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_DESCSTD);
        addToList(se, ELEM_DESCSTD);
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
        if (matchList (reader, this, 
             localName, ELEM_RECCONTENTSRC,
             StringElement.class)) {
            return;
        }
        if (matchList (reader, this, 
                localName, ELEM_RECCREDATE,
                StringElement.class)) {
               return;
           }
        if (matchList (reader, this, 
                localName, ELEM_RECCHGDATE,
                StringElement.class)) {
               return;
           }
        if (matchList (reader, this, 
                localName, ELEM_RECID,
                StringElement.class)) {
               return;
           }
        if (matchList (reader, this, 
                localName, ELEM_LANG,
                StringElement.class)) {
               return;
           }
        if (matchList (reader, this, 
                localName, ELEM_RECORG,
                StringElement.class)) {
               return;
           }
        if (matchList (reader, this, 
                localName, ELEM_DESCSTD,
                StringElement.class)) {
               return;
           }
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
