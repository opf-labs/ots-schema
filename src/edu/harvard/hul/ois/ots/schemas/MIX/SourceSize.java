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

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class SourceSize extends MixElement {

    private final static String ELEM_X = "SourceXDimension";
    private final static String ELEM_Y = "SourceYDimension";
    private final static String ELEM_Z = "SourceZDimension";

    /**  Constructor from XML
     */
    public SourceSize (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        name = "SourceSize";
        parse(reader);
    }
    
    /** No-argument constructor */
    public SourceSize () {
        super();
        name = "SourceSize";
    }

    public SourceXDimension getSourceXDimension () {
        return (SourceXDimension) getField (ELEM_X);
    }
    
    public void setSourceXDimension (SourceXDimension d) throws XmlContentException {
        setField (ELEM_X, d);
    }

    public SourceYDimension getSourceYDimension () {
        return (SourceYDimension) getField (ELEM_Y);
    }
    
    public void setSourceYDimension (SourceYDimension d) throws XmlContentException {
        setField (ELEM_Y, d);
    }

    public SourceZDimension getSourceZDimension () {
        return (SourceZDimension) getField (ELEM_Z);
    }
    
    public void setSourceZDimension (SourceZDimension d) throws XmlContentException {
        setField (ELEM_Z, d);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_X, mainElem);
        outputGenericElement (ns, ELEM_Y, mainElem);
        outputGenericElement (ns, ELEM_Z, mainElem);

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
                localName, ELEM_X,
                SourceXDimension.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_Y,
                SourceYDimension.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_Z,
                SourceZDimension.class)) {
            return;
        }
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
