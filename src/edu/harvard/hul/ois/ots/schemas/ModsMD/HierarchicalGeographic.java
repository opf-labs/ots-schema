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

public class HierarchicalGeographic extends ModsElement {

    private final static String ELEM_ET = "extraterrestrialArea";
    private final static String ELEM_CONTINENT = "continent";
    private final static String ELEM_COUNTRY = "country";
    private final static String ELEM_PROVINCE = "province";
    private final static String ELEM_REGION = "region";
    private final static String ELEM_STATE = "state";
    private final static String ELEM_TERRITORY = "territory";
    private final static String ELEM_COUNTY = "county";
    private final static String ELEM_CITY = "city";
    private final static String ELEM_CITYSECTION = "citySection";
    private final static String ELEM_ISLAND = "island";
    private final static String ELEM_AREA = "area";

    /**  Constructor for this element
     */
    public HierarchicalGeographic (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "hierarchicalGeographic";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public HierarchicalGeographic () {
        super();
        name = "hierarchicalGeographic";
        defineRestrictions();
    }
    
    /** Define restrictions */
    private void defineRestrictions () {
    }

    /** Get the extraterrestrialAreas */
    @SuppressWarnings("unchecked")
	public List<StringElement> getExtraterrestrialAreas () {
        return (genericList(getList (ELEM_ET)));
    }

    /** Add an extraterrestrialArea 
     * @throws XmlContentException
     * @return The created StringElement */
    public StringElement addExtraterrestrialArea (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_ET);
        addToList (se, ELEM_ET);
        return se;
    }

    /** Get the continents */
    @SuppressWarnings("unchecked")
	public List<StringElement> getContinents () {
        return (genericList(getList (ELEM_CONTINENT)));
    }

    /** Add a continent 
     * @throws XmlContentException
     * @return The created StringElement */
    public StringElement addContinent (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_ET);
        addToList (se, ELEM_CONTINENT);
        return se;
    }

    /** Get the countRies */
    @SuppressWarnings("unchecked")
	public List<StringElement> getCountries () {
        return (genericList(getList (ELEM_COUNTRY)));
    }

    /** Add a country 
     * @throws XmlContentException
     * @return The created StringElement */
    public StringElement addCountry (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_COUNTRY);
        addToList (se, ELEM_COUNTRY);
        return se;
    }

    /** Get the provinces */
    @SuppressWarnings("unchecked")
	public List<StringElement> getProvincies () {
        return (genericList(getList (ELEM_PROVINCE)));
    }

    /** Add a province 
     * @throws XmlContentException
     * @return The created StringElement */
    public StringElement addProvince (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_PROVINCE);
        addToList (se, ELEM_PROVINCE);
        return se;
    }

    /** Get the regions */
    @SuppressWarnings("unchecked")
	public List<StringElement> getRegions () {
        return (genericList(getList (ELEM_REGION)));
    }

    /** Add a region 
     * @throws XmlContentException
     * @return The created StringElement */
    public StringElement addRegion (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_REGION);
        addToList (se, ELEM_REGION);
        return se;
    }

    /** Get the states */
    @SuppressWarnings("unchecked")
	public List<StringElement> getStates () {
        return (genericList(getList (ELEM_STATE)));
    }

    /** Add a state 
     * @throws XmlContentException
     * @return The created StringElement */
    public StringElement addState (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_STATE);
        addToList (se, ELEM_STATE);
        return se;
    }

    /** Get the terrotories */
    @SuppressWarnings("unchecked")
	public List<StringElement> getTerritories () {
        return (genericList(getList (ELEM_TERRITORY)));
    }

    /** Add a territory 
     * @throws XmlContentException
     * @return The created StringElement */
    public StringElement addTerritory (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_TERRITORY);
        addToList (se, ELEM_TERRITORY);
        return se;
    }

    /** Get the countiess */
    @SuppressWarnings("unchecked")
	public List<StringElement> getCounties () {
        return (genericList(getList (ELEM_COUNTY)));
    }

    /** Add a county 
     * @throws XmlContentException
     * @return The created StringElement */
    public StringElement addCounty (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_COUNTY);
        addToList (se, ELEM_COUNTY);
        return se;
    }

    /** Get the cities */
    @SuppressWarnings("unchecked")
	public List<StringElement> getCities () {
        return (genericList(getList (ELEM_CITY)));
    }

    /** Add a city 
     * @throws XmlContentException
     * @return The created StringElement */
    public StringElement addCity (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_CITY);
        addToList (se, ELEM_CITY);
        return se;
    }

    /** Get the citySections */
    @SuppressWarnings("unchecked")
	public List<StringElement> getCitySections () {
        return (genericList(getList (ELEM_CITYSECTION)));
    }

    /** Add a citySection
     * @throws XmlContentException
     * @return The created StringElement */
    public StringElement addCitySection (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_CITYSECTION);
        addToList (se, ELEM_CITYSECTION);
        return se;
    }

    /** Get the islands */
    @SuppressWarnings("unchecked")
	public List<StringElement> getIslands () {
        return (genericList(getList (ELEM_ISLAND)));
    }

    /** Add a island
     * @throws XmlContentException
     * @return The created StringElement */
    public StringElement addIsland (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_ISLAND);
        addToList (se, ELEM_ISLAND);
        return se;
    }

    /** Get the areas */
    @SuppressWarnings("unchecked")
	public List<StringElement> getAreas () {
        return (genericList(getList (ELEM_AREA)));
    }

    /** Add an area
     * @throws XmlContentException
     * @return The created StringElement */
    public StringElement addArea (String x) throws XmlContentException {
        StringElement se = new StringElement (x, ELEM_AREA);
        addToList (se, ELEM_AREA);
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
            localName, ELEM_ET,
            StringElement.class))
        return;
        if (matchList (reader, this, 
            localName, ELEM_CONTINENT,
            StringElement.class))
        return;
        if (matchList (reader, this, 
            localName, ELEM_COUNTRY,
            StringElement.class))
        return;
        if (matchList (reader, this, 
            localName, ELEM_PROVINCE,
            StringElement.class))
        return;
        if (matchList (reader, this, 
            localName, ELEM_REGION,
            StringElement.class))
        return;
        if (matchList (reader, this, 
                localName, ELEM_STATE,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_TERRITORY,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_COUNTY,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_CITY,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_CITYSECTION,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_ISLAND,
                StringElement.class))
            return;
        if (matchList (reader, this, 
                localName, ELEM_AREA,
                StringElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
