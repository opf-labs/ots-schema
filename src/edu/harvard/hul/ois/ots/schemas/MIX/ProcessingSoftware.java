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

public class ProcessingSoftware extends MixElement {

    private final static String ELEM_NAME = "processingSoftwareName";
    private final static String ELEM_VSN = "processingSoftwareVersion";
    private final static String ELEM_OSNAME = "processingOperatingSystemName";
    private final static String ELEM_OSVSN = "processingOperatingSystemVersion";

    /**  Constructor from XML
     */
    public ProcessingSoftware (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "ProcessingSoftware";
        parse(reader);
    }
    
    /** No-argument constructor */
    public ProcessingSoftware () {
        super();
        name = "ProcessingSoftware";
    }


    public StringElement getProcessingSoftwareName () {
        return (StringElement) getField (ELEM_NAME);
    }
    
    public void setProcessingSoftwareName (String s) throws XmlContentException {
        setField (ELEM_NAME, s);
    }
    
    public StringElement getProcessingSoftwareVersion () {
        return (StringElement) getField (ELEM_VSN);
    }
    
    public void setProcessingSoftwareVersion (String s) throws XmlContentException {
        setField (ELEM_VSN, s);
    }
    
    public StringElement getProcessingOperatingSystemName () {
        return (StringElement) getField (ELEM_OSNAME);
    }
    
    public void setProcessingOperatingSystemName (String s) throws XmlContentException {
        setField (ELEM_OSNAME, s);
    }
    
    public StringElement getProcessingOperatingSystemVersion () {
        return (StringElement) getField (ELEM_OSVSN);
    }
    
    public void setProcessingOperatingSystemVersion (String s) throws XmlContentException {
        setField (ELEM_OSVSN, s);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_NAME, mainElem);
        outputGenericElement (ns, ELEM_VSN, mainElem);
        outputGenericElement (ns, ELEM_OSNAME, mainElem);
        outputGenericElement (ns, ELEM_OSVSN, mainElem);

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
                localName, ELEM_NAME,
                StringElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_VSN,
                StringElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_OSNAME,
                StringElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_OSVSN,
                StringElement.class)) {
            return;
        }
        throw new XmlContentException ("Unknown element name " + localName);
    }
}
