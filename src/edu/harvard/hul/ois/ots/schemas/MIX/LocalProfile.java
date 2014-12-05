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

public class LocalProfile extends MixElement {

    private final static String ELEM_NAME = "localProfileName";
    private final static String ELEM_URL = "localProfileURL";

    /**  Constructor for this element
     */
    public LocalProfile (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "LocalProfile";
        parse (reader);
    }
    
    /** No-argument constructor */
    public LocalProfile () {
        super();
        name = "LocalProfile";
    }
    

    /** Output method which adds to a parent element.*/
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_NAME, mainElem);
        outputGenericElement (ns, ELEM_URL, mainElem);
    }


    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    /** Returns the profileName. */
    public StringElement getLocalProfileName () {
        return (StringElement) getField (ELEM_NAME);
    }
    
    /** Sets the profileName. 
     * @throws XmlContentException */
    public void setLocalProfileName (String s) throws XmlContentException {
        setField (ELEM_NAME, s);
    }
    
    /** Returns the profileURL. */
    public StringElement getLocalProfileURL () {
        return (StringElement) getField (ELEM_URL);
    }
    
    /** Sets the profileURI. 
     * @throws XmlContentException */
    public void setIccProfileURL (String s) throws XmlContentException {
        setField (ELEM_URL, s);
    }

    /** Classes must implement this to handle START_ELEMENT cases.  */
    protected void dispatch (String localName, XMLStreamReader reader) 
                  throws XMLStreamException, XmlContentException {
        if (match (reader, this,
                localName, ELEM_NAME,
                StringElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_URL,
                StringElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
