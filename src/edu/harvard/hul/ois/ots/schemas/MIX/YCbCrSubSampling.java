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
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class YCbCrSubSampling extends MixElement {

    private final static String ELEM_HORIZ = "yCbCrSubsampleHoriz";
    private final static String ELEM_VERT = "yCbCrSubsampleVert";

    /* Permitted values for yCbCrSubsampleHoriz and yCbCrSubsampleVert */
    private final static int[] VALUES_SUBSAMPLE = 
    { 1, 2, 4 };
    
    /**  Constructor for this element
     */
    public YCbCrSubSampling (XMLStreamReader reader)
            throws XMLStreamException, XmlContentException {
        name = "YCbCrSubSampling";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public YCbCrSubSampling () {
        super();
        name = "YCbCrSubSampling";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_HORIZ, VALUES_SUBSAMPLE);
        addRestriction(ELEM_VERT, VALUES_SUBSAMPLE);
    }
    
    public IntegerElement getYCbCrSubsampleHoriz () {
        return (IntegerElement) getField (ELEM_HORIZ);
    }
    
    public void setYCbCrSubsampleHoriz (int x) throws XmlContentException {
        setField (ELEM_HORIZ, x);
    }
    
    public IntegerElement getYCbCrSubsampleVert () {
        return (IntegerElement) getField (ELEM_VERT);
    }
    
    public void setYCbCrSubsampleVert (int x) throws XmlContentException {
        setField (ELEM_VERT, x);
    }
    
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputGenericElement (ns, ELEM_HORIZ, mainElem);
        outputGenericElement (ns, ELEM_VERT, mainElem);
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }
    
    /** Classes must implement this to handle START_ELEMENT cases.  */
    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        if (IntegerElement.match (reader, this, 
                localName, ELEM_HORIZ, 
                IntegerElement.class))
            return;
        if (IntegerElement.match (reader, this, 
                localName, ELEM_VERT, 
                IntegerElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }


}
