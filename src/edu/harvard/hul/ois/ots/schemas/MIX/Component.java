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

/** Component is a subelement of ReferenceBlackWhite. */
public class Component extends MixElement {

    private final static String ELEM_INTERP = "componentPhotometricInterpretation";
    private final static String ELEM_FOOTROOM = "footroom";
    private final static String ELEM_HEADROOM = "headroom";
    
    private final static String[] VALUES_INTERP =
    { "R", "G", "B", "Y", "Cb", "Cr" };
    
    /**  Constructor from XML
     */
    public Component (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "Component";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public Component () {
        super();
        name = "Component";
        defineRestrictions();
    }
    
    private void defineRestrictions() {
        addRestriction(ELEM_INTERP, VALUES_INTERP);
    }


    /** Returns the componentPhotometricInterpretation. */
    public StringElement getComponentPhotometricInterpretation () {
        return (StringElement) getField (ELEM_INTERP);
    }
    
    /** Sets the componentPhotometricInterpretation. Doesn't check for valid strings. */
    public void setComponentPhotometricInterpretation (String s) 
               throws XmlContentException {
        setField (ELEM_INTERP, s);
    }
    
    /** Returns the headroom */
    public RationalElement getHeadroom () {
        return (RationalElement) getField (ELEM_HEADROOM);
    }
    
    /** Sets the headroom 
     * @throws XmlContentException */
    public void setHeadroom (Rational r) throws XmlContentException {
        setField (ELEM_HEADROOM, r);
    }
    
    /** Returns the footroom */
    public RationalElement getFootroom () {
        return (RationalElement) getField (ELEM_FOOTROOM);
    }
    
    /** Sets the footroom 
     * @throws XmlContentException */
    public void setFootroom (Rational r) throws XmlContentException {
        setField (ELEM_FOOTROOM, r);
    }
    
    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_INTERP, mainElem);
        outputGenericElement (ns, ELEM_FOOTROOM, mainElem);
        outputGenericElement (ns, ELEM_HEADROOM, mainElem);
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
                localName, ELEM_INTERP,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_FOOTROOM,
                RationalElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_HEADROOM,
                RationalElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
