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

public class SubjectDistance extends MixElement {

    private final static String ELEM_DIST = "distance";
    private final static String ELEM_MMDIST = "MinMaxDistance";

    /**  Constructor from XML
     */
    public SubjectDistance (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "SubjectDistance";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public SubjectDistance () {
        super();
        name = "SubjectDistance";
        defineRestrictions();
    }
    
    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_DIST, Restriction.NONNEGATIVE);
    }

    /** Returns distance */
    public RealElement getDistance () {
        return (RealElement) getField (ELEM_DIST);
    }

    /** Sets distance 
     * @throws XmlContentException */
    public void setDistance (Double s) throws XmlContentException {
        setField (ELEM_DIST, s);
    }

    /** Returns MinMaxDistance */
    public MinMaxDistance getMinMaxDistance () {
        return (MinMaxDistance) getField (ELEM_MMDIST);
    }

    /** Sets MinMaxDistance 
     * @throws XmlContentException */
    public void setMinMaxDistance (MinMaxDistance s) throws XmlContentException {
        setField (ELEM_MMDIST, s);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_DIST, mainElem);
        outputGenericElement (ns, ELEM_MMDIST, mainElem);
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
                localName, ELEM_DIST,
                DecimalElement.class))
            return;
        if (match (reader, this, 
                localName, ELEM_MMDIST,
                MinMaxDistance.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
