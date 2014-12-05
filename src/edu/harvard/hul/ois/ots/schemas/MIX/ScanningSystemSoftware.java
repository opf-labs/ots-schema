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

public class ScanningSystemSoftware extends MixElement {

    private final static String ELEM_NAME = "scanningSoftwareName";
    private final static String ELEM_VERSION = "scanningSoftwareVersionNo";

    /**  Constructor from XML
     */
    public ScanningSystemSoftware (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "ScanningSystemSoftware";
        parse(reader);
    }
    
    /** No-argument constructor */
    public ScanningSystemSoftware () {
        super();
        name = "ScanningSystemSoftware";
    }


    public StringElement getScanningSoftwareName () {
        return (StringElement) getField (ELEM_NAME);
    }
    
    public void setScanningSoftwareName (String x) throws XmlContentException {
        setField (ELEM_NAME, x);
    }

    public StringElement getScanningSoftwareVersionNo () {
        return (StringElement) getField (ELEM_VERSION);
    }
    
    public void setScanningSoftwareVersionNo (String x) throws XmlContentException {
        setField (ELEM_VERSION, x);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
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
                StringElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_VERSION,
                StringElement.class)) {
            return;
        }
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
