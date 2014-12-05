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

public class PrimaryChromaticities extends MixElement {

    private final static String ELEM_REDX = "primaryChromaticitiesRedX";
    private final static String ELEM_REDY = "primaryChromaticitiesRedY";
    private final static String ELEM_GREENX = "primaryChromaticitiesGreenX";
    private final static String ELEM_GREENY = "primaryChromaticitiesGreenY";
    private final static String ELEM_BLUEX = "primaryChromaticitiesBlueX";
    private final static String ELEM_BLUEY = "primaryChromaticitiesBlueY";

    /**  Constructor from XML
     */
    public PrimaryChromaticities (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "PrimaryChromaticities";
        parse(reader);
    }
    
    /** No-argument constructor */
    public PrimaryChromaticities () {
        super();
        name = "PrimaryChromaticities";
    }


    public RationalElement getPrimaryChromaticitiesRedX () {
        return (RationalElement) getField (ELEM_REDX);
    }
    
    public void setPrimaryChromaticitiesRedX (Rational r) throws XmlContentException {
        setField (ELEM_REDX, r);
    }
    
    public RationalElement getPrimaryChromaticitiesRedY () {
        return (RationalElement) getField (ELEM_REDY);
    }
    
    public void setPrimaryChromaticitiesRedY (Rational r) throws XmlContentException {
        setField (ELEM_REDY, r);
    }

    public RationalElement getPrimaryChromaticitiesGreenX () {
        return (RationalElement) getField (ELEM_GREENX);
    }

    public void setPrimaryChromaticitiesGreenX (Rational r) throws XmlContentException {
        setField (ELEM_GREENX, r);
    }

    public RationalElement getPrimaryChromaticitiesGreenY () {
        return (RationalElement) getField (ELEM_GREENY);
    }

    public void setPrimaryChromaticitiesGreenY (Rational r) throws XmlContentException {
        setField (ELEM_GREENY, r);
    }

    public RationalElement getPrimaryChromaticitiesBlueX () {
        return (RationalElement) getField (ELEM_BLUEX);
    }
    
    public void setPrimaryChromaticitiesBlueX (Rational r) throws XmlContentException {
        setField (ELEM_BLUEX, r);
    }

    public RationalElement getPrimaryChromaticitiesBlueY () {
        return (RationalElement) getField (ELEM_BLUEY);
    }

    public void setPrimaryChromaticitiesBlueY (Rational r) throws XmlContentException {
        setField (ELEM_BLUEY, r);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_REDX, mainElem);
        outputGenericElement (ns, ELEM_REDY, mainElem);
        outputGenericElement (ns, ELEM_GREENX, mainElem);
        outputGenericElement (ns, ELEM_GREENY, mainElem);
        outputGenericElement (ns, ELEM_BLUEX, mainElem);
        outputGenericElement (ns, ELEM_BLUEY, mainElem);

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
                localName, ELEM_REDX,
                RationalElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_REDY,
                RationalElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_GREENX,
                RationalElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_GREENY,
                RationalElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_BLUEX,
                RationalElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_BLUEY,
                RationalElement.class)) {
            return;
        }
        throw new XmlContentException ("Unknown element name " + localName);
    }
}
