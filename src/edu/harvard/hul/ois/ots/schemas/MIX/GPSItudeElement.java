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

import edu.harvard.hul.ois.ots.schemas.XmlContent.Rational;
import edu.harvard.hul.ois.ots.schemas.XmlContent.RationalElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

/** GPSLongitude and GPSLatitude are essentially the same */
public abstract class GPSItudeElement extends MixElement {

    protected final static String ELEM_DEG = "degrees";
    protected final static String ELEM_MIN = "minutes";
    protected final static String ELEM_SEC = "seconds";

    public RationalElement getDegrees () {
        return (RationalElement) getField (ELEM_DEG);
    }
    
    public void setDegrees (Rational r) throws XmlContentException {
        setField (ELEM_DEG, r);
    }

    public RationalElement getMinutes () {
        return (RationalElement) getField (ELEM_MIN);
    }
    
    public void setMinutes (Rational r) throws XmlContentException {
        setField (ELEM_MIN, r);
    }

    public RationalElement getSeconds () {
        return (RationalElement) getField (ELEM_SEC);
    }
    
    public void setSeconds (Rational r) throws XmlContentException {
        setField (ELEM_SEC, r);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        RationalElement deg = (RationalElement) getFieldElement (ELEM_DEG);
        if (deg != null) {
           deg.output (ns, mainElem);
        }
        RationalElement min = (RationalElement) getFieldElement (ELEM_MIN);
        if (min != null) {
           min.output (ns, mainElem);
        }
        RationalElement sec = (RationalElement) getFieldElement (ELEM_SEC);
        if (sec != null) {
           sec.output (ns, mainElem);
        }

    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        if (match (reader, this, 
                localName, ELEM_DEG,
                RationalElement.class))
            return;
        if (match (reader, this, 
                localName, ELEM_MIN,
                RationalElement.class))
            return;
        if (match (reader, this, 
                localName, ELEM_SEC,
                RationalElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
