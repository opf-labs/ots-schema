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

/** Implements the "Fixity" element in MIX, which is a subelement of
 *  BasicDigitalObjectInformation. This element can be repeated, so
 *  it should be wrapped in a ListElement<Fixity>.
 */
public class Fixity extends MixElement {

    private final static String ELEM_ALGORITHM = "messageDigestAlgorithm";
    private final static String ELEM_DIGEST = "messageDigest";
    private final static String ELEM_ORIG = "messageDigestOriginator";
    
    /* Permitted values for messageDigestAlgorithm */
    private final static String[] VALUES_ALGORITHM = 
    { "Adler-32", "CRC32", "HAVAL", "MD5", "MNP", "SHA-1",
       "SHA-256", "SHA-384", "SHA512", "TIGER", "WHIRLPOOL", "unknown"};
    
    /**  Constructor from XML
     */
    public Fixity (XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        name = "Fixity";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public Fixity () {
        super();
        name = "Fixity";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_ALGORITHM, VALUES_ALGORITHM);
    }

    /** Returns messageDigestAlgorithm */
    public StringElement getMessageDigestAlgorithm () {
        return (StringElement) getField (ELEM_ALGORITHM);
    }
    
    /** Sets messageDigestAlgorithm 
     * @throws XmlContentException */
    public void setMessageDigestAlgorithm (String s) throws XmlContentException {
        setField (ELEM_ALGORITHM, s);
    }
    
    /** Returns messageDigest */
    public StringElement getMessageDigest () {
        return (StringElement) getField (ELEM_DIGEST);
    }
    
    /** Sets messageDigestAlgorithm 
     * @throws XmlContentException */
    public void setMessageDigest (String s) throws XmlContentException {
        setField (ELEM_DIGEST, s);
    }
    
    /** Returns messageDigestOriginator */
    public StringElement getMessageDigestOriginator () {
        return (StringElement) getField (ELEM_ORIG);
    }
    
    /** Sets messageDigestOriginator 
     * @throws XmlContentException */
    public void setMessageDigestOriginator (String s) throws XmlContentException {
        setField (ELEM_ORIG, s);
    }
    

    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_ALGORITHM, mainElem);
        outputGenericElement (ns, ELEM_DIGEST, mainElem);
        outputGenericElement (ns, ELEM_ORIG, mainElem);
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
                localName, ELEM_ALGORITHM,
                StringElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_DIGEST,
                StringElement.class))
            return;
        if (match (reader, this, 
                localName, ELEM_ORIG,
                StringElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
