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

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.IntegerElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.Restriction;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class BitsPerSample extends MixElement {

    private final static String ELEM_VALUE = "bitsPerSampleValue";
    private final static String ELEM_UNIT = "bitsPerSampleUnit";

    private final static String[] VALUES_UNIT =
    { "integer", "floating point" };

    /**  Constructor for this element
     */
    public BitsPerSample (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "BitsPerSample";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public BitsPerSample () {
        super();
        name = "BitsPerSample";
        defineRestrictions();
    }

    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_VALUE, Restriction.NONNEGATIVE);
        addRestriction(ELEM_UNIT, VALUES_UNIT);
    }
    
    /** Returns the List of bitsPerSampleValues */
    @SuppressWarnings("unchecked")
	public List<IntegerElement> getBitsPerSampleValues () {
        return genericList( getList (ELEM_VALUE));
    }
    
    public void addBitsPerSampleValue (Integer n) throws XmlContentException {
        addToList (new IntegerElement (n, ELEM_VALUE), ELEM_VALUE);
    }
    
    public StringElement getBitsPerSampleUnit () {
        return (StringElement) getField (ELEM_UNIT);
    }
    
    public void setBitsPerSampleUnit (String s) throws XmlContentException {
        setField (ELEM_UNIT, s);
    }



    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputList (ns, ELEM_VALUE, mainElem);
        outputGenericElement (ns, ELEM_UNIT, mainElem);
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
        if (matchList (reader, this,
                localName, ELEM_VALUE,
                IntegerElement.class))
            return;
        if (match (reader, this,
                localName, ELEM_UNIT,
                StringElement.class))
            return;
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
