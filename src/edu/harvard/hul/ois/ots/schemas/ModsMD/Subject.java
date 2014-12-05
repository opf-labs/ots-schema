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

/** The subject MODS element, which is one of the top-level elements
 *  of a modsGroup. */
public class Subject extends LangGroupElement {

    private final static String ELEM_TOPIC = "topic";
    private final static String ELEM_GEOGRAPHIC = "geographic";
    private final static String ELEM_TEMPORAL = "temporal";
    private final static String ELEM_TITLEINFO = "titleInfo";
    private final static String ELEM_NAME = "name";
    private final static String ELEM_GEOCODE = "geographicCode";
    private final static String ELEM_HIERGEOGRAPHIC = "hierarchicalGeographic";
    private final static String ELEM_CARTOGRAPHICS = "cartographics";
    private final static String ELEM_OCCUPATION = "occupation";
    private final static String ELEM_GENRE = "genre";

    /**  Constructor for this element
     */
    public Subject (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "subject";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public Subject () {
        super();
        name = "subject";
        defineRestrictions();
    }
    
    /** Define restrictions */
    private void defineRestrictions () {
    }
    
    /** Returns the List of topics. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getTopics () {
        return (List<StringElement>) genericList (getList (ELEM_TOPIC));
    }

    /** Adds to the List of topics. 
     *  @return the StringElement which is created
     *  @throws XmlContentException */
    public StringElement addTopic (String s) throws XmlContentException {
        StringElement se = new StringElement (s, ELEM_TOPIC);
        addToList(se, ELEM_TOPIC);
        return se;
    }

    /** Returns the List of geographics. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getGeographics () {
        return (List<StringElement>) genericList (getList (ELEM_GEOGRAPHIC));
    }

    /** Adds to the List of geographics. 
     *  @return the StringElement which is created
     *  @throws XmlContentException */
    public StringElement addGeographic (String s) throws XmlContentException {
        StringElement se = new StringElement (s, ELEM_GEOGRAPHIC);
        addToList(se, ELEM_GEOGRAPHIC);
        return se;
    }

    /** Returns the List of temporals. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getTemporals () {
        return (List<StringElement>) genericList (getList (ELEM_TEMPORAL));
    }

    /** Adds to the List of temporas. 
     *  @return the StringElement which is created
     *  @throws XmlContentException */
    public StringElement addTemporal (String s) throws XmlContentException {
        StringElement se = new StringElement (s, ELEM_TEMPORAL);
        addToList(se, ELEM_TEMPORAL);
        return se;
    }
    
    /** Returns the List of TitleInfos. */
    @SuppressWarnings("unchecked")
	public List<TitleInfo> getTitleInfos () {
        return (List<TitleInfo>) genericList(getList (ELEM_TITLEINFO));
    }

    /** Adds to the List of titleInfos. 
     *  @throws XmlContentException */
    public void addTitleInfo (TitleInfo t) throws XmlContentException {
        addToList(t, ELEM_TITLEINFO);
    }

    /** Returns the List of Names. */
    @SuppressWarnings("unchecked")
	public List<Name> getNames () {
        return (List<Name>) genericList (getList (ELEM_NAME));
    }

    /** Adds to the List of names. 
     *  @throws XmlContentException */
    public void addName (Name n) throws XmlContentException {
        addToList(n, ELEM_NAME);
    }

    /** Returns the List of geographicCodes. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getGeographicCodes () {
        return (List<StringElement>) genericList(getList (ELEM_GEOCODE));
    }

    /** Adds to the List of geographicCodes. 
     *  @return the StringElement which is created
     *  @throws XmlContentException */
    public StringElement addGeographicCode (String s) throws XmlContentException {
        StringElement se = new StringElement (s, ELEM_GEOCODE);
        addToList(se, ELEM_GEOCODE);
        return se;
    }

    /** Returns the List of hierarchicalGeographics. */
    @SuppressWarnings("unchecked")
	public List<HierarchicalGeographic> getHierarchicalGeographics () {
        return (List<HierarchicalGeographic>) genericList(getList(ELEM_HIERGEOGRAPHIC));
    }

    /** Adds to the List of hierarchicalGeographic. 
     *  @throws XmlContentException */
    public void addHierarchicalGeographic (HierarchicalGeographic h) throws XmlContentException {
        addToList(h, ELEM_HIERGEOGRAPHIC);
    }

    /** Returns the List of cartographics (I refuse to say "cartographicses") */
    @SuppressWarnings("unchecked")
	public List<Cartographics> getCartographics () {
        return (List<Cartographics>) genericList(getList(ELEM_CARTOGRAPHICS));
    }

    /** Adds to the List of cartographics. 
     *  @throws XmlContentException */
    public void addCartographics (Cartographics c) throws XmlContentException {
        addToList(c, ELEM_CARTOGRAPHICS);
    }

    /** Returns the List of occupation. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getOccupations () {
        return (List<StringElement>) genericList(getList (ELEM_OCCUPATION));
    }

    /** Adds to the List of occupations. 
     *  @return the StringElement which is created
     *  @throws XmlContentException */
    public StringElement addOccupation (String s) throws XmlContentException {
        StringElement se = new StringElement (s, ELEM_OCCUPATION);
        addToList(se, ELEM_OCCUPATION);
        return se;
    }

    /** Returns the List of genres. */
    @SuppressWarnings("unchecked")
	public List<StringElement> getGenres () {
        return (List<StringElement>) genericList(getList (ELEM_GENRE));
    }

    /** Adds to the List of genres. 
     *  @return the StringElement which is created
     *  @throws XmlContentException */
    public StringElement addGenre (String s) throws XmlContentException {
        StringElement se = new StringElement (s, ELEM_GENRE);
        addToList(se, ELEM_GENRE);
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
             localName, ELEM_TOPIC,
             StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_GEOGRAPHIC,
                StringElement.class))
               return;
        if (matchList (reader, this, 
                localName, ELEM_TEMPORAL,
                StringElement.class))
               return;
        if (matchList (reader, this, 
                localName, ELEM_TITLEINFO,
                TitleInfo.class))
               return;
        if (matchList (reader, this, 
                localName, ELEM_NAME,
                Name.class))
               return;
        if (matchList (reader, this, 
                localName, ELEM_GEOCODE,
                StringElement.class))
               return;
        if (matchList (reader, this, 
                localName, ELEM_HIERGEOGRAPHIC,
                HierarchicalGeographic.class))
               return;
        if (matchList (reader, this, 
                localName, ELEM_CARTOGRAPHICS,
                Cartographics.class))
               return;
        if (matchList (reader, this, 
                localName, ELEM_OCCUPATION,
                StringElement.class))
               return;
        if (matchList (reader, this, 
                localName, ELEM_GENRE,
                StringElement.class))
               return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
