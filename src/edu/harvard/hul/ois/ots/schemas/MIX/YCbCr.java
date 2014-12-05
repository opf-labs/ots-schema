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

public class YCbCr extends MixElement {

    private final static String ELEM_SUB = "YCbCrSubSampling";
    private final static String ELEM_POS = "yCbCrPositioning";
    private final static String ELEM_COEFF = "YCbCrCoefficients";
    
    /* Permitted values for yCbCrPositioning */
    private final static int[] VALUES_POS = 
    { 1, 2 };
    
    /**  Constructor from XML
     */
    public YCbCr (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "YCbCr";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public YCbCr () {
        super();
        name = "YCbCr";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_POS, VALUES_POS);
    }

    public YCbCrSubSampling getYCbCrSubSampling () {
        return (YCbCrSubSampling) getField (ELEM_SUB);
    }
    
    public void setYCbCrSubSampling (YCbCrSubSampling x) throws XmlContentException {
        setField (ELEM_SUB, x);
    }
    
    public IntegerElement getYCbCrPositioning () {
        return (IntegerElement) getField (ELEM_POS);
    }
    

    public void setYCbCrPositioning (int n) throws XmlContentException {
        setField (ELEM_POS, n);
    }
    
    public YCbCrCoefficients getYCbCrCoefficients () {
        return (YCbCrCoefficients) getField (ELEM_COEFF);
    }
    
    public void setYCbCrCoefficients (YCbCrCoefficients x) throws XmlContentException {
        setField (ELEM_COEFF, x);
    }

    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_SUB, mainElem);
        outputGenericElement (ns, ELEM_POS, mainElem);
        outputGenericElement (ns, ELEM_COEFF, mainElem);
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
                localName, ELEM_SUB, 
                YCbCrSubSampling.class))
            return;
        if (match (reader, this,
                localName, ELEM_POS,
                GeneralCaptureInformation.class)) {
            return;
        }
        if (YCbCrCoefficients.match (reader, this,
                localName, ELEM_COEFF,
                YCbCrCoefficients.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
