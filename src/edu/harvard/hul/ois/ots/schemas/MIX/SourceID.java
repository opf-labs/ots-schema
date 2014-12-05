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

/** SourceID is a subelement of SourceInformation */
public class SourceID extends MixElement {

    private final static String ELEM_TYPE = "sourceIDType";
    private final static String ELEM_VALUE = "sourceIDValue";

    /**  Constructor from XML
     */
    public SourceID (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "SourceID";
        parse(reader);
    }
    
    /** No-argument constructor */
    public SourceID () {
        super();
        name = "SourceID";
    }

    public StringElement getSourceIDType () {
        return (StringElement) getField (ELEM_TYPE);
    }
    
    public void setSourceIDType (String t) throws XmlContentException {
        setField (ELEM_TYPE, t);
    }
    
    public StringElement getSourceIDValue () {
        return (StringElement) getField (ELEM_VALUE);
    }
    public void setSourceIDValue (String v) throws XmlContentException {
        setField (ELEM_VALUE, v);
    }
    
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
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
                StringElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_VALUE,
                StringElement.class)) {
            return;
        }
        throw new XmlContentException ("Unknown element name " + localName);
    }
}
