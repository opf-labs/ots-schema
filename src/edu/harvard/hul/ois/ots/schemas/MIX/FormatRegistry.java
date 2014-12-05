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

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

/** FormatRegistry is a subelement of BasicDigitalObjectInformation */
public class FormatRegistry extends MixElement {

    private final static String ELEM_NAME = "formatRegistryName";
    private final static String ELEM_KEY = "formatRegistryKey";

    /**  Constructor from XML
     */
    public FormatRegistry (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "FormatRegistry";
        parse(reader);
    }
    
    /** No-argument constructor */
    public FormatRegistry () {
        super();
        name = "FormatRegistry";
    }


    /** Returns formatRegistryName */
    public StringElement getFormatRegistryName () {
        return (StringElement) getField (ELEM_NAME);
    }
    
    /** Sets formatRegistryName 
     * @throws XmlContentException */
    public void setFormatRegistryName (String s) throws XmlContentException {
        setField (ELEM_NAME, s);
    }
    
    /** Returns formatRegistryKey */
    public StringElement getFormatRegistryKey () {
        return (StringElement) getField (ELEM_KEY);
    }

    /** Sets formatRegistryKey 
     * @throws XmlContentException */
    public void setFormatRegistryKey (String s) throws XmlContentException {
        setField (ELEM_KEY, s);
    }
    
    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        StringElement name = (StringElement) getFieldElement(ELEM_NAME);
        if (name != null) {
            name.output (ns, mainElem);
        }
        StringElement key = (StringElement) getFieldElement(ELEM_KEY);
        if (key != null) {
            key.output (ns, mainElem);
        }
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
                localName, ELEM_NAME,
                StringElement.class))
            return;

        if (match (reader, this, 
                localName, ELEM_KEY,
                StringElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }


}
