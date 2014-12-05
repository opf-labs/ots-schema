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

import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

/** ObjectIdentifier is a subelement of BasicDigitalObjectInformation */
public class ObjectIdentifier extends MixElement {

    private final static String ELEM_TYPE = "objectIdentifierType";
    private final static String ELEM_VALUE = "objectIdentifierValue";

    /**  Constructor from XML
     */
    public ObjectIdentifier (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "ObjectIdentifier";
        parse(reader);
    }
    
    /** No-argument constructor */
    public ObjectIdentifier () {
        super();
        name = "ObjectIdentifier";
    }

    /** Returns objectIdentifierType */
    public StringElement getObjectIdentifierType () {
        return (StringElement) getField (ELEM_TYPE);
    }
    
    /** Sets objectIdentifierType 
     * @throws XmlContentException */
    public void setObjectIdentifierType (String s) throws XmlContentException {
        setField (ELEM_TYPE, s);
    }
    
    /** Returns objectIdentifierValue */
    public StringElement getObjectIdentifierValue () {
        return (StringElement) getField (ELEM_VALUE);
    }

    /** Sets objectIdentifierValue 
     * @throws XmlContentException */
    public void setObjectIdentifierValue (String s) throws XmlContentException {
        setField (ELEM_VALUE, s);
    }

    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_TYPE, mainElem);
        outputGenericElement (ns, ELEM_VALUE, mainElem);
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
                localName, ELEM_TYPE,
                StringElement.class))
            return;

        if (match (reader, this, 
                localName, ELEM_VALUE,
                StringElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }


}
