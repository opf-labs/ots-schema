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

import edu.harvard.hul.ois.ots.schemas.XmlContent.RealElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class SourceZDimension extends MixElement {


    private final static String ELEM_VALUE = "sourceZDimensionValue";
    private final static String ELEM_UNIT = "sourceZDimensionUnit";

    /* Permitted values for unit */
    private final static String[] VALUES_UNIT = 
    { "in.", "mm."};

    /**  Constructor from XML
     */
    public SourceZDimension (XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        name = "SourceZDimension";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public SourceZDimension () {
        super();
        name = "SourceZDimension";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_UNIT, VALUES_UNIT);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_VALUE, mainElem);
        outputGenericElement (ns, ELEM_UNIT, mainElem);
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }
        
    public RealElement getSourceZDimensionValue () {
        return (RealElement) getField (ELEM_VALUE);
    }
    
    public void setSourceZDimensionValue (double d) throws XmlContentException {
        setField (ELEM_VALUE, d);
    }

    public StringElement getSourceZDimensionUnit () {
        return (StringElement) getField (ELEM_UNIT);
    }
    
    public void setSourceZDimensionUnit (String u) throws XmlContentException {
        setField (ELEM_UNIT, u);
        StringElement elem = (StringElement) getFieldElement (ELEM_UNIT);
        elem.checkContent(VALUES_UNIT);
    }
    
    /** Classes must implement this to handle START_ELEMENT cases.  */
    protected void dispatch (String localName, XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        if (match (reader, this, 
                localName, ELEM_VALUE,
                RealElement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_UNIT,
                StringElement.class)) {
            return;
        }
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
