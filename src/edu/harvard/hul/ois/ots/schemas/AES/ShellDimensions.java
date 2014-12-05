/**********************************************************************
 * Copyright (c) 2010 by the President and Fellows of Harvard College
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 *
 * Contact information
 *
 * Office for Information Systems
 * Harvard University Library
 * Harvard University
 * Cambridge, MA  02138
 * (617)495-3724
 * hulois@hulmail.harvard.edu
 **********************************************************************/

package edu.harvard.hul.ois.ots.schemas.AES;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.staxmate.out.SMNamespace;
import org.codehaus.staxmate.out.SMOutputElement;

import edu.harvard.hul.ois.ots.schemas.XmlContent.XmlContentException;

public class ShellDimensions extends DimensionsSubtype {

    private final static String ELEMENTNAME = "shellDimensions";
    
    protected final static String TYPENAME = "shellDimensionsType";

    private final static String ELEM_LENGTH ="length";
    private final static String ELEM_WIDTH ="width";
    private final static String ELEM_DEPTH ="depth";
    private final static String ELEM_DIAMETER ="diameter";

    public ShellDimensions (XMLStreamReader reader) 
             throws XMLStreamException, XmlContentException {
        this.name = ELEMENTNAME;
        parse (reader);
    }
    
    public ShellDimensions () {
        this.name = ELEMENTNAME;
    }
    
    /** Returns the type name for this class. */
    @Override
    public String getTypeName () {
        return TYPENAME;
    }

    public Measurement getDiameter () {
        return (Measurement) getField (ELEM_DIAMETER);
    }
    
    public void setDiameter (Measurement m) throws XmlContentException {
        setField (ELEM_DIAMETER, m);
    }

    public Measurement getWidth () {
        return (Measurement) getField (ELEM_WIDTH);
    }
    
    public void setWidth (Measurement m) throws XmlContentException {
        setField (ELEM_WIDTH, m);
    }

    public Measurement getLength () {
        return (Measurement) getField (ELEM_LENGTH);
    }
    
    public void setLength (Measurement m) throws XmlContentException {
        setField (ELEM_LENGTH, m);
    }

    public Measurement getDepth () {
        return (Measurement) getField (ELEM_DEPTH);
    }
    
    public void setDepth (Measurement m) throws XmlContentException {
        setField (ELEM_DEPTH, m);
    }


    @Override
    protected void dispatch(String localName, XMLStreamReader reader)
            throws XMLStreamException, XmlContentException {
        if (match (reader, this, 
                localName, ELEM_LENGTH,
                Measurement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_WIDTH,
                Measurement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_DEPTH,
                Measurement.class)) {
            return;
        }
        if (match (reader, this, 
                localName, ELEM_DIAMETER,
                Measurement.class)) {
            return;
        }
        throw new XmlContentException ("Unknown element name " + localName);
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = parent.addElement(ns, name);
        outputAttrs (mainElem);
        outputGenericElement (ns, ELEM_LENGTH, mainElem);
        outputGenericElement (ns, ELEM_WIDTH, mainElem);
        outputGenericElement (ns, ELEM_DEPTH, mainElem);
        outputGenericElement (ns, ELEM_DIAMETER, mainElem);
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

}
