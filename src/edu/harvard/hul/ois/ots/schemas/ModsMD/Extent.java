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

package edu.harvard.hul.ois.ots.schemas.ModsMD;

import edu.harvard.hul.ois.ots.schemas.XmlContent.IntegerElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.Restriction;
import edu.harvard.hul.ois.ots.schemas.XmlContent.StringElement;
import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;


/** The extent MODS element, which comes under part. */
public class Extent extends ModsElement {

    private final static String ELEM_START = "start";
    private final static String ELEM_END = "end";
    private final static String ELEM_TOTAL = "total";
    private final static String ELEM_LIST = "list";

    /**  Constructor for this element
     */
    public Extent (XMLStreamReader reader) 
           throws XMLStreamException, XmlContentException {
        name = "extent";
        defineRestrictions();
        parse (reader);
    }
    
    /** No-argument constructor */
    public Extent () {
        super();
        name = "extent";
        defineRestrictions();
    }
    
    /** Define restrictions */
    private void defineRestrictions () {
        addRestriction(ELEM_TOTAL, Restriction.NONNEGATIVE);
    }

    public StringElement getStart () {
        return (StringElement) getField(ELEM_START);
    }
    
    public void setStart (String st) throws XmlContentException {
        setField (ELEM_START, st);
    }

    public StringElement getEnd () {
        return (StringElement) getField(ELEM_END);
    }
    
    public void setEnd (String en) throws XmlContentException {
        setField (ELEM_END, en);
    }

    public IntegerElement getTotal () {
        return (IntegerElement) getField(ELEM_TOTAL);
    }
    
    public void setTotal (int tot) throws XmlContentException {
        setField (ELEM_TOTAL, tot);
    }

    public StringElement getList () {
        return (StringElement) getField(ELEM_LIST);
    }
    
    public void setList (String lst) throws XmlContentException {
        setField (ELEM_LIST, lst);
    }


    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

    /** Output method which adds to a parent element. Since Extent
     *  has non-repeating elements, it can't inherit the ModsElement method. 
     */
    @Override
    public void output(SMNamespace ns, SMOutputElement parent) throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputGenericElement (ns, ELEM_START, mainElem);
        outputGenericElement (ns, ELEM_END, mainElem);
        outputGenericElement (ns, ELEM_TOTAL, mainElem);
        outputGenericElement (ns, ELEM_LIST, mainElem);
    }

    protected void dispatch (String localName, XMLStreamReader reader) 
              throws XMLStreamException, XmlContentException {
        if (match (reader, this, 
               localName, ELEM_START,
               StringElement.class))
           return;
        if (match (reader, this, 
               localName, ELEM_END,
               StringElement.class))
           return;
        if (match (reader, this, 
               localName, ELEM_TOTAL,
               IntegerElement.class))
           return;
        if (match (reader, this, 
                localName, ELEM_LIST,
                StringElement.class))
            return;
        
        throw new XmlContentException ("Unknown element name " + localName);
    }

}
