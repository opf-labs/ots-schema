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
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class SpatialMetrics extends MixElement {

    private final static String ELEM_PLANE = "samplingFrequencyPlane";
    private final static String ELEM_UNIT = "samplingFrequencyUnit";
    private final static String ELEM_X = "xSamplingFrequency";
    private final static String ELEM_Y = "ySamplingFrequency";

    /* Permitted values for samplingFrequencyPlane */
    private final static String[] VALUES_PLANE = 
    { "camera/scanner focal plane",
     "object plane",
     "source object plane"};

    /* Permitted values for samplingFrequencyUnit */
    private final static String[] VALUES_UNIT = 
    { "no absolute unit of measurement",
     "in.",
     "cm"};

    /**  Constructor from XML
     */
    public SpatialMetrics (XMLStreamReader reader) 
              throws XMLStreamException, XmlContentException {
        name = "SpatialMetrics";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public SpatialMetrics () {
        super();
        name = "SpatialMetrics";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_UNIT, VALUES_UNIT);
        addRestriction(ELEM_PLANE, VALUES_PLANE);
    }


    public StringElement getSamplingFrequencyPlane () {
        return (StringElement) getField (ELEM_PLANE);
    }
    
    /** Sets samplingFrequencyPlane 
     * @throws XmlContentException */
    public void setSamplingFrequencyPlane (String s) throws XmlContentException {
        setField (ELEM_PLANE, s);
    }
    
    public StringElement getSamplingFrequencyUnit () {
        return (StringElement) getField (ELEM_UNIT);
    }
    
    public void setSamplingFrequencyUnit (String s) throws XmlContentException {
        setField (ELEM_UNIT, s);
    }
    
    public RationalElement getXSamplingFrequency () {
        return (RationalElement) getField (ELEM_X);
    }
    
    public void setXSamplingFrequency (Rational r) throws XmlContentException {
        setField (ELEM_X, r);
    }

    public RationalElement getYSamplingFrequency () {
        return (RationalElement) getField (ELEM_Y);
    }
    
    public void setYSamplingFrequency (Rational r) throws XmlContentException {
        setField (ELEM_Y, r);
    }
    
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_PLANE, mainElem);
        outputGenericElement (ns, ELEM_UNIT, mainElem);
        outputGenericElement (ns, ELEM_X, mainElem);
        outputGenericElement (ns, ELEM_Y, mainElem);

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
                localName, ELEM_PLANE,
                StringElement.class)) {
            return;
        }
        if (match(reader, this,
                localName, ELEM_UNIT,
                StringElement.class)) {
            return;
        }
        if (match(reader, this,
                localName, ELEM_X,
                RationalElement.class))
            return;
        if (match(reader, this,
                localName, ELEM_Y,
                RationalElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
