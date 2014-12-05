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

public class DigitalCameraModel extends MixElement {

    private final static String ELEM_NAME = "digitalCameraModelName";
    private final static String ELEM_NUMBER = "digitalCameraModelNumber";
    private final static String ELEM_SERIAL = "digitalCameraModelSerialNo";

    /**  Constructor from XML
     */
    public DigitalCameraModel (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
    	name = "DigitalCameraModel";
        parse(reader);
    }
    
    /** No-argument constructor */
    public DigitalCameraModel () {
        super();
        name = "DigitalCameraModel";
    }


    public StringElement getDigitalCameraModelName () {
        return (StringElement) getField(ELEM_NAME);
    }
    
    public void setDigitalCameraModelName (String name) throws XmlContentException {
        setField (ELEM_NAME, name);
    }
    
    public StringElement getDigitalCameraModelNumber () {
        return (StringElement) getField(ELEM_NUMBER);
    }
    
    public void setDigitalCameraModelNumber (String num) throws XmlContentException {
        setField (ELEM_NUMBER, num);
    }

    public StringElement getDigitalCameraModelSerialNo () {
        return (StringElement) getField(ELEM_SERIAL);
    }
    
    public void setDigitalCameraModelSerialNo (String num) throws XmlContentException {
        setField (ELEM_SERIAL, num);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_NAME, mainElem);
        outputGenericElement (ns, ELEM_NUMBER, mainElem);
        outputGenericElement (ns, ELEM_SERIAL, mainElem);
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
                StringElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_NUMBER,
                StringElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_SERIAL,
                StringElement.class)) {
            return;
        }
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
