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

/** Compression is a subelement of BasicDigitalObjectInformation */
public class Compression extends MixElement {

    private final static String ELEM_SCHEME = "compressionScheme";
    private final static String ELEM_LOCALLIST = "compressionSchemeLocalList";
    private final static String ELEM_LOCALVAL = "compressionSchemeLocalValue";
    private final static String ELEM_RATIO = "compressionRatio";

    /**  Constructor from XML
     */
    public Compression (XMLStreamReader reader) 
              throws XMLStreamException, XmlContentException {
        name = "Compression";
        parse(reader);
    }
    
    /** No-argument constructor */
    public Compression () {
        super();
        name = "Compression";
    }


    /** Returns compressionScheme */
    public StringElement getCompressionScheme () {
        return (StringElement) getField (ELEM_SCHEME);
    }
    
    /** Sets compressionScheme 
     * @throws XmlContentException */
    public void setCompressionScheme (String s) throws XmlContentException {
        setField (ELEM_SCHEME, s);
    }
    
    /** Returns compressionSchemeLocalList */
    public StringElement getCompressionSchemeLocalList () {
        return (StringElement) getField (ELEM_LOCALLIST);
    }
    
    /** Sets compressionSchemeLocalList 
     * @throws XmlContentException */
    public void setCompressionSchemeLocalList (String s) throws XmlContentException {
        setField (ELEM_LOCALLIST, s);
    }
    
    /** Returns compressionSchemeLocalValue */
    public StringElement getCompressionSchemeLocalValue () {
        return (StringElement) getField (ELEM_LOCALVAL);
    }
    
    /** Sets compressionSchemeLocalValue 
     * @throws XmlContentException */
    public void setCompressionSchemeLocalValue (String s) throws XmlContentException {
        setField (ELEM_LOCALVAL, s);
    }
    
    /** Returns compressionRatio */
    public RationalElement getCompressionRatio () {
        return (RationalElement) getField (ELEM_RATIO);
    }
    
    /** Sets compressionRatio 
     * @throws XmlContentException */
    public void setCompressionRatio (Rational x) throws XmlContentException {
        setField (ELEM_RATIO, x);
    }
    
    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputGenericElement (ns, ELEM_SCHEME, mainElem);
        outputGenericElement (ns, ELEM_LOCALLIST, mainElem);
        outputGenericElement(ns, ELEM_LOCALVAL, mainElem);
        outputGenericElement(ns, ELEM_RATIO, mainElem);
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
                localName, ELEM_SCHEME,
                StringElement.class))
            return;
        if (match (reader, this, 
                localName, ELEM_LOCALLIST,
                StringElement.class))
            return;
        if (match (reader, this, 
                localName, ELEM_LOCALVAL,
                StringElement.class))
            return;
        if (match (reader, this, 
                localName, ELEM_RATIO,
                RationalElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }
    

}
