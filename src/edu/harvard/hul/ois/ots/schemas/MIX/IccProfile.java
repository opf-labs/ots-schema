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

/** IccProfile is a subelement of ColorProfile. */
public class IccProfile extends MixElement {

    private final static String ELEM_NAME = "iccProfileName";
    private final static String ELEM_VERSION = "iccProfileVersion";
    private final static String ELEM_URI = "iccProfileURI";

    /**  Constructor for this element
     */
    public IccProfile (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
    	name = "IccProfile";
        parse (reader);
    }
    
    /** No-argument constructor */
    public IccProfile () {
        super();
        name = "IccProfile";
    }
    

    /** Returns the profileName. */
    public StringElement getIccProfileName () {
        return (StringElement) getField (ELEM_NAME);
    }
    
    /** Sets the profileName. 
     * @throws XmlContentException */
    public void setIccProfileName (String s) throws XmlContentException {
        setField (ELEM_NAME, s);
    }
    
    /** Returns the profileVersion. */
    public StringElement getIccProfileVersion () {
        return (StringElement) getField (ELEM_VERSION);
    }
    
    /** Sets the profileVersion. 
     * @throws XmlContentException */
    public void setIccProfileVersion (String s) throws XmlContentException {
        setField (ELEM_VERSION, s);
    }
    
    /** Returns the profileURI. */
    public StringElement getIccProfileURI () {
        return (StringElement) getField (ELEM_URI);
    }
    
    /** Sets the profileURI. 
     * @throws XmlContentException */
    public void setIccProfileURI (String s) throws XmlContentException {
        setField (ELEM_URI, s);
    }
    
    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_NAME, mainElem);
        outputGenericElement (ns, ELEM_VERSION, mainElem);
        outputGenericElement (ns, ELEM_URI, mainElem);
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
        if (StringElement.match (reader, this,
                localName, ELEM_NAME,
                StringElement.class))
            return;
        if (StringElement.match (reader, this,
                localName, ELEM_VERSION,
                StringElement.class))
            return;
        if (StringElement.match (reader, this,
                localName, ELEM_URI,
                StringElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
