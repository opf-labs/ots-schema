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

public class YCbCrCoefficients extends MixElement {

    /* The elements which make up YCbCrCoefficients are capitalized
     * even though they're rationals. Inconsistent, but not our fault. */ 
    private final static String ELEM_RED = "LumaRed";
    private final static String ELEM_GREEN = "LumaGreen";
    private final static String ELEM_BLUE = "LumaBlue";

    /**  Constructor from XML
     */
    public YCbCrCoefficients (XMLStreamReader reader) 
              throws XMLStreamException, XmlContentException {
        name = "YCbCrCoefficients";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public YCbCrCoefficients () {
        super();
        name = "YCbCrCoefficients";
        defineRestrictions();
    }

    private void defineRestrictions () {
    }

    public RationalElement getLumaRed () {
        return (RationalElement) getField (ELEM_RED);
    }
    
    public void setLumaRed (Rational x) throws XmlContentException {
        setField (ELEM_RED, x);
    }

    public RationalElement getLumaGreen () {
        return (RationalElement) getField (ELEM_GREEN);
    }
    
    public void setLumaGreen (Rational x) throws XmlContentException {
        setField (ELEM_GREEN, x);
    }

    public RationalElement getLumaBlue () {
        return (RationalElement) getField (ELEM_BLUE);
    }
    
    public void setLumaBlue (Rational x) throws XmlContentException {
        setField (ELEM_BLUE, x);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_RED, mainElem);
        outputGenericElement (ns, ELEM_GREEN, mainElem);
        outputGenericElement (ns, ELEM_BLUE, mainElem);
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
            if (RationalElement.match (reader, this, 
                    localName, ELEM_RED, 
                    RationalElement.class))
                return;
            if (RationalElement.match (reader, this, 
                    localName, ELEM_GREEN, 
                    RationalElement.class))
                return;
            if (RationalElement.match (reader, this, 
                    localName, ELEM_BLUE, 
                    RationalElement.class))
                return;
            throw new XmlContentException ("Unknown element name " + localName);
    }
    
}
