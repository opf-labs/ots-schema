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

public class CylinderDimensions extends DimensionsSubtype {

    protected final static String TYPENAME = "cylinderDimensionsType";

    private final static String ELEM_DIAMETER = "diameter";
    private final static String ELEM_LENGTH = "length";

    /** Returns the type name for this class. */
    @Override
    public String getTypeName () {
        return TYPENAME;
    }

    public Dimensions getDiameter () {
        return (Dimensions) getField(ELEM_DIAMETER);
    }
    
    public void setDiameter (Dimensions d) throws XmlContentException {
        setField (ELEM_DIAMETER, d);
    }
    
    public ShellDimensions getLength () {
        return (ShellDimensions) getField (ELEM_LENGTH);
    }
    
    public void setLength (ShellDimensions d) throws XmlContentException {
        setField (ELEM_LENGTH, d);
    }
    
    @Override
    protected void dispatch(String localName, XMLStreamReader reader)
            throws XMLStreamException, XmlContentException {
        if (match(reader, this, localName, ELEM_DIAMETER, Dimensions.class))
            return;
        if (match(reader, this, localName, ELEM_LENGTH, ShellDimensions.class))
            return;
    }

    @Override
    public void output(SMNamespace ns, SMOutputElement parent)
            throws XMLStreamException {
        SMOutputElement mainElem = outputElement (ns, name, parent); 
        outputGenericElement (ns, ELEM_DIAMETER, mainElem);
        outputGenericElement (ns, ELEM_LENGTH, mainElem);
    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return false;
    }

}
