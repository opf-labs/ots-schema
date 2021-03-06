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

/** FormatDesignation is a subelement of BasicDigitalObjectInformation */
public class FormatDesignation extends MixElement {

    private final static String ELEM_NAME = "formatName";
    private final static String ELEM_VERSION = "formatVersion";

    /**  Constructor from XML
     */
    public FormatDesignation (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
    	name = "FormatDesignation";
        parse(reader);
    }
    
    /** No-argument constructor */
    public FormatDesignation () {
        super();
        name = "FormatDesignation";
    }



    /** Returns formatName */
    public StringElement getFormatName () {
        return (StringElement) getField (ELEM_NAME);
    }
    
    /** Sets formatName 
     * @throws XmlContentException */
    public void setFormatName (String s) throws XmlContentException {
        setField (ELEM_NAME, s);
    }
    
    /** Returns formatVersion */
    public StringElement getFormatVersion () {
        return (StringElement) getField (ELEM_VERSION);
    }

    /** Sets formatVersion 
     * @throws XmlContentException */
    public void setFormatVersion (String s) throws XmlContentException {
        setField (ELEM_VERSION, s);
    }

    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_NAME, mainElem);
        outputGenericElement (ns, ELEM_VERSION, mainElem);
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
                localName, ELEM_VERSION,
                StringElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

    
}
