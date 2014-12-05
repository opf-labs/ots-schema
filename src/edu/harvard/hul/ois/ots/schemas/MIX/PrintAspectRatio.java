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

import edu.harvard.hul.ois.ots.schemas.XmlContent.IntegerElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.Restriction;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class PrintAspectRatio extends MixElement {

    private final static String ELEM_X = "xPrintAspectRatio";
    private final static String ELEM_Y = "yPrintAspectRatio";

    /**  Constructor from XML
     */
    public PrintAspectRatio (XMLStreamReader reader) 
               throws XMLStreamException, XmlContentException {
        name = "PrintAspectRatio";
        defineRestrictions();
        parse(reader);
    }
    
    /** No-argument constructor */
    public PrintAspectRatio () {
        super();
        name = "PrintAspectRatio";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_X, Restriction.NONNEGATIVE);
        addRestriction(ELEM_Y, Restriction.NONNEGATIVE);
    }

    public IntegerElement getXPrintAspectRatio () {
        return (IntegerElement) getField (ELEM_X);
    }
    
    public void setXPrintAspectRatio (int n) throws XmlContentException {
        setField (ELEM_X, n);
    }
    
    public IntegerElement getYPrintAspectRatio () {
        return (IntegerElement) getField (ELEM_Y);
    }
    
    public void setYPrintAspectRatio (int n) throws XmlContentException {
        setField (ELEM_Y, n);
    }
    
    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_X, mainElem);
        outputGenericElement (ns, ELEM_Y, mainElem);
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
                localName, ELEM_X,
                IntegerElement.class))
            return;
        if (match (reader, this, 
                localName, ELEM_Y,
                IntegerElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
