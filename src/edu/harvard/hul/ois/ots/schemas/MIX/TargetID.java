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

public class TargetID extends MixElement {

    private final static String ELEM_MFR = "targetManufacturer";
    private final static String ELEM_NAME = "targetName";
    private final static String ELEM_NO = "targetNo";
    private final static String ELEM_MEDIA = "targetMedia";

    /**  Constructor from XML
     */
    public TargetID (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "TargetID";
        parse(reader);
    }
    
    /** No-argument constructor */
    public TargetID () {
        super();
        name = "TargetID";
    }

    public StringElement getTargetManufacturer () {
        return (StringElement) getField (ELEM_MFR);
    }
    
    public void setTargetManufacturer (String mfr) throws XmlContentException {
        setField (ELEM_MFR, mfr);
    }

    public StringElement getTargetName () {
        return (StringElement) getField (ELEM_NAME);
    }
    
    public void setTargetName (String name) throws XmlContentException {
        setField (ELEM_NAME, name);
    }
    public StringElement getTargetNo () {
        return (StringElement) getField (ELEM_NO);
    }
    
    public void setTargetNo (String no) throws XmlContentException {
        setField (ELEM_NO, no);
    }
    public StringElement getTargetMedia () {
        return (StringElement) getField (ELEM_MEDIA);
    }
    
    public void setTargetMedia (String m) throws XmlContentException {
        setField (ELEM_MEDIA, m);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_MFR, mainElem);
        outputGenericElement (ns, ELEM_NAME, mainElem);
        outputGenericElement (ns, ELEM_NO, mainElem);
        outputGenericElement (ns, ELEM_MEDIA, mainElem);
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
                localName, ELEM_MFR,
                StringElement.class))
            return;
        if (StringElement.match (reader, this,
                localName, ELEM_NAME,
                StringElement.class))
            return;
        if (StringElement.match (reader, this,
                localName, ELEM_NO,
                StringElement.class))
            return;
        if (StringElement.match (reader, this,
                localName, ELEM_MEDIA,
                StringElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
