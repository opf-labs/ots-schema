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
package edu.harvard.hul.ois.ots.schemas.MIX;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.DecimalElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.RealElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.Restriction;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class MinMaxDistance extends MixElement {

    private final static String ELEM_MIN = "minDistance";
    private final static String ELEM_MAX = "maxDistance";

    /**  Constructor from XML
     */
    public MinMaxDistance (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "MinMaxDistance";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public MinMaxDistance () {
        super();
        name = "MinMaxDistance";
        defineRestrictions();
    }
    
    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_MIN, Restriction.NONNEGATIVE);
        addRestriction(ELEM_MAX, Restriction.NONNEGATIVE);
    }

    /** Returns minDistance */
    public RealElement getMinDistance () {
        return (RealElement) getField (ELEM_MIN);
    }

    /** Sets minDistance 
     * @throws XmlContentException */
    public void setMinDistance (Double s) throws XmlContentException {
        setField (ELEM_MIN, s);
    }

    /** Returns maxDistance */
    public RealElement getMaxDistance () {
        return (RealElement) getField (ELEM_MAX);
    }

    /** Sets maxDistance 
     * @throws XmlContentException */
    public void setMaxDistance (Double s) throws XmlContentException {
        setField (ELEM_MAX, s);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_MIN, mainElem);
        outputGenericElement (ns, ELEM_MAX, mainElem);
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }
    
    /** Classes must implement this to handle START_ELEMENT cases.  */
    @Override
    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        if (match (reader, this, 
                localName, ELEM_MIN,
                DecimalElement.class))
            return;

        if (match (reader, this, 
                localName, ELEM_MAX,
                DecimalElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }


}
