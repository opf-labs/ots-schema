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

import edu.harvard.hul.ois.ots.schemas.XmlContent.IntegerElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class MaximumOpticalResolution extends MixElement {

    private final static String ELEM_X = "xOpticalResolution";
    private final static String ELEM_Y = "yOpticalResolution";
    private final static String ELEM_UNIT = "opticalResolutionUnit";

    /* Permitted values for opticalResolutionUnit */
    private final static String[] VALUES_UNIT = 
    { "no absolute unit",
      "in.",
      "cm"};

    /**  Constructor from XML
     */
    public MaximumOpticalResolution (XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
    	name = "MaximumOpticalResolution";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public MaximumOpticalResolution () {
        super();
        name = "MaximumOpticalResolution";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_UNIT, VALUES_UNIT);
    }

    
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        IntegerElement x = (IntegerElement) getFieldElement (ELEM_X);
        if (x != null) {
            x.output (ns, mainElem);
        }
        IntegerElement y = (IntegerElement) getFieldElement (ELEM_Y);
        if (y != null) {
            y.output (ns, mainElem);
        }
        StringElement unit = (StringElement) getFieldElement (ELEM_UNIT);
        if (unit != null) {
            unit.output (ns, mainElem);
        }
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    public IntegerElement getXOpticalResolution () {
        return (IntegerElement) getField (ELEM_X);
    }
    
    public void setXOpticalResolution (int r) throws XmlContentException {
        setField (ELEM_X, r);
    }

    public IntegerElement getYOpticalResolution () {
        return (IntegerElement) getField (ELEM_Y);
    }
    
    public void setYOpticalResolution (int r) throws XmlContentException {
        setField (ELEM_Y, r);
    }

    /** Returns opticalResolutionUnit */
    public StringElement getOpticalResolutionUnit () {
        return (StringElement) getField (ELEM_UNIT);
    }
    
    /** Sets opticalResolutionUnit */
    public void setOpticalResolutionUnit (StringElement m) throws XmlContentException {
        setField (ELEM_UNIT, m);
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        if (match (reader, this, 
                localName, ELEM_X,
                IntegerElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_Y,
                IntegerElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_UNIT,
                StringElement.class)) {
            return;
        }
        throw new XmlContentException ("Unknown element name " + localName);

    }

    
}
